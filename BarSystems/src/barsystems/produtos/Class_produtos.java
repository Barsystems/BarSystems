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
import java.text.DecimalFormat;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;


/**
 *
 * @author Lucas
 */
public class Class_produtos {
    protected Class_Conexao_Banco banco = new Class_Conexao_Banco();
    protected Connection conexaoMySQL = banco.getConexaoMySQL();
    protected DecimalFormat dffloat = new DecimalFormat("##,###.00");
    
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
    public String getCodigo(){
        return codigo;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public String getQuantidade(){
        return quantidade_por_caixa;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public String getValor_Compra(){
        return valor_compra_unidade;
    }
    
    public String getValor_Venda(){
        return valor_unidade_venda;
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
                    conexaoMySQL.close();
                    return false;
                }
                else{
                    stmt.execute();
                    stmt.close();
                    conexaoMySQL.close();
                    JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");
                    return true;
                }
            } catch (SQLException u) {    
                throw new RuntimeException(u);    
        } 
    } // Fim Cadastra
    
    /**
     * Edita dados de produtos
     * @param codigo
     * @param descricao
     * @param quantidade
     * @param tipo
     * @param valor_compra
     * @param valor_venda
     * @return true se conseguiu atualizar o valor do produto
     */
    public boolean edita(
            String codigo, 
            String descricao, 
            String quantidade, 
            String tipo, 
            String valor_compra, 
            String valor_venda){
        
        String sql = "Update produtos set descricao='"+descricao+
                "',quantidade_por_caixa='"+Integer.parseInt(quantidade)+
                "',tipo='"+tipo+"',valor_compra_unidade="+
                Float.parseFloat(valor_compra)+
                ",valor_venda_unidade="+Float.parseFloat(valor_venda)+" WHERE id_produto = "+codigo;
    
            try {    
                PreparedStatement stmt = conexaoMySQL.prepareStatement(sql);    
                
    
                stmt.executeUpdate();
                    stmt.close();
                    conexaoMySQL.close();
                    return true;
                
                    
                }
             catch (SQLException u) {    
                throw new RuntimeException(u);   
                
        } 
    }
    
    /**
     * Exclui produto com codigo do parametro recebido
     * @param codigo
     * @return true se a operacao foi efetuada com sucesso
     */
    
    public boolean exclui(String codigo){
        String sql = "UPDATE produtos set excluido = 1 where id_produto = '"+codigo+"'";    
    
            try {    
                PreparedStatement stmt = conexaoMySQL.prepareStatement(sql);    
                if(!stmt.execute()){
                    stmt.close();
                    conexaoMySQL.close();
                    return false;
                }
                else{
                    stmt.execute();
                    stmt.close();
                    conexaoMySQL.close();
                    JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");
                    return true;
                }
            } catch (SQLException u) {    
                throw new RuntimeException(u);    
        } 
    }
    
    /**
     * carregaLista
     * @return lista com o campo descricao de todos os produtos cadastrados
     */
    
    public DefaultListModel carregaLista(){
        DefaultListModel listModel = new DefaultListModel();
        
        //ListModel lista = new ListModel();
        try{
            
           String sql = "SELECT descricao FROM produtos where excluido = 0 order by descricao";  
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
     * CarregaProduto
     * Retorna o prduto selecionado na Jlist do formulario Painel_Produtos!
     * OBS: NAO USAR SE NAO ESTIVER COM JList SELECIONADO!
     * @param nome 
     */
    public void carregaProduto(String nome){
        try{
            
           String sql = "SELECT id_produto, descricao, quantidade_por_caixa, tipo, valor_compra_unidade, valor_venda_unidade FROM produtos where excluido = 0 and descricao = '"+nome+"'";  
            PreparedStatement stmt = conexaoMySQL.prepareStatement(sql);  
   
            ResultSet rs = stmt.executeQuery();              
            while(rs.next()){
                
                String valorCompra = dffloat.format(rs.getFloat(5));
                if (valorCompra.equals(",00")) {
                    valorCompra = "0,00";
                }
                String valorVenda = dffloat.format(rs.getFloat(6));
                if (valorVenda.equals(",00")) {
                    valorVenda = "0,00";
                }
                this.codigo = rs.getString(1);
                this.descricao = rs.getString(2);
                this.quantidade_por_caixa = rs.getString(3);
                this.tipo = rs.getString(4);
                this.valor_compra_unidade = valorCompra;
                this.valor_unidade_venda = valorVenda;
            }              
            rs.close();  
            stmt.close();
            conexaoMySQL.close();
        
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    } // FIM CARREGA PRODUTOS
    
    public DefaultListModel pesquisa(String pesquisa){
        DefaultListModel listModel = new DefaultListModel();
        
        //ListModel lista = new ListModel();
        try{
            
           String sql = "SELECT descricao FROM produtos where excluido = 0 and descricao like'%"+pesquisa+"%'";  
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
    }// FIM PESQUISA
    
    
}
