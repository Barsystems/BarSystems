/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compras;

import principal.Class_Troca_Virgula_Por_Ponto;
import conexao_banco.Class_Conexao_Banco;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    + "and compras.id_fornecedor = fornecedores.id_fornecedor "
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
            
            String sql = "SELECT nome from setores_produtos where excluido = 0";
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
            
            String sql = "select produtos.descricao from produtos inner join setores_produtos on produtos.id_setor = setores_produtos.id_setor where produtos.excluido = 0 and setores_produtos.nome = '"+tipo+"'";
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
    
     public List carrega_Formas_pagamento(){
        List lista = new List();
        try{
            
            String sql = "SELECT descricao from formas_pagamento where excluido = 0";
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
     
     public List carrega_Centros_estoque(){
        List lista = new List();
        try{
            
            String sql = "SELECT nome from centros_estoque where excluido = 0";
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
     
      public String getCodProduto(String produto){
        String id_produto = "";
         try{
            
            String sql = "SELECT id_produto from produtos where excluido = 0 and descricao='"+produto+"'";
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            {
                id_produto = rs.getString(1);
            }
            
            rs.close();  
            stmt.close();
            banco.FecharConexao();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return id_produto;
    }// FIM CARREGA LISTA
      
      public String getCodForma_pagamento(String forma){
        String forma_p = "";
         try{
            
            String sql = "SELECT id_forma_pagamento from formas_pagamento where excluido = 0 and descricao='"+forma+"'";
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            {
                forma_p = rs.getString(1);
            }
            
            rs.close();  
            stmt.close();
            banco.FecharConexao();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return forma_p;
    }// FIM CARREGA LISTA
      
      public String getCodCentro(String centro){
        String id_produto = "";
         try{
            
            String sql = "SELECT id_centro_estoque from centros_estoque where excluido = 0 and nome='"+centro+"'";
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            {
                id_produto = rs.getString(1);
            }
            
            rs.close();  
            stmt.close();
            banco.FecharConexao();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return id_produto;
    }// FIM CARREGA LISTA
      
      public String getCodFornecedor(String fornecedor){
        String id_fornecedor = "";
         try{
            String sql = "SELECT id_fornecedor from fornecedores where excluido = 0 and nome_fantasia='"+fornecedor+"'";
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            {
                id_fornecedor = rs.getString(1);
            }
            
            rs.close();  
            stmt.close();
            banco.FecharConexao();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return id_fornecedor;
    }// FIM CARREGA 
      
      public String getCodUltimaCompra(){
        String id_compra = "";
         try{
            String sql = "SELECT MAX(id_compra) from compras where excluido = 0";
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            {
                id_compra = rs.getString(1);
            }
            
            rs.close();  
            stmt.close();
            banco.FecharConexao();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return id_compra;
    }// FIM CARREGA LISTA
    
    public boolean verfica_se_produto_ja_existe(String id_produto, String id_centro){
        boolean verifica = false;
        String id_teste = "";
         try{
            String sql = "SELECT id_produto from produtos_centro_estoque where id_produto='"+id_produto+"' and id_centro_estoque = '"+id_centro+"'";
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
            ResultSet rs = stmt.executeQuery();  
            
            while(rs.next())
            
                id_teste = rs.getString(1);
            
            rs.close();
            banco.FecharConexao();
            
            if(!id_teste.equals(""))
                return true;
            else
                return false;
                        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return verifica;
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
                                DefaultTableModel tabela_produtos,
                                String forma_pagamento,
                                TableModel tabela_parcelas
                                ){
        
        String sql = "INSERT INTO compras (descricao, id_fornecedor, numero_nota, data, valor, parcelas, id_forma_pagamento) VALUES (?, ?, ?, ?, ?, ?, ?)";    
    
            try {    
                Class_Conexao_Banco banco = new Class_Conexao_Banco();
                Connection conn = banco.getConexaoMySQL();
                PreparedStatement ps = conn.prepareStatement(sql);    
                ps.setString(1, descricao);
                ps.setInt(2, Integer.parseInt(getCodFornecedor(cod_fornecedor)));    
                ps.setString(3, numero_nota);    
                ps.setString(4, data);
                ps.setString(5, valor);
                ps.setString(6, parcelas);
                ps.setString(7, getCodForma_pagamento(forma_pagamento));
                ps.execute();
                
                /**** ARRRUMAAARRR AQUI!! */
                sql = "INSERT INTO pagamentos_compra (id_compra, parcela, valor, data, liquidada) VALUES (?, ?, ?, ?, ?)";
                for(int i = 0;i<tabela_parcelas.getRowCount();i++){
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, getCodUltimaCompra());
                    ps.setInt(2, (int) tabela_parcelas.getValueAt(i, 0));    
                    ps.setFloat(3, (float) tabela_parcelas.getValueAt(i,1));    
                    ps.setString(4, (String)tabela_parcelas.getValueAt(i, 3));
                    ps.setBoolean(5,(boolean)tabela_parcelas.getValueAt(i, 2) );
                    ps.execute();
                }
                /***** TERMINARR!!! ****/
                
                sql = "INSERT INTO produtos_compra (id_produtos, valor_compra, quantidade_em_unidade, id_centro_estoque, id_compras_fk) VALUES (?, ?, ?, ?, ?)";
                
                for(int i = 0;i<tabela_produtos.getRowCount();i++){
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, getCodProduto((String) tabela_produtos.getValueAt(i, 0)));
                    ps.setFloat(2, (float) tabela_produtos.getValueAt(i, 3));    
                    ps.setString(3, (String) tabela_produtos.getValueAt(i, 1));    
                    ps.setString(4, getCodCentro((String) tabela_produtos.getValueAt(i, 4)));
                    ps.setString(5, getCodUltimaCompra());
                    ps.execute();
                }
                 
                String sql_insert = "INSERT INTO produtos_centro_estoque (id_produto, id_centro_estoque, quantidade_em_unidade ) VALUES (?, ?, ?)";
                String sql_update = "UPDATE produtos_centro_estoque SET quantidade_em_unidade = (?+ quantidade_em_unidade) WHERE id_produto = ? and id_centro_estoque = ?";
                
                for(int i = 0;i<tabela_produtos.getRowCount();i++){
                    if(verfica_se_produto_ja_existe(getCodProduto((String) tabela_produtos.getValueAt(i, 0)), getCodCentro((String) tabela_produtos.getValueAt(i, 4)))){
                        ps = conn.prepareStatement(sql_update);
                        ps.setString(1, (String) tabela_produtos.getValueAt(i, 1));
                        ps.setString(2, getCodProduto((String) tabela_produtos.getValueAt(i, 0)));
                        ps.setString(3, getCodCentro((String) tabela_produtos.getValueAt(i, 4))); 
                        
                        ps.execute();
                    } else{
                        ps = conn.prepareStatement(sql_insert);
                        ps.setString(1, getCodProduto((String) tabela_produtos.getValueAt(i, 0)));
                        ps.setString(2, getCodCentro((String) tabela_produtos.getValueAt(i, 4))); 
                        ps.setString(3, (String) tabela_produtos.getValueAt(i, 1));
                        ps.execute();
                    }
                }
                
                ps.close();
                conn.close();
                JOptionPane.showMessageDialog(null,"Compra cadastrada com sucesso");
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
