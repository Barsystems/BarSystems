/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico_setor.dao;

import conexao_banco.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import servico_setor.classe.ServicoSetorClasse;

/**
 *
 * @author Marcos
 */
public class ServicoSetorDAO implements IServicoSetorDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO servico_setor (nome) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE servico_setor SET nome = ? WHERE id_setor = ?";
    private static final String SQL_REMOVE = "UPDATE servico_setor SET excluido = 1 WHERE id_setor = ?";
    private static final String SQL_REFRESH_TABLE = "SELECT id_setor, nome FROM servico_setor WHERE excluido = 0 AND nome != 'Sem setor' AND nome LIKE ? ORDER BY nome";
    private static final String SQL_FIND_ALL = "SELECT id_setor, nome FROM servico_setor WHERE excluido = 0 AND nome LIKE ?";
    private static final String SQL_VERIFICA_SETOR_PRODUTO_REPETIDO = "SELECT nome FROM servico_setor WHERE nome = ? AND excluido = 0";
    
    @Override
    public int save(ServicoSetorClasse setor) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setString(1, setor.getSetor());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public int update(ServicoSetorClasse setor) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, setor.getSetor());
            ps.setLong(2, setor.getId());
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
    public List<ServicoSetorClasse> refreshTable(String pesquisa) {
        List<ServicoSetorClasse> setores = new ArrayList<ServicoSetorClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_REFRESH_TABLE);
            ps.setString(1, "%"+pesquisa+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ServicoSetorClasse setor = new ServicoSetorClasse();
                setor.setId(rs.getLong("id_setor"));
                setor.setSetor(rs.getString("nome"));
                setores.add(setor);
            }
            rs.close();
            ps.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        
        return setores;
    }
    
    @Override
    public List<ServicoSetorClasse> findAll(String pesquisa) {
        List<ServicoSetorClasse> setores = new ArrayList<ServicoSetorClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ps.setString(1, "%"+pesquisa+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ServicoSetorClasse setor = new ServicoSetorClasse();
                setor.setId(rs.getLong("id_setor"));
                setor.setSetor(rs.getString("nome"));
                setores.add(setor);
            }
            rs.close();
            ps.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        
        return setores;
    }
    
    @Override
    public boolean verificaSetorRepetido(String nome) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_SETOR_PRODUTO_REPETIDO);
            ps.setString(1, nome);
            rs = ps.executeQuery();
            while (rs.next()) {
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
    
}
