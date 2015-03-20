/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barsystems.fornecedores;

import barsystems.conexaoBanco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class Class_Fornecedores {
    protected Class_Conexao_Banco banco = new Class_Conexao_Banco();
    protected Connection conexaoMySQL = banco.getConexaoMySQL();
    
    private String razao_social;
    private String nome_fantasia;
    private String cnpj;
    private String cidade;
    private String estado;
    private String endereco;
    private String bairro;
    private String telefone;
    private String email;
    private String inscricao_estadual;
    private String observacoes;
    private String id_fornecedor;
    
    /**
     * Construtor
     * @param razao_social
     * @param nome_fantasia
     * @param cnpj
     * @param cidade
     * @param estado
     * @param endereco
     * @param bairro
     * @param telefone
     * @param email
     * @param inscricao_estadual
     * @param observacoes 
     */
    public Class_Fornecedores( 
            String razao_social, 
            String nome_fantasia, 
            String cnpj,
            String cidade, 
            String estado, 
            String endereco, 
            String bairro, 
            String telefone, 
            String email, 
            String inscricao_estadual, 
            String observacoes){
        
            this.razao_social = razao_social;
            this.nome_fantasia = nome_fantasia; 
            this.cnpj = cnpj;
            this.cidade = cidade;
            this.estado = estado;
            this.endereco = endereco;
            this.bairro = bairro;
            this.telefone = telefone;
            this.email = email;
            this.inscricao_estadual = inscricao_estadual;
            this.observacoes = observacoes;
        
    }
    /**
     * Construtor sem parametros
     */
    public Class_Fornecedores(){
        
    }

    public String getId_fornecedor(){
        return id_fornecedor;
    }
    /**
     * @return the razao_social
     */
    public String getRazao_social() {
        return razao_social;
    }

    /**
     * @return the nome_fantasia
     */
    public String getNome_fantasia() {
        return nome_fantasia;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }


    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the inscricao_estadual
     */
    public String getInscricao_estadual() {
        return inscricao_estadual;
    }

    /**
     * @return the observacoes
     */
    public String getObservacoes() {
        return observacoes;
    }
    
    public boolean Cadastra(){
        
        String sql = "INSERT INTO fornecedores(razao_social, nome_fantasia, cnpj, cidade, estado, endereco, bairro, telefone, email, inscricao_estadual, observacoes)"+
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)";    
    
            try {    
                PreparedStatement stmt = conexaoMySQL.prepareStatement(sql);    
                stmt.setString(1, razao_social);
                stmt.setString(2, nome_fantasia);    
                stmt.setString(3, cnpj);    
                stmt.setString(4, cidade);
                stmt.setString(5, estado);
                stmt.setString(6, endereco);
                stmt.setString(7, bairro);
                stmt.setString(8, telefone);
                stmt.setString(9, email);
                stmt.setString(10, inscricao_estadual);
                stmt.setString(11, observacoes);
    
                if(!stmt.execute()){
                    stmt.close();
                    conexaoMySQL.close();
                    return false;
                }
                else{
                    stmt.execute();
                    stmt.close();
                    conexaoMySQL.close();
                    JOptionPane.showMessageDialog(null,"Fornecedor cadastrado com sucesso!");
                    return true;
                }
            } catch (SQLException u) {    
                throw new RuntimeException(u);    
        } 
    } // Fim Cadastra
    
    
    public boolean edita(
            String razao_social, 
            String nome_fantasia, 
            String cnpj,
            String cidade, 
            String estado, 
            String endereco, 
            String bairro, 
            String telefone, 
            String email, 
            String inscricao_estadual, 
            String observacoes,
            String codigo){
        
        String sql = "Update fornecedores set razao_social='"+razao_social+"', "
                + "nome_fantasia ='"+nome_fantasia+"', "
                + "cnpj='"+cnpj+"', cidade = '"+cidade+"', "
                + "estado='"+estado+"', "
                + "endereco = '"+endereco+"', "
                + "telefone = '"+telefone+"', "
                + "email = '"+email+"', "
                + "inscricao_estadual = '"+inscricao_estadual+"', "
                + "observacoes = '"+observacoes+"' "
                + "WHERE id_fornecedor = '"+codigo+"'";
    
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
    
    public boolean exclui(String codigo){
        String sql = "UPDATE fornecedores set excluido = 1 where id_fornecedor = '"+codigo+"'";    
    
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
    
    public DefaultListModel carregaLista(){
        DefaultListModel listModel = new DefaultListModel();
        
        //ListModel lista = new ListModel();
        try{
            
           String sql = "SELECT nome_fantasia FROM fornecedores where excluido = 0";  
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
    
    public void carregaFornecedor(String nome){
        try{
            
           String sql = "SELECT razao_social, nome_fantasia, cnpj, cidade, estado, endereco, bairro, telefone, email, inscricao_estadual, observacoes, id_fornecedor FROM fornecedores where excluido = 0 and nome_fantasia = '"+nome+"'";  
            PreparedStatement stmt = conexaoMySQL.prepareStatement(sql);  
   
            ResultSet rs = stmt.executeQuery();  
              
            while(rs.next()){ 
                this.razao_social = rs.getString(1);
                this.nome_fantasia = rs.getString(2); 
                this.cnpj = rs.getString(3);
                this.cidade = rs.getString(4);
                this.estado = rs.getString(5);
                this.endereco = rs.getString(6);
                this.bairro = rs.getString(7);
                this.telefone = rs.getString(8);
                this.email = rs.getString(9);
                this.inscricao_estadual = rs.getString(10);
                this.observacoes = rs.getString(11);
                this.id_fornecedor = rs.getString(12);
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
            
           String sql = "SELECT nome_fantasia FROM fornecedores where excluido = 0 and nome_fantasia like'%"+pesquisa+"%'";  
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
    
    public int retornaIdFornecedor(String nome_fantasia) {
        int id = 0;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("select id_fornecedor from fornecedores "
                    + "where nome_fantasia = '"+nome_fantasia+"'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    
}


