/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barsystems.produtos;
import barsystems.Class_Troca_Virgula_Por_Ponto;
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
    protected DecimalFormat dffloat = new DecimalFormat("##,###.00");
    
    protected String descricao, tipo, valor_compra_unidade,valor_unidade_venda, codigo;
    
    public Class_produtos(){
        
    }
    
    public Class_produtos(String codigo, String descricao, String tipo, String valor_compra, String valor_venda){
        this.codigo = codigo;
        this.descricao = descricao;
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
        
        String sql = "INSERT INTO produtos(id_produto, descricao, tipo,excluido,valor_compra_unidade,valor_venda_unidade)"+
                "VALUES(?,?,?,?,?,?)";    
    
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
        try {    
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);    
            stmt.setInt(1, Integer.parseInt(codigo));
            stmt.setString(2, descricao);        
            stmt.setString(3, tipo); 
            stmt.setBoolean(4, false);
            stmt.setFloat(5, troca.trocaVirgulaPorPonto(valor_compra_unidade));
            stmt.setFloat(6, troca.trocaVirgulaPorPonto(valor_unidade_venda));

            if(!stmt.execute()){
                stmt.close();
                banco.FecharConexao();
                return false;
            }
            else{
                stmt.execute();
                stmt.close();
                banco.FecharConexao();
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
     * @param tipo
     * @param valor_compra
     * @param valor_venda
     * @return true se conseguiu atualizar o valor do produto
     */
    public boolean edita(
            String codigo, 
            String descricao,  
            String tipo, 
            String valor_compra, 
            String valor_venda) {
        
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
        
        String sql = "Update produtos set descricao='"+descricao+
                "',tipo='"+tipo+"',valor_compra_unidade="+
                troca.trocaVirgulaPorPonto(valor_compra)+
                ",valor_venda_unidade="+troca.trocaVirgulaPorPonto(valor_venda)+" WHERE id_produto = "+codigo;
    
            try {    
                PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);    
                
    
                stmt.executeUpdate();
                    stmt.close();
                    banco.FecharConexao();
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
                PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);    
                if(!stmt.execute()){
                    stmt.close();
                    banco.FecharConexao();
                    return false;
                }
                else{
                    stmt.execute();
                    stmt.close();
                    banco.FecharConexao();
                    JOptionPane.showMessageDialog(null,"Produto excluído com sucesso!");
                    return true;
                }
            } catch (SQLException u) {    
                throw new RuntimeException(u);    
        } 
    }
    
    public boolean excluiDefinitivo(String codigo){
        String sql = "DELETE FROM produtos where id_produto = '"+codigo+"'";    
    
            try {    
                PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);    
                if(!stmt.execute()){
                    stmt.close();
                    banco.FecharConexao();
                    return false;
                }
                else{
                    stmt.execute();
                    stmt.close();
                    banco.FecharConexao();
                    JOptionPane.showMessageDialog(null,"Produto excluído com sucesso!");
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
           PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
           ResultSet rs = stmt.executeQuery();  
              
            while(rs.next()){  
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
     * CarregaProduto
     * Retorna o prduto selecionado na Jlist do formulario Painel_Produtos!
     * OBS: NAO USAR SE NAO ESTIVER COM JList SELECIONADO!
     * @param nome 
     */
    public void carregaProduto(String nome){
        try{
            
           String sql = "SELECT id_produto, descricao, tipo, valor_compra_unidade, valor_venda_unidade FROM produtos where excluido = 0 and descricao = '"+nome+"'";  
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
   
            ResultSet rs = stmt.executeQuery();              
            while(rs.next()){
                
                String valorCompra = dffloat.format(rs.getFloat(4));
                if (valorCompra.equals(",00")) {
                    valorCompra = "0,00";
                }
                String valorVenda = dffloat.format(rs.getFloat(5));
                if (valorVenda.equals(",00")) {
                    valorVenda = "0,00";
                }
                this.codigo = rs.getString(1);
                this.descricao = rs.getString(2);
                this.tipo = rs.getString(3);
                this.valor_compra_unidade = valorCompra;
                this.valor_unidade_venda = valorVenda;
            }              
            rs.close();  
            stmt.close();
            banco.FecharConexao();
        
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    } // FIM CARREGA PRODUTOS
    
    public DefaultListModel pesquisa(String pesquisa){
        DefaultListModel listModel = new DefaultListModel();
        
        //ListModel lista = new ListModel();
        try{
            
           String sql = "SELECT descricao FROM produtos where excluido = 0 and descricao like'%"+pesquisa+"%'";  
           PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  
           
           ResultSet rs = stmt.executeQuery();  
              
            while(rs.next()){  
               listModel.addElement(rs.getString(1));
            }              
            rs.close();  
            stmt.close();
            banco.FecharConexao();
        
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return listModel;
    }// FIM PESQUISA
    
    //esta clase vai verificar se já existe um produto cadastrado com o mesmo codigo
    public boolean verificaCodigoProdutosAtivos(String Codigo) {
        
        boolean flag = false;
        try{

           String sql = "SELECT id_produto FROM produtos where excluido = 0 and id_produto = '"+Codigo+"'";  
           PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  

           ResultSet rs = stmt.executeQuery();  

            while(rs.next()){  
               flag = true;
            }
            rs.close();  
            stmt.close();
            banco.FecharConexao();


        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return flag;
    }
    
    //esta clase vai verificar se já existe um produto cadastrado com o mesmo codigo mas que ja tenha sido excluido
    public boolean verificaCodigoProdutosExcluidos(String Codigo) {
        
        boolean flag = false;
        try{

           String sql = "SELECT id_produto FROM produtos where excluido = 1 and id_produto = '"+Codigo+"'";  
           PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  

           ResultSet rs = stmt.executeQuery();  

            while(rs.next()){  
               flag = true;
            }
            rs.close();  
            stmt.close();
            banco.FecharConexao();


        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return flag;
    }
    
    //esta clase vai verificar se já existe um produto cadastrado com o mesmo nome
    public boolean verificaNomeProdutos(String nome) {
        boolean flag = false;
        try{

            String sql = "SELECT descricao FROM produtos where excluido = 0 and descricao = '"+nome+"'";  
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  

            ResultSet rs = stmt.executeQuery();  

            while(rs.next()){  
               flag = true;
            }
            rs.close();  
            stmt.close();
            banco.FecharConexao();


        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return flag;
    }
    
    public int retornaMaiorIdProduto() {
        
        int idProduto = 0;
        
        try
        {
            String sql = "SELECT MAX(id_produto) as maiorCodigo FROM produtos";  
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);  

            ResultSet rs = stmt.executeQuery();  

            if(rs.next()){  
               idProduto = rs.getInt(1);
            }
            rs.close();  
            stmt.close();
            banco.FecharConexao();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getMessage();
        }
        
        return idProduto;
    }
    
}
