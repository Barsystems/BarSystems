
package barsystems.usuarios;

import barsystems.conexaoBanco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Class_Usuarios {
    
    Class_Conexao_Banco banco = new Class_Conexao_Banco();
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
            Connection con = banco.getConexaoMySQL();
            String sql = "SELECT nome from usuarios WHERE excluido = 0 order by nome";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) 
            {
                lista.addElement(rs.getString(1));
            }
            
            rs.close();
            st.close();
            con.close();
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
            Connection con = banco.getConexaoMySQL();
            String SQL = "select id_usuario, nome, senha, tipo from usuarios where nome = '"+nome+"'";
            PreparedStatement st = con.prepareStatement(SQL);
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
            con.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
    public boolean cadastra(String nome, String senha, String tipo) {
        
        String sql = "INSERT INTO usuarios(nome, senha, tipo) VALUES(?,?,?)";    
    
        try {    
            Connection con = banco.getConexaoMySQL();
            PreparedStatement stmt = con.prepareStatement(sql);    
            stmt.setString(1, nome);
            stmt.setString(2, senha);    
            stmt.setString(3, tipo);    

            if(!stmt.execute()){
                stmt.close();
                con.close();
                return false;
            }
            else{
                stmt.execute();
                stmt.close();
                con.close();
                JOptionPane.showMessageDialog(null,"Usuário cadastrado com sucesso!");
                return true;
            }
        } catch (SQLException u) {    
            throw new RuntimeException(u);
        }
        
    }
    
    public int getIdUsuario(String nome_usuario) {
        int id_usuario = 0;
        try {
            Connection con = banco.getConexaoMySQL();
            String query = "select id_usuario from usuarios where nome = '"+nome_usuario+"'";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id_usuario = rs.getInt("id_usuario");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id_usuario;
    }
    
}
