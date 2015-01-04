
package barsystems.usuarios;

import barsystems.conexaoBanco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;

public class Class_Usuarios {
    
    protected Class_Conexao_Banco banco = new Class_Conexao_Banco();
    protected String id_usuario, nome, tipo, senha;
    
    public Class_Usuarios() {
        
    }
    
    public Class_Usuarios(String id_usuario, String nome, String tipo, String senha) {
        this.id_usuario = id_usuario;
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
    
}
