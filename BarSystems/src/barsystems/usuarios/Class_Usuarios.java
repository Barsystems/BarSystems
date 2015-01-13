
package barsystems.usuarios;

import barsystems.conexaoBanco.Class_Conexao_Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Class_Usuarios {
    
    protected Class_Conexao_Banco banco = new Class_Conexao_Banco();
    protected String id_usuario, nome, tipo, senha;
    
    public Class_Usuarios() {
        
    }
    
    public Class_Usuarios(String nome, String tipo, String senha) {
        this.nome = nome;
        this.tipo = tipo;
        this.senha = senha;
    }
    
    public String getIdUsuario() {
        return id_usuario;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public DefaultListModel carregaLista() {
        
        DefaultListModel lista = new DefaultListModel();
        
        try 
        {
            String sql = "SELECT nome from usuarios WHERE excluido = 0 order by nome";
            PreparedStatement st = banco.getConexaoMySQL().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) 
            {
                lista.addElement(rs.getString(1));
            }
            
            rs.close();
            st.close();
            banco.FecharConexao();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            e.getMessage();
        }
        
        return lista;
    }
    
    public void carregaUsuario(String nome) {
        
        try 
        {
            String SQL = "select id_usuario, nome, senha, tipo from usuarios where nome = '"+nome+"'";
            PreparedStatement st = banco.getConexaoMySQL().prepareStatement(SQL);
            ResultSet rs = st.executeQuery();
            while(rs.next()) 
            {
                this.id_usuario = rs.getString(1);
                this.nome = rs.getString(2);
                this.senha = rs.getString(3);
                this.tipo = rs.getString(4);
            }
            
            rs.close();
            st.close();
            banco.FecharConexao();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
    public boolean cadastra(String nome, String senha, String tipo) {
        
        String sql = "INSERT INTO usuarios(nome, senha, tipo) VALUES(?,?,?)";    
    
        try {    
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement(sql);    
            stmt.setString(1, nome);
            stmt.setString(2, senha);    
            stmt.setString(3, tipo);    

            if(!stmt.execute()){
                stmt.close();
                banco.FecharConexao();
                return false;
            }
            else{
                stmt.execute();
                stmt.close();
                banco.FecharConexao();
                JOptionPane.showMessageDialog(null,"Usuário cadastrado com sucesso!");
                return true;
            }
        } catch (SQLException u) {    
            throw new RuntimeException(u);
        }
        
    }
    
}
