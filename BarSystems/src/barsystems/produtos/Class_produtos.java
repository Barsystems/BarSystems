/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barsystems.produtos;
import barsystems.conexaoBanco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Lucas
 */
public class Class_produtos {
    protected Class_Conexao_Banco banco = new Class_Conexao_Banco();
    protected Connection conexaoMySQL = banco.getConexaoMySQL();
    
    protected String descricao, quantidade_por_caixa, tipo, valor_compra_unidade,valor_unidade_venda, codigo;
    
    public Class_produtos(){
        
    }
    
    public Class_produtos(String codigo, String descricao, String quantidade, String tipo, String valor_compra, String valor_venda){
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidade_por_caixa = quantidade;
        this.tipo = tipo;
        this.valor_compra_unidade = valor_compra;
        this.valor_unidade_venda = valor_venda;
    }
    
    /** Cadastra
     *  Realiza cadastro de produtos no banco de dados
     * @return true or false
     */
    public boolean Cadastra(){
        
        String sql = "INSERT INTO produtos(id_produto, descricao,quantidade_por_caixa,tipo,excluido,valor_compra_unidade,valor_venda_unidade)"+
                "VALUES(?,?,?,?,?,?,?)";    
    
            try {    
                PreparedStatement stmt = conexaoMySQL.prepareStatement(sql);    
                stmt.setInt(1, Integer.parseInt(codigo));
                stmt.setString(2, descricao);    
                stmt.setInt(3, Integer.parseInt(quantidade_por_caixa));    
                stmt.setString(4, tipo); 
                stmt.setBoolean(5, false);
                stmt.setFloat(6, Float.parseFloat(valor_compra_unidade));
                stmt.setFloat(7, Float.parseFloat(valor_unidade_venda));
    
                if(!stmt.execute()){
                    stmt.close();
                    return false;
                }
                else{
                    stmt.execute();
                    stmt.close();
                    JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");
                    return true;
                }
            } catch (SQLException u) {    
                throw new RuntimeException(u);    
        } 
    } // Fim Cadastra
    
    public ListModel carregaLista(){
        ListModel lista = new ListModel();
        try{
            
            String sql = "SELECT descricao FROM produtos";  
            PreparedStatement stmt = conexaoMySQL.prepareStatement(sql);  
   
            ResultSet rs = stmt.executeQuery();  
              
            while(rs.next()){  
                lista.a
            }              
            rs.close();  
            stmt.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }
    
}
