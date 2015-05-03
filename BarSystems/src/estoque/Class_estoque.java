
package estoque;

import conexao_banco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import produtos.Class_produtos;

public class Class_estoque {
 
    public Class_estoque(){
        
    }
    
    public void carregaCentrosEstoqueComboBox(JComboBox combo) {
        combo.removeAllItems();
        try {
            String sql = "SELECT nome FROM centros_estoque WHERE excluido = 0";
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement stmt = conn.prepareStatement(sql);  
            ResultSet rs = stmt.executeQuery();  
            while(rs.next())
            {
                combo.addItem(rs.getString(1));
            }
            rs.close();  
            stmt.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
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
    
    public boolean verficaProdutoExistenteCentroEstoque(int id_produto, int id_centro){
        boolean flag = false;
         try{
            String sql = "SELECT id_produto FROM produtos_centro_estoque WHERE id_produto = '"+id_produto+"' AND id_centro_estoque = '"+id_centro+"'";
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement(sql);  
            ResultSet rs = ps.executeQuery();  
            if (rs.next()) {
                flag = true;
            }
            
            rs.close();
            ps.close();
            conn.close();        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return flag;
    }
    
    public void cadastraProdutosCentroEstoque(JTable tabela_produtos) {
        try {
            String sql_insert = "INSERT INTO produtos_centro_estoque (id_produto, id_centro_estoque, quantidade_em_unidade ) VALUES (?, ?, ?)";
            String sql_update = "UPDATE produtos_centro_estoque SET quantidade_em_unidade = (?+ quantidade_em_unidade) WHERE id_produto = ? and id_centro_estoque = ?";

            Class_produtos produtos = new Class_produtos();
            Class_estoque estoque = new Class_estoque();
            DefaultTableModel modelo = (DefaultTableModel) tabela_produtos.getModel();
            
            int id_produto, id_centro_estoque, quantidade;
            
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = null;
            for (int i = 0; i < tabela_produtos.getRowCount(); i++) {
                id_produto = produtos.retornaIdProduto((String) modelo.getValueAt(i, 0));
                id_centro_estoque = estoque.retornaIdCentroEstoque((String) modelo.getValueAt(i, 4));
                quantidade = (int) modelo.getValueAt(i, 1);
                if (verficaProdutoExistenteCentroEstoque(id_produto, id_centro_estoque)) {
                    ps = conn.prepareStatement(sql_update);
                    ps.setInt(1, quantidade);
                    ps.setInt(2, id_produto);
                    ps.setInt(3, id_centro_estoque); 
                    ps.executeUpdate();
                } else {
                    ps = conn.prepareStatement(sql_insert);
                    ps.setInt(1, id_produto);
                    ps.setInt(2, id_centro_estoque); 
                    ps.setInt(3, quantidade);
                    ps.executeUpdate();
                }
            }
            ps.close();
            conn.close();
        } catch (Exception e) {    
            e.printStackTrace();
        } 
   
    }
}
