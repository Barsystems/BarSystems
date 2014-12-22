/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barsystems.estoque;

import barsystems.conexaoBanco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    public DefaultTableModel carregaTabela(String codigo){
        DefaultTableModel tabela = new DefaultTableModel(null, new String[] {"Produto", "Quantidade por caixa", 
            "Quantidade em unidade", "Valor compra unidade", "Valor venda unidade"});
        
        try{
            
           String sql = "SELECT descricao, "
                   + "quantidade_por_caixa, "
                   + "quantidade_em_unidade, "
                   + "valor_compra_unidade, "
                   + "valor_venda_unidade "
                   + "from produtos, produtos_centro_estoque "
                   + "where produtos.id_produto = produtos_centro_estoque.id_produto and id_centro_estoque ='"+codigo+"' and produtos.excluido = 0";  
           PreparedStatement stmt = conexaoMySQL.prepareStatement(sql);  
           
           ResultSet rs = stmt.executeQuery();  
              
            while(rs.next()){  
               tabela.addRow(new Object[]{rs.getString(1),
                                          rs.getString(2),
                                          rs.getString(3),
                                          rs.getString(4),
                                          rs.getString(5)});
            }
            rs.close();  
            stmt.close();
            conexaoMySQL.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return tabela;
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
    
}
