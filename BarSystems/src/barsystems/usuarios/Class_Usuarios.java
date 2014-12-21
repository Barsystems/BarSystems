
package barsystems.usuarios;

import barsystems.conexaoBanco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;

public class Class_Usuarios {
    
    protected Class_Conexao_Banco banco = new Class_Conexao_Banco();
    protected Connection con = banco.getConexaoMySQL();
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
    
    public DefaultListModel carregaUsuarios() {
        
        DefaultListModel lista = new DefaultListModel();
        
        try 
        {
            String sql = "SELECT nome from usuarios WHERE excluido = 0";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lista.addElement(rs.getString(1));
            }
            
            rs.close();
            st.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            e.getMessage();
        }
        
        return lista;
    }
    
}
