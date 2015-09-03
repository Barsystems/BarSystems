/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_estoque.dao;

import centro_estoque.classe.CentroEstoqueClasse;
import conexao_banco.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class CentroEstoqueDAO implements ICentroEstoqueDAO {

    ConexaoBanco banco;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO centro_estoque (nome, ativo) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE centro_estoque SET nome = ?, ativo = ? WHERE id_centro_estoque = ?";
    private static final String SQL_REMOVE = "UPDATE centro_estoque SET excluido = 1 "
            + "WHERE id_centro_estoque = ?";
    private static final String SQL_FIND_ALL = "SELECT id_centro_estoque, nome, ativo FROM centro_estoque "
            + "WHERE excluido = 0 AND nome LIKE ?";
    private static final String SQL_VERIFICA_CENTRO_ESTOQUE_REPETIDO = "SELECT nome FROM centro_estoque "
            + "WHERE nome = ? AND excluido = 0";
    
    public CentroEstoqueDAO() {
        banco = new ConexaoBanco();
    }
    
    @Override
    public int save(CentroEstoqueClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setString(1, classe.getNome());
            ps.setBoolean(2, classe.isAtivo());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(CentroEstoqueClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, classe.getNome());
            ps.setBoolean(2, classe.isAtivo());
            ps.setLong(3, classe.getId());
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
    public List<CentroEstoqueClasse> findAll(String pesquisa) {
        List<CentroEstoqueClasse> lista = new ArrayList<CentroEstoqueClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ps.setString(1, "%"+pesquisa+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                CentroEstoqueClasse classe = new CentroEstoqueClasse();
                classe.setId(rs.getLong("id_centro_estoque"));
                classe.setNome(rs.getString("nome"));
                classe.setAtivo(rs.getBoolean("ativo"));
                
                lista.add(classe);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean verificaCentroEstoqueRepetido(String nome) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_CENTRO_ESTOQUE_REPETIDO);
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
    
}
