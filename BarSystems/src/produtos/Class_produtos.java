
package produtos;

import principal.Class_Troca_Virgula_Por_Ponto;
import conexao_banco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Class_produtos {
    
    protected String descricao, tipo, valor_unidade_venda, codigo, porcentagem, valor_compra;
    
    public Class_produtos() {
        
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public String getValor_Compra() {
        return this.valor_compra;
    }
    
    public String getPorcentagem() {
        return this.porcentagem;
    }
    
    public String getValor_Venda() {
        return valor_unidade_venda;
    }
    
    public String retornaProdutoPesquisa(int id_produto, String descricao) {
        String produto = "";
        try {
            String sql = "";
            if (id_produto > 0) {
                sql = "SELECT descricao FROM produtos WHERE excluido = 0 AND id_produto = '"+id_produto+"'";
            } else {
                sql = "SELECT descricao FROM produtos WHERE excluido = 0 AND descricao LIKE '"+descricao+"%'";
            }
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement stmt = conn.prepareStatement(sql);  
            ResultSet rs = stmt.executeQuery();  
            if (rs.next())
            {
                produto = rs.getString(1);
            }
            rs.close();  
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produto;
    }
    
    public void carregaProdutosComboBox(JComboBox combo) {
        combo.removeAllItems();
        combo.addItem("<Selecione>");
        try {
            String sql = "SELECT produtos.descricao FROM produtos WHERE produtos.excluido = 0";
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
    
    public int retornaIdProduto(String produto) {
        int codigo = 0;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT id_produto FROM produtos WHERE descricao = '"+produto+"'");  
           
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
    
    /** Cadastra
     *  Realiza cadastro de produtos no banco de dados
     * @param id_produto
     * @param descricao
     * @param id_setor
     * @param valor_venda
     */
    public void Cadastra(int id_produto, String descricao, int id_setor, float porcentagem_venda) {
        
        String sql = "INSERT INTO produtos(id_produto, descricao, id_setor, valor_venda_unidade)"+
                "VALUES(?, ?, ?, ?)";    
        
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement(sql);    
            ps.setInt(1, id_produto);
            ps.setString(2, descricao);        
            ps.setInt(3, id_setor); 
            ps.setFloat(4, porcentagem_venda);
            ps.execute();
            
            ps.close();
            con.close();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e) {    
            e.printStackTrace();
        } 
    } // Fim Cadastra
    
    /**
     * Edita dados de produtos
     * @param codigo
     * @param descricao
     * @param id_setor
     * @param porcentagem
     * @param valor_venda
     */
    public void edita(int codigo, String descricao, int id_setor, float porcentagem, float valor_venda) {
        
        String sql = "UPDATE produtos SET "
                + "descricao = '"+descricao+"', "
                + "id_setor = '"+id_setor+"', "
                + "porcentagem_lucro = '"+porcentagem+"', "
                + "valor_venda_unidade = "+valor_venda+" WHERE id_produto = "+codigo;
    
            try {
                Class_Conexao_Banco banco = new Class_Conexao_Banco();
                Connection con = banco.getConexaoMySQL();
                PreparedStatement ps = con.prepareStatement(sql);    
                ps.executeUpdate();
                
                ps.close();
                con.close();
                
                JOptionPane.showMessageDialog(null,"Produto editado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (SQLException e) {    
                e.printStackTrace();  
        } 
    }
    
    /**
     * Exclui produto com codigo do parametro recebido
     * @param codigo
     */
    
    public void exclui(int codigo) {
        String sql = "UPDATE produtos SET excluido = 1 WHERE id_produto = '"+codigo+"'";    
    
            try {    
                Class_Conexao_Banco banco = new Class_Conexao_Banco();
                Connection con = banco.getConexaoMySQL();
                PreparedStatement ps = con.prepareStatement(sql);    
                ps.execute();
                
                ps.close();
                con.close();

                JOptionPane.showMessageDialog(null,"Produto excluído com sucesso!");
            } catch (Exception e) {    
                e.printStackTrace();
        } 
    }
    
    public boolean excluiDefinitivo(String codigo){
        String sql = "DELETE FROM produtos where id_produto = '"+codigo+"'";    
    
            try {    
                Class_Conexao_Banco banco = new Class_Conexao_Banco();
                Connection con = banco.getConexaoMySQL();
                PreparedStatement stmt = con.prepareStatement(sql);    
                if(!stmt.execute()){
                    stmt.close();
                    con.close();
                    return false;
                }
                else{
                    stmt.execute();
                    stmt.close();
                    con.close();
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
    public DefaultListModel carregaLista() {
        
        DefaultListModel listModel = new DefaultListModel();
        
        try {
           String sql = "SELECT descricao FROM produtos WHERE excluido = 0 ORDER BY descricao";  
           Class_Conexao_Banco banco = new Class_Conexao_Banco();
           Connection con = banco.getConexaoMySQL();
           PreparedStatement ps = con.prepareStatement(sql);    
           
           ResultSet rs = ps.executeQuery();  
              
            while(rs.next()){  
               listModel.addElement(rs.getString(1));
            }
            
            rs.close();  
            ps.close();
            con.close();
        
        } catch(Exception e) {
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
            
            NumberFormat z = NumberFormat.getCurrencyInstance();
            
            String sql = "SELECT produtos.*, setores_produtos.nome "
                    + "FROM produtos "
                    + "INNER JOIN setores_produtos ON produtos.id_setor = setores_produtos.id_setor "
                    + "WHERE produtos.excluido = 0 and produtos.descricao = '"+nome+"'";  
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement(sql);    
   
            ResultSet rs = ps.executeQuery();              
            while(rs.next()) {                
                this.codigo = rs.getString("produtos.id_produto");
                this.descricao = rs.getString("produtos.descricao");
                this.tipo = rs.getString("setores_produtos.nome");
                this.valor_compra = z.format(rs.getFloat("produtos.valor_compra"));
                this.porcentagem = rs.getString("porcentagem_lucro") + " %";
                this.valor_unidade_venda = z.format(rs.getFloat("produtos.valor_venda_unidade"));
            }              
            rs.close();  
            ps.close();
            con.close();
          
        } catch(Exception e) {
            e.printStackTrace();
        }
    } // FIM CARREGA PRODUTOS
    
    public DefaultListModel pesquisa(String pesquisa){
        DefaultListModel listModel = new DefaultListModel();
        
        //ListModel lista = new ListModel();
        try{
            
           String sql = "SELECT descricao FROM produtos where excluido = 0 and descricao like'%"+pesquisa+"%'";  
           Class_Conexao_Banco banco = new Class_Conexao_Banco();
           Connection con = banco.getConexaoMySQL();
                PreparedStatement stmt = con.prepareStatement(sql);   
           
           ResultSet rs = stmt.executeQuery();  
              
            while(rs.next()){  
               listModel.addElement(rs.getString(1));
            }              
            rs.close();  
            stmt.close();
            con.close();
        
            
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
           Class_Conexao_Banco banco = new Class_Conexao_Banco();
           Connection con = banco.getConexaoMySQL();
                PreparedStatement stmt = con.prepareStatement(sql);     

           ResultSet rs = stmt.executeQuery();  

            while(rs.next()){  
               flag = true;
            }
            rs.close();  
            stmt.close();
            con.close();


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
           Class_Conexao_Banco banco = new Class_Conexao_Banco();
           Connection con = banco.getConexaoMySQL();
                PreparedStatement stmt = con.prepareStatement(sql);     

           ResultSet rs = stmt.executeQuery();  

            while(rs.next()){  
               flag = true;
            }
            rs.close();  
            stmt.close();
            con.close();


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
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
                PreparedStatement stmt = con.prepareStatement(sql);   

            ResultSet rs = stmt.executeQuery();  

            while(rs.next()){  
               flag = true;
            }
            rs.close();  
            stmt.close();
            con.close();


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
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
                PreparedStatement stmt = con.prepareStatement(sql);   

            ResultSet rs = stmt.executeQuery();  

            if(rs.next()){  
               idProduto = rs.getInt(1);
            }
            rs.close();  
            stmt.close();
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getMessage();
        }
        
        return idProduto;
    }
    
    public void carregaSetoresComboBox(JComboBox combo) {
        combo.removeAllItems();
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("SELECT nome from setores_produtos "
                    + "WHERE excluido = 0");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString (1));
            }
            rs.close();
            ps.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int retornaIdSetor(String nome) {
        int id = 0;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("SELECT id_setor FROM setores_produtos "
                    + "WHERE nome = '"+nome+"' AND excluido = 0");
            ResultSet rs = ps.executeQuery();
            rs.next();
            id = rs.getInt(1);
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public DefaultListModel carregaSetores(String setor) {
        DefaultListModel list = new DefaultListModel();
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("SELECT nome FROM setores_produtos WHERE excluido = 0 "
                    + "AND nome != 'Sem categoria' AND nome LIKE '%"+setor+"%'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.addElement(rs.getString(1));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void cadastraSetor(String nome) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("INSERT INTO setores_produtos (nome) VALUES (?)");
            ps.setString(1, nome);
            ps.executeUpdate();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Setor cadastrado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editaSetor(String nome, String novo_nome) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("UPDATE setores_produtos SET "
                    + "nome = '"+novo_nome+"' WHERE nome = '"+nome+"'");
            ps.executeUpdate();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Setor editado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean verificaNomeRepetidoSetor(String nome) {
        boolean flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("SELECT nome FROM setores_produtos "
                    + "WHERE nome = '"+nome+"'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    public boolean verificaProdutoVinculadoAoSetor(int id_setor) {
        boolean flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("SELECT id_setor FROM produtos "
                    + "WHERE id_setor = '"+id_setor+"' and excluido = 0");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    public void excluiSetor(int id_setor) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("UPDATE setores_produtos SET "
                    + "excluido = 1 WHERE id_setor = '"+id_setor+"'");
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean verificaSetorPadrao(String nome) {
        boolean flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("SELECT padrao FROM setores_produtos "
                    + "WHERE nome = '"+nome+"' AND padrao = 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    public void AtualizaValorCompraProduto(int id_produto, float valor_compra_unidade) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            
            float valor = 0;
            
            //PRIMEIRO VAMOS VERIFICAR SE O VALOR ATUAL É MAIOR QUE O VALOR ANTERIOR
            PreparedStatement ps = conn.prepareStatement("SELECT valor_compra FROM produtos "
                    + "WHERE id_produto = '"+id_produto+"'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                valor = rs.getFloat(1);
            }
            
            //SE O NOVO VALOR FOR MAIOR QUE O VALOR ANTERIOR, ENTÃO PROSSEGUIMOS
            if (valor_compra_unidade > valor) {
                //ATUALIZAMOS O VALOR DE COMPRA DESTE PRODUTO, E TAMBÉM ATUALIZAMOS O VALOR DE VENDA
                ps = conn.prepareStatement("UPDATE produtos SET "
                        + "valor_compra = '"+valor_compra_unidade+"', "
                        + "valor_venda_unidade = ((valor_compra * porcentagem_lucro) / 100) + valor_compra "
                        + "WHERE id_produto = '"+id_produto+"'");
                ps.executeUpdate();
            }
            
            ps.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
