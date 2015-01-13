/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barsystems.estoque;

import barsystems.Class_Troca_Virgula_Por_Ponto;
import barsystems.conexaoBanco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lucas
 */
public class Class_estoque {
    protected Class_Conexao_Banco banco = new Class_Conexao_Banco();
    protected Connection conexaoMySQL = banco.getConexaoMySQL();
    
    private String codigo_compra;
    private Date data;
    private String fornecedor;
 
    public Class_estoque(){
        
    }
    
    public DefaultListModel carregaLista(){
        DefaultListModel listModel = new DefaultListModel();
        
        //ListModel lista = new ListModel();
        try{
            
           String sql = "SELECT nome FROM centros_estoque where excluido = 0";  
           PreparedStatement stmt = conexaoMySQL.prepareStatement(sql);  
           
           ResultSet rs = stmt.executeQuery();  
              
            while(rs.next()){  
               listModel.addElement(rs.getString(1));
            }              
            rs.close();  
            stmt.close();
            conexaoMySQL.close();
        
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return listModel;
    }// FIM CARREGA LISTA
    
    /**
     * Carrega tabela com dados passados
     * @param codigo
     * @param tabela 
     */
    public void carregaTabela(String codigo, DefaultTableModel tabela){
        
        tabela.setRowCount(0);
        
        String valorCompra = null, valorVenda = null;
        DecimalFormat dffloat = new DecimalFormat("##,###.00");
        
        try{
            
            String sql = "SELECT descricao, "
                   + "quantidade_por_caixa, "
                   + "quantidade_em_unidade, "
                   + "valor_compra_unidade, "
                   + "valor_venda_unidade "
                   + "from produtos, produtos_centro_estoque, produtos_compra "
                   + "where produtos.id_produto = produtos_centro_estoque.id_produto and produtos_centro_estoque.id_centro_estoque ='"+codigo+"' and  produtos.id_produto = produtos_compra.id_produtos  and produtos.excluido = 0";  
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            {
                valorCompra = dffloat.format(rs.getFloat(4));
                if (valorCompra.equals(",00")) {
                    valorCompra = "0,00";
                }
                valorVenda= dffloat.format(rs.getFloat(5));
                if (valorVenda.equals(",00")) {
                    valorVenda = "0,00";
                }
                
                tabela.addRow(new Object[] {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    valorCompra,
                    valorVenda
                });
            }
            
            rs.close();  
            stmt.close();
            banco.FecharConexao();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public String getCodigo(String nome){
        String codigo = null;
        try{
            
           String sql = "SELECT id_centro_estoque from centros_estoque where nome = '"+nome+"'";  
           PreparedStatement stmt = conexaoMySQL.prepareStatement(sql);  
           
           ResultSet rs = stmt.executeQuery();  
              
            while(rs.next()){  
               codigo = rs.getString(1);
            }
            rs.close();  
            stmt.close();
            conexaoMySQL.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
       return codigo;
    }
    
    public void carregaDetalhes(String nome){
        try{
           
           String sql = "SELECT MAX(id_produtos_compra),id_compras_fk,data_compra, nome_fantasia from compras,produtos,produtos_compra, fornecedores where produtos.descricao = '"+nome+"' and id_produto = id_produtos and id_compra = id_compras_fk and id_fornecedor = codigo_fornecedor";  
           PreparedStatement stmt = conexaoMySQL.prepareStatement(sql);  
           
           ResultSet rs = stmt.executeQuery();  
              
            while(rs.next()){
              codigo_compra = rs.getString(2);
              data = rs.getDate(3);
              fornecedor = rs.getString(4);
              
            }
            rs.close();  
            stmt.close();
            conexaoMySQL.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void transferencia(String centro, String produto, int quantidade_passada, String tipo, int qtd_por_caixa, String centro_de_transferencia){
        int count=0;
        int codigo_centro =0;
        int codigo_produto =0;
        if(tipo.equals("Caixa")){
            try { 
                String sql = "Select produtos_centro_estoque.id_produto, centros_estoque.id_centro_estoque "
                        + "from produtos_centro_estoque, produtos, centros_estoque "
                        + "where centros_estoque.nome = '"+centro+"' "
                        + "and centros_estoque.id_centro_estoque = produtos_centro_estoque.id_centro_estoque "
                        + "and descricao = '"+produto+"' "
                        + "and produtos.id_produto = produtos_centro_estoque.id_produto";
                PreparedStatement stmt = conexaoMySQL.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();  
                
                while(rs.next()){
                    codigo_produto = rs.getInt(1);
                    codigo_centro = rs.getInt(2);
                    count++;
                }
                rs.close();
                stmt.close();
                if(count > 0){
                    sql = "Update produtos_centro_estoque set quantidade_em_unidade = quantidade_em_unidade+("+quantidade_passada*qtd_por_caixa+") WHERE id_produto = "+codigo_produto+" and id_centro_estoque = "+codigo_centro;
                    PreparedStatement stmt2 = conexaoMySQL.prepareStatement(sql);
                    stmt2.executeUpdate();
                    stmt2.close();
                }else{
                    sql = "Select id_centro_estoque, id_produto from centros_estoque, produtos WHERE nome = '"+centro+"' and descricao = '"+produto+"'";
                    PreparedStatement stmt3 = conexaoMySQL.prepareStatement(sql);
                    
                    ResultSet rs2 = stmt3.executeQuery();  
                
                    while(rs2.next()){
                        codigo_centro = rs2.getInt(1);
                        codigo_produto = rs2.getInt(2);
                    }
                    rs2.close();
                    stmt3.close();
                    sql = "Insert INTO produtos_centro_estoque "
                            + "(id_produto, id_centro_estoque, quantidade_em_unidade) "
                            + "VALUES (?,?,?)";
                    PreparedStatement stmt4 = banco.getConexaoMySQL().prepareStatement(sql);    
                    stmt4.setInt(1, codigo_produto);
                    stmt4.setInt(2, codigo_centro);        
                    stmt4.setInt(3, quantidade_passada*qtd_por_caixa);
                    
                    stmt4.execute();
                    stmt4.close();
                }
                    
                sql = "Select id_centro_estoque, id_produto from centros_estoque, produtos WHERE nome = '"+centro_de_transferencia+"' and descricao = '"+produto+"'";
                PreparedStatement stmt5 = conexaoMySQL.prepareStatement(sql);

                ResultSet rs3 = stmt5.executeQuery();  

                while(rs3.next()){
                    codigo_centro = rs3.getInt(1);
                    codigo_produto = rs3.getInt(2);
                }    
                
                rs3.close();
                stmt5.close();
                
                sql = "Update produtos_centro_estoque set quantidade_em_unidade = quantidade_em_unidade-("+quantidade_passada*qtd_por_caixa+") WHERE id_produto = "+codigo_produto+" and id_centro_estoque = "+codigo_centro;
                PreparedStatement stmt6 = conexaoMySQL.prepareStatement(sql);
                stmt6.executeUpdate();
                stmt6.close();
                
                
                
                banco.FecharConexao();    
            }
             catch (SQLException u) {    
                throw new RuntimeException(u);   
                
        }
        }else if(tipo.equals("Unidade")){
            try { 
                String sql = "Select produtos_centro_estoque.id_produto, centros_estoque.id_centro_estoque "
                        + "from produtos_centro_estoque, produtos, centros_estoque "
                        + "where centros_estoque.nome = '"+centro+"' "
                        + "and centros_estoque.id_centro_estoque = produtos_centro_estoque.id_centro_estoque "
                        + "and descricao = '"+produto+"' "
                        + "and produtos.id_produto = produtos_centro_estoque.id_produto";
                PreparedStatement stmt = conexaoMySQL.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();  
                
                while(rs.next()){
                    codigo_produto = rs.getInt(1);
                    codigo_centro = rs.getInt(2);
                    count++;
                }
                rs.close();
                stmt.close();
                if(count > 0){
                    sql = "Update produtos_centro_estoque set quantidade_em_unidade = quantidade_em_unidade+("+quantidade_passada+") WHERE id_produto = "+codigo_produto+" and id_centro_estoque = "+codigo_centro;
                    PreparedStatement stmt2 = conexaoMySQL.prepareStatement(sql);
                    stmt2.executeUpdate();
                    stmt2.close();
                }else{
                    sql = "Select id_centro_estoque, id_produto from centros_estoque, produtos WHERE nome = '"+centro+"' and descricao = '"+produto+"'";
                    PreparedStatement stmt3 = conexaoMySQL.prepareStatement(sql);
                    
                    ResultSet rs2 = stmt3.executeQuery();  
                
                    while(rs2.next()){
                        codigo_centro = rs2.getInt(1);
                        codigo_produto = rs2.getInt(2);
                    }
                    rs2.close();
                    stmt3.close();
                    sql = "Insert INTO produtos_centro_estoque "
                            + "(id_produto, id_centro_estoque, quantidade_em_unidade) "
                            + "VALUES (?,?,?)";
                    PreparedStatement stmt4 = banco.getConexaoMySQL().prepareStatement(sql);    
                    stmt4.setInt(1, codigo_produto);
                    stmt4.setInt(2, codigo_centro);        
                    stmt4.setInt(3, quantidade_passada);
                    
                    stmt4.execute();
                    stmt4.close();
                }
                    
                sql = "Select id_centro_estoque, id_produto from centros_estoque, produtos WHERE nome = '"+centro_de_transferencia+"' and descricao = '"+produto+"'";
                PreparedStatement stmt5 = conexaoMySQL.prepareStatement(sql);

                ResultSet rs3 = stmt5.executeQuery();  

                while(rs3.next()){
                    codigo_centro = rs3.getInt(1);
                    codigo_produto = rs3.getInt(2);
                }    
                
                rs3.close();
                stmt5.close();
                
                sql = "Update produtos_centro_estoque set quantidade_em_unidade = quantidade_em_unidade-("+quantidade_passada+") WHERE id_produto = "+codigo_produto+" and id_centro_estoque = "+codigo_centro;
                PreparedStatement stmt6 = conexaoMySQL.prepareStatement(sql);
                stmt6.executeUpdate();
                stmt6.close();
                
                
                
                banco.FecharConexao();    
            }
             catch (SQLException u) {    
                throw new RuntimeException(u);   
                
        }
            
        }
    }

    /**
     * @return the codigo_compra
     */
    public String getCodigo_compra() {
        return codigo_compra;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @return the fornecedor
     */
    public String getFornecedor() {
        return fornecedor;
    }
    
    
}
