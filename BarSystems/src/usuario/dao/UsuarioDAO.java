
package usuario.dao;

import conexao_banco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import usuario.classe.UsuarioClasse;

public class UsuarioDAO implements IUsuarioDAO {
    
    Class_Conexao_Banco banco = new Class_Conexao_Banco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO usuario (nome, tipo, senha) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET nome = ?, tipo = ?, senha = ? WHERE id_usuario = ?";
    private static final String SQL_REMOVE = "UPDATE usuario SET excluido = 1 WHERE id_usuario = ?";
    private static final String SQL_FIND_ALL = "SELECT id_usuario, nome, senha, tipo FROM usuario WHERE excluido = 0 AND nome LIKE ? ORDER BY nome";
    private static final String SQL_VALIDA_USUARIO = "SELECT nome, senha FROM usuario WHERE nome = ? AND senha = ?";
    private static final String SQL_GET_USUARIO = "SELECT id_usuario, nome, senha, tipo FROM usuario WHERE excluido = 0 AND nome = ?";
    private static final String SQL_VERIFICA_USUARIO_REPETIDO = "SELECT nome FROM usuario WHERE nome = ? AND excluido = 0";
    
    /*
    public void carregaUsuariosNormaisComboBox(javax.swing.JComboBox combo_usuario) {
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (!rs.getString(2).equals("Administrador")) {
                    combo_usuario.addItem(rs.getString(1));
                }
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    
    @Override
    public List<UsuarioClasse> findAll(String pesquisa) {
        List<UsuarioClasse> users = new ArrayList<UsuarioClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ps.setString(1, "%"+pesquisa+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                UsuarioClasse user = new UsuarioClasse();
                user.setId(rs.getLong("id_usuario"));
                user.setNome(rs.getString("nome"));
                user.setSenha(rs.getString("senha"));
                user.setTipo(rs.getString("tipo"));
                users.add(user);
            }
            rs.close();
            ps.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        
        return users;
    }
    
    @Override
    public boolean validaUsuario(String usuario, String senha) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VALIDA_USUARIO);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            rs = ps.executeQuery();
            if (rs.next()) { 
                flag = true;
            } 
            rs.close();
            ps.close();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return flag;
    }
    
    @Override
    public UsuarioClasse getUsuario(String usuario) {
        UsuarioClasse user = new UsuarioClasse();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_GET_USUARIO);
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            if (rs.next()) { 
                user.setId(rs.getLong("id_usuario"));
                user.setNome(rs.getString("nome"));
                user.setSenha(rs.getString("senha"));
                user.setTipo(rs.getString("tipo"));
            } 
            rs.close();
            ps.close();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return user;
    }
    
    @Override
    public boolean verificaUsuarioRepetido(String nome) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_USUARIO_REPETIDO);
            ps.setString(1, nome);
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    @Override
    public int save(UsuarioClasse user) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setString(1, user.getNome());
            ps.setString(2, user.getTipo());
            ps.setString(3, user.getSenha());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /*
    public int getIdUsuario(String nome_usuario) {
        int id_usuario = 0;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            String query = "select id_usuario from usuarios where nome = '"+nome_usuario+"' and excluido = 0";
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
    }*/
    
    /*
    public String getTipoUsuario(String nome) {
        String tipo = null;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            String query = "select tipo from usuarios where nome = '"+nome+"' and excluido = 0";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tipo = rs.getString(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipo;
    }*/
    
    /*
    public boolean verificaSenhaAdministrador(String senha) {
        boolean flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            String query = "select senha from usuarios where nome = 'Administrador' and senha = '"+senha+"' "
                    + "and excluido = 0";
            PreparedStatement ps = con.prepareStatement(query);
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
    }*/
    
    /*
    public boolean pedeSenhaAdministrador(String nome_usuario) {
        boolean flag = false;
        if (getTipoUsuario(nome_usuario).equals("Administrador")) {
            String senha = JOptionPane.showInputDialog(null, "Digite a senha do administrador!", "Atenção", JOptionPane.PLAIN_MESSAGE);
            if (verificaSenhaAdministrador(senha) == true) {
                flag = true;
            }
        }
        return flag;
    }*/
    
    @Override
    public int update(UsuarioClasse user) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, user.getNome());
            ps.setString(2, user.getTipo());
            ps.setString(3, user.getSenha());
            ps.setLong(4, user.getId());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public int remove(Long id) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_REMOVE);
            ps.setLong(1, id);
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /*
    public void excluiResponsavelCaixa(int id_usuario) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            String query = "DELETE FROM responsaveis_caixa WHERE id_usuario = '"+id_usuario+"'";
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    
}
