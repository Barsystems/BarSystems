/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barsystems.compras;

import barsystems.Class_Troca_Virgula_Por_Ponto;
import barsystems.conexaoBanco.Class_Conexao_Banco;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
            
            String sql = "SELECT produtos.descricao, quantidade_em_unidade "
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
    
    
    public List carrega_Fornecedor(){
        List lista = new List();
        try{
            
            String sql = "SELECT nome_fantasia from fornecedores where excluido = 0";
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            {
                lista.add(rs.getString(1));
            }
            
            rs.close();  
            stmt.close();
            banco.FecharConexao();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }// FIM CARREGA LISTA
    
    public String carrega_valor(String produto){
       String valor = "0";
        try{
            
            String sql = "SELECT DISTINCT valor_compra_unidade from produtos where excluido = 0 and descricao ='"+produto+"'";
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            {
                valor = rs.getString(1);
            }
            
            rs.close();  
            stmt.close();
            banco.FecharConexao();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return valor;
    }// FIM CARREGA LISTA
    
    /**
     * 
     * @param descricao
     * @param cod_fornecedor
     * @param numero_nota
     * @param data
     * @param valor
     * @param parcelas
     * @param tabela
     * @param sub_valor
     * @param qtd_de_caixa
     * @param qtd_por_caixa
     * @param id_centro_estoque 
     */
    public void realiza_Compra( String descricao,
                                String cod_fornecedor,
                                String numero_nota,
                                String data,
                                String valor,
                                String parcelas,
                                TableModel tabela,
                                String id_centro_estoque){
        int valor_unidade = 0;
        String id_compra = "";
        String id_produto = "";
        String sql = "INSERT INTO compras ( descricao, codigo_fornecedor, numero_nota, data, valor, parcelas)"+
                "VALUES(?,?,?,?,?,?)";    
    
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
        try {
            Connection con = banco.getConexaoMySQL();
            PreparedStatement stmt = con.prepareStatement(sql);    
            stmt.setString(1, descricao);
            stmt.setInt(2, Integer.parseInt(cod_fornecedor));        
            stmt.setString(3, numero_nota); 
            stmt.setString(4, data);
            stmt.setFloat(5, troca.trocaVirgulaPorPonto(valor));
            stmt.setString(6, parcelas);

            if(!stmt.execute()){
                stmt.close();
                con.close();
            }
            else{
                stmt.execute();
                stmt.close();
                con.close();
                JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");
            }
        } catch (SQLException u) {    
            throw new RuntimeException(u);    
        } 
        
       try{
            sql = "SELECT MAX(id_compra) from compras where excluido = 0";
            PreparedStatement stmt2 = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs2 = stmt2.executeQuery();  
            
            while(rs2.next())
            {
                id_compra = rs2.getString(1);
            }
            
            rs2.close();  
            stmt2.close();
            banco.FecharConexao();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
       
       try {
           for(int i=0;i<tabela.getRowCount();i++){
               id_produto = retornaId_Produtos_da_Compra((String) tabela.getValueAt(i, 2));
                sql = "INSERT INTO produtos_compra ( id_produtos, valor_compra, quantidade_em_unidade, data_compra, id_centro_estoque, id_compras_fk)"+
                     "VALUES(?,?,?,?,?,?)";  
                 Connection con = banco.getConexaoMySQL();
                 PreparedStatement stmt3 = con.prepareStatement(sql);    
                 stmt3.setString(1, id_produto);
                 stmt3.setFloat(2, Float.parseFloat((String) tabela.getValueAt(i, 6)));        
                 stmt3.setInt(3, Integer.parseInt((String) tabela.getValueAt(i, 0))*Integer.parseInt((String) tabela.getValueAt(i, 1))); 
                 stmt3.setString(4, data);
                 stmt3.setInt(5, Integer.parseInt(id_centro_estoque));
                 stmt3.setInt(6, Integer.parseInt(id_compra));

                 if(!stmt3.execute()){
                     stmt3.close();
                     con.close();
                 }
                 else{
                     stmt3.execute();
                     stmt3.close();
                     con.close();
                     JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");
                 }
            }
        } catch (SQLException u) {    
            throw new RuntimeException(u);    
        } 
       
       try {
           String val="";
           for(int i=0;i<tabela.getRowCount();i++){
                id_produto = retornaId_Produtos_da_Compra((String) tabela.getValueAt(i, 2));
                sql = "SELECT id_produto from produtos_centro_estoque where  id_produto='"+id_produto+"' and id_centro_estoque='"+id_centro_estoque+"'";
                PreparedStatement stmt10 = banco.getConexaoMySQL().prepareStatement(sql);  

                ResultSet rs10= stmt10.executeQuery();  

                while(rs10.next())
                {
                   val = rs10.getString(1);
                }
                if(val.equals("")){
                    JOptionPane.showMessageDialog(null, "ENTROU AQUI!!");
                sql = "INSERT INTO produtos_centro_estoque (id_produto, id_centro_estoque, quantidade_em_unidade)"+
                 "VALUES(?,?,?)";  
                 Connection con = banco.getConexaoMySQL();
                 PreparedStatement stmt11 = con.prepareStatement(sql);    
                 stmt11.setString(1, id_produto);
                 stmt11.setString(2, id_centro_estoque);        
                 valor_unidade = Integer.parseInt((String) tabela.getValueAt(i, 0))*Integer.parseInt((String) tabela.getValueAt(i, 1));
                 stmt11.setInt(3, valor_unidade); 
                 if(!stmt11.execute()){
                     stmt11.close();
                     con.close();
                 }
                 else{
                     stmt11.execute();
                     stmt11.close();
                     con.close();
                     JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");
                 }
                }else{
                valor_unidade = Integer.parseInt((String) tabela.getValueAt(i, 0))*Integer.parseInt((String) tabela.getValueAt(i, 1));    
                sql = "Update produtos_centro_estoque set quantidade_em_unidade='"+valor_unidade+"'+quantidade_em_unidade where id_produto='"+id_produto+"' and id_centro_estoque = '"+id_centro_estoque+"'";
                Connection con = banco.getConexaoMySQL();
                PreparedStatement stmt20 = con.prepareStatement(sql);    
                
    
                stmt20.executeUpdate();
                stmt20.close();
                con.close();
                
                }
                rs10.close();  
                stmt10.close();
                banco.FecharConexao();
           }
       } catch (SQLException u) {    
            throw new RuntimeException(u);    
        } 
        
    }
    
    public String retornaId_Produtos_da_Compra(String produto){
        String valor = "0";
        try{
            
            String sql = "SELECT id_produto from produtos where excluido = 0 and descricao ='"+produto+"'";
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            {
                valor = rs.getString(1);
            }
            
            rs.close();  
            stmt.close();
            banco.FecharConexao();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return valor;
    }
    
    public String retorna_cod_fornecedor(String nome_fantasia){
        String codigo = "0";
        try{
            
            String sql = "SELECT id_fornecedor from fornecedores where nome_fantasia = '"+nome_fantasia+"' and excluido = 0";
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            {
                codigo = rs.getString(1);
            }
            
            rs.close();  
            stmt.close();
            banco.FecharConexao();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return codigo;
    }

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
