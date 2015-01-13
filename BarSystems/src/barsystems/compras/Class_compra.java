/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barsystems.compras;

import barsystems.conexaoBanco.Class_Conexao_Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lucas
 */
public class Class_compra {
    protected Class_Conexao_Banco banco = new Class_Conexao_Banco();
    private String id_compra;
    private String descricao;
    private String fornecedor;
    private String numero_nota;
    private String data;
    private String responsavel;
    private String valor;
 /**
     * carregaLista
     * @return lista com o campo codigo de todas as compras cadastradas
     */
    
    /**
     * Carrega tabela com dados passados
     * @param codigo
     * @param tabela 
     */
    public void carregaTabela(DefaultTableModel tabela){
        
        tabela.setRowCount(0);       
        try{
            
            String sql = "SELECT id_compra, descricao, data from compras where excluido = 0 order by id_compra";
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            {
                
                tabela.addRow(new Object[] {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                });
            }
            
            rs.close();  
            stmt.close();
            banco.FecharConexao();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }// FIM CARREGA TABELA   
    
    public void carregaDados(String codigo){
        try{
            
            String sql = "SELECT id_compra, descricao, nome_fantasia, numero_nota, data, responsavel, valor "
                    + "from compras, fornecedores "
                    + "where id_compra = '"+codigo+"'"
                    + "and codigo_fornecedor = id_fornecedor "
                    + "and compras.excluido = 0";
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            {
                    setId_compra(rs.getString(1));
                    setDescricao(rs.getString(2));
                    setFornecedor(rs.getString(3));
                    setNumero_nota(rs.getString(4));
                    setData(rs.getString(5));
                    setResponsavel(rs.getString(6));
                    setValor(rs.getString(7));
            }
            
            rs.close();  
            stmt.close();
            banco.FecharConexao();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void carregaProdutos_Compra(String codigo, DefaultTableModel tabela){
        tabela.setRowCount(0);       
        try{
            
            String sql = "SELECT produtos.descricao, quantidade_em_caixa "
                    + "from produtos_compra, produtos "
                    + "where id_compras_fk = '"+codigo+"' and id_produtos = id_produto";
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            {
                
                tabela.addRow(new Object[] {
                    rs.getString(1),
                    rs.getString(2),
                });
            }
            
            rs.close();  
            stmt.close();
            banco.FecharConexao();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public DefaultListModel carregaTipo(){
        DefaultListModel listModel = new DefaultListModel();
        try{
            
            String sql = "SELECT DISTINCT tipo from produtos where excluido = 0";
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            {
                listModel.addElement(rs.getString(1));
            }
            
            rs.close();  
            stmt.close();
            banco.FecharConexao();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return listModel;
    }// FIM CARREGA LISTA
    
    public DefaultListModel carregaListaProduto(String tipo){
        DefaultListModel listModel = new DefaultListModel();
        try{
            
            String sql = "SELECT descricao from produtos where excluido = 0 and tipo = '"+tipo+"'";
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            {
                listModel.addElement(rs.getString(1));
            }
            
            rs.close();  
            stmt.close();
            banco.FecharConexao();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return listModel;
    }// FIM CARREGA LISTA

    /**
     * @return the id_compra
     */
    public String getId_compra() {
        return id_compra;
    }

    /**
     * @param id_compra the id_compra to set
     */
    public void setId_compra(String id_compra) {
        this.id_compra = id_compra;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the fornecedor
     */
    public String getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * @return the numero_nota
     */
    public String getNumero_nota() {
        return numero_nota;
    }

    /**
     * @param numero_nota the numero_nota to set
     */
    public void setNumero_nota(String numero_nota) {
        this.numero_nota = numero_nota;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the responsavel
     */
    public String getResponsavel() {
        return responsavel;
    }

    /**
     * @param responsavel the responsavel to set
     */
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
