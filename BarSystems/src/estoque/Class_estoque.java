
package estoque;

import conexao_banco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import produtos.Class_produtos;

public class Class_estoque {
 
    public Class_estoque(){
        
    }
    
    public void cadastraCentro(String centro) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO centros_estoque (nome) VALUES (?)");
            ps.setString(1, centro);
            ps.executeUpdate();
            
            ps.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Centro de estoque cadastrado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean verificaNomeRepetidoCentro(String centro) {
        boolean flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT nome FROM centros_estoque "
                    + "WHERE nome = '"+centro+"' AND excluido = 0");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
            
            ps.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    public DefaultListModel carregaLista() {
        DefaultListModel listModel = new DefaultListModel();
        
        try {
            
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            String sql = "SELECT nome FROM centros_estoque WHERE excluido = 0";  
            PreparedStatement ps = conn.prepareStatement(sql);  
           
            ResultSet rs = ps.executeQuery();  
              
            while(rs.next()) {  
                listModel.addElement(rs.getString(1));
            }
            
            rs.close();  
            ps.close();
            conn.close();
        
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        return listModel;
    }// FIM CARREGA LISTA
    
    /**
     * Carrega tabela com dados passados
     * @param codigo
     * @param tabela 
     */
    public void carregaTabela(int codigo, DefaultTableModel tabela) {
        
        tabela.setRowCount(0);
        NumberFormat z = NumberFormat.getCurrencyInstance();
        
        try {
            String sql = "SELECT produtos.descricao, produtos_centro_estoque.quantidade_em_unidade, produtos.valor_venda_unidade, "
                    + "produtos.id_produto "
                    + "FROM produtos "
                    + "INNER JOIN produtos_centro_estoque ON produtos.id_produto = produtos_centro_estoque.id_produto "
                    + "WHERE produtos_centro_estoque.id_centro_estoque = '"+codigo+"' and produtos.excluido = 0";  
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement(sql);  
           
            ResultSet rs = ps.executeQuery();  
            
            while(rs.next()) {                
                tabela.addRow(new Object[] {
                    rs.getString(1),
                    rs.getInt(2),
                    z.format(rs.getFloat(3)),
                    rs.getInt(4)
                });
            }
            
            rs.close();  
            ps.close();
            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public int getCodigo(String nome) {
        
        int codigo = 0;
        
        try {
            
            String sql = "SELECT id_centro_estoque FROM centros_estoque WHERE nome = '"+nome+"'"; 
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement(sql);  
           
            ResultSet rs = ps.executeQuery();  
              
            while(rs.next()){  
               codigo = rs.getInt(1);
            }
            rs.close();  
            ps.close();
            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
        
       return codigo;
    }
    
    public int retornaIdCentroEstoque(String nome) {
        int codigo = 0;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT id_centro_estoque FROM centros_estoque "
                    + "WHERE nome = '"+nome+"'");  
           
            ResultSet rs = ps.executeQuery();  
              
            while(rs.next()){  
               codigo = rs.getInt(1);
            }
            rs.close();  
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codigo;
    }
    
    public void transferencia(String centro_de_transferencia, String produto, int quantidade_passada, String centro) {
        
        int count = 0;
        boolean verfica = false;
        int id_centro_origem = retornaIdCentroEstoque(centro);
        int id_centro_transferencia = retornaIdCentroEstoque(centro_de_transferencia);
        Class_produtos produtos = new Class_produtos();
        int codigo_produto = produtos.retornaIdProduto(produto);
        
        try {
            
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            
            //Verificar se o produto já existe no centro de estoque
            String sql = "Select id_produto, id_centro_estoque from produtos_centro_estoque "
                    + "where id_produto='"+codigo_produto+"'"
                    + "and id_centro_estoque='"+id_centro_transferencia+"'";
            
            PreparedStatement ps = conn.prepareStatement(sql);  
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){
                
                count++;
                
            }
            
            //Se o produto já existe só acrescenta
            if (count > 0) {
                sql = "UPDATE produtos_centro_estoque SET "
                        + "quantidade_em_unidade = quantidade_em_unidade + ("+quantidade_passada+") "
                        + "WHERE id_produto = "+codigo_produto+" AND id_centro_estoque = "+id_centro_transferencia;
                ps = conn.prepareStatement(sql);
                ps.executeUpdate();
            } else { //Se não, devemos fazer um novo insert
                
                sql = "INSERT INTO produtos_centro_estoque (id_produto, id_centro_estoque, quantidade_em_unidade) "
                        + "VALUES (?, ?, ?)";
                ps = conn.prepareStatement(sql);    
                ps.setInt(1, codigo_produto);
                ps.setInt(2, id_centro_transferencia);        
                ps.setInt(3, quantidade_passada);
                ps.execute();
            }

            //Por ultimo, basta retirar o produto do centro de origem
            sql = "UPDATE produtos_centro_estoque SET "
                    + "quantidade_em_unidade = quantidade_em_unidade - ("+quantidade_passada+") "
                    + "WHERE id_produto = "+codigo_produto+" and id_centro_estoque = "+id_centro_origem;
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            
            ps.close();
            rs.close();
            conn.close();
            
            JOptionPane.showMessageDialog(null,"Transferido com sucesso!","Sucesso",JOptionPane.PLAIN_MESSAGE);
        }
         catch (Exception e) {    
            e.printStackTrace();
        }
    }    
}
