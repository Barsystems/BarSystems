/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_custo.dao;

import centro_custo.classe.CentroCustoClasse;
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
public class CentroCustoDAO implements ICentroCustoDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO centro_custo (nome, tipo) VALUES (?, ?)";
    
    private static final String SQL_UPDATE = "UPDATE centro_custo SET nome = ?, tipo = ? WHERE id_centro_custo = ?";
    
    private static final String SQL_REMOVE = "UPDATE centro_custo SET excluido = 1 WHERE id_centro_custo = ?";
    
    private static final String SQL_FIND_ALL = "SELECT id_centro_custo, nome, tipo, saldo FROM centro_custo "
            + "WHERE excluido = 0 AND nome LIKE ? ORDER BY tipo, nome";
    
    private static final String SQL_VERIFICA_CENTRO_CUSTO_REPETIDO = "SELECT nome FROM centro_custo "
            + "WHERE nome = ? AND excluido = 0";
    
    private static final String SQL_DIMINUI_SALDO = "UPDATE centro_custo SET saldo = saldo - ? WHERE id_centro_custo = ?";
    
    private static final String SQL_AUMENTA_SALDO = "UPDATE centro_custo SET saldo = saldo + ? WHERE id_centro_custo = ?";

    @Override
    public int save(CentroCustoClasse centro) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setString(1, centro.getNome());
            ps.setString(2, centro.getTipo());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(CentroCustoClasse centro) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, centro.getNome());
            ps.setString(2, centro.getTipo());
            ps.setLong(3, centro.getId());
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
    public List<CentroCustoClasse> findAll(String pesquisa) {
        List<CentroCustoClasse> centrosCusto = new ArrayList<CentroCustoClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ps.setString(1, "%"+pesquisa+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                CentroCustoClasse centroCusto = new CentroCustoClasse();
                centroCusto.setId(rs.getLong("id_centro_custo"));
                centroCusto.setNome(rs.getString("nome"));
                centroCusto.setTipo(rs.getString("tipo"));
                centroCusto.setSaldo(rs.getFloat("saldo"));
                
                centrosCusto.add(centroCusto);
            }
            rs.close();
            ps.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        
        return centrosCusto;
    }

    @Override
    public boolean verificaCentroCustoRepetido(String nome) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_CENTRO_CUSTO_REPETIDO);
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
    public int diminuiSaldo(Long id, float saldo) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_DIMINUI_SALDO);
            ps.setFloat(1, saldo);
            ps.setLong(2, id);
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int aumentaSaldo(Long id, float saldo) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_AUMENTA_SALDO);
            ps.setFloat(1, saldo);
            ps.setLong(2, id);
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
