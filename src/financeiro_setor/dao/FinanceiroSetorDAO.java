/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financeiro_setor.dao;

import conexao_banco.ConexaoBanco;
import financeiro_setor.classe.FinanceiroSetorClasse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FinanceiroSetorDAO implements IFinanceiroSetorDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO financeiro_setor (nome, tipo) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE financeiro_setor SET nome = ?, tipo = ? WHERE id_setor = ?";
    private static final String SQL_REMOVE = "UPDATE financeiro_setor SET excluido = 1 WHERE id_setor = ?";
    private static final String SQL_FIND_ALL = "SELECT id_setor, nome, tipo, padrao FROM financeiro_setor "
            + "WHERE excluido = 0 AND nome LIKE ? ORDER BY tipo DESC, nome";
    private static final String SQL_VERIFICA_SETOR_REPETIDO = "SELECT nome FROM financeiro_setor  "
            + "WHERE nome = ? AND excluido = 0";

    @Override
    public int save(FinanceiroSetorClasse setor) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setString(1, setor.getNome());
            ps.setString(2, setor.getTipo());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(FinanceiroSetorClasse setor) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, setor.getNome());
            ps.setString(2, setor.getTipo());
            ps.setLong(3, setor.getId());
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
    public List<FinanceiroSetorClasse> findAll(String pesquisa) {
        List<FinanceiroSetorClasse> lista_setor = new ArrayList<FinanceiroSetorClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ps.setString(1, "%"+pesquisa+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                FinanceiroSetorClasse setor = new FinanceiroSetorClasse();
                setor.setId(rs.getLong("id_setor"));
                setor.setNome(rs.getString("nome"));
                setor.setTipo(rs.getString("tipo"));
                setor.setPadrao(rs.getBoolean("padrao"));
                lista_setor.add(setor);
            }
            rs.close();
            ps.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        
        return lista_setor;
    }

    @Override
    public boolean verificaSetorRepetido(String setor) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_SETOR_REPETIDO);
            ps.setString(1, setor);
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
