
package usuario.dao;

import conexao_banco.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import usuario.classe.UsuarioClasse;

public class UsuarioDAO implements IUsuarioDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
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
    private static final String SQL_FIND_USUARIO_RESPONSAVEL_CENTRO_CUSTO = "SELECT usuario.id_usuario, usuario.nome, "
            + "usuario.tipo, usuario.senha, centro_custo.id_centro_custo "
            + "FROM centro_custo_responsavel "
            + "INNER JOIN centro_custo ON centro_custo_responsavel.id_centro_custo_fk = centro_custo.id_centro_custo "
            + "INNER JOIN usuario ON centro_custo_responsavel.id_usuario_fk = usuario.id_usuario "
            + "WHERE centro_custo.id_centro_custo = ? AND usuario.excluido = 0 AND centro_custo.excluido = 0 "
            + "ORDER BY usuario.tipo, usuario.tipo";
    private static final String SQL_ADD_RESPONSAVEL_CENTRO_CUSTO = "INSERT INTO centro_custo_responsavel "
            + "(id_centro_custo_fk, id_usuario_fk) VALUES (?, ?)";
    private static final String SQL_REMOVE_RESPONSAVEL_CENTRO_CUSTO = "DELETE FROM centro_custo_responsavel "
            + "WHERE id_centro_custo_fk = ? AND id_usuario_fk = ?";
    
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
    
    @Override
    public List<UsuarioClasse> findUsuarioResponsavelCentroCusto(Long id_centro_custo) {
        List<UsuarioClasse> users = new ArrayList<UsuarioClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_USUARIO_RESPONSAVEL_CENTRO_CUSTO);
            ps.setLong(1, id_centro_custo);
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
    public int addResponsavelCentroCusto(Long id_centro_custo, Long id_usuario) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_ADD_RESPONSAVEL_CENTRO_CUSTO);
            ps.setLong(1, id_centro_custo);
            ps.setLong(2, id_usuario);
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public int removeResponsavelCentroCusto(Long id_centro_custo, Long id_usuario) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_REMOVE_RESPONSAVEL_CENTRO_CUSTO);
            ps.setLong(1, id_centro_custo);
            ps.setLong(2, id_usuario);
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
