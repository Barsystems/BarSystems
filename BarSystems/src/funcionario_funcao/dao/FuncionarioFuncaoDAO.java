/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario_funcao.dao;

import conexao_banco.Class_Conexao_Banco;
import funcionario_funcao.classe.FuncionarioFuncaoClasse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FuncionarioFuncaoDAO implements IFuncionarioFuncaoDAO {
    
    Class_Conexao_Banco banco = new Class_Conexao_Banco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO funcionario_funcao (nome) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE funcionario_funcao SET nome = ? WHERE id_funcao = ?";
    private static final String SQL_REMOVE = "UPDATE funcionario_funcao SET excluido = 1 WHERE id_funcao = ?";
    private static final String SQL_REFRESH_TABLE = "SELECT id_funcao, nome FROM funcionario_funcao WHERE excluido = 0 AND nome != 'Sem função' AND nome LIKE ?";
    private static final String SQL_FIND_ALL = "SELECT id_funcao, nome FROM funcionario_funcao WHERE excluido = 0 AND nome LIKE ?";
    private static final String SQL_VERIFICA_FUNCAO_REPETIDA = "SELECT nome FROM funcionario_funcao  WHERE nome = ? AND excluido = 0";

    @Override
    public int save(FuncionarioFuncaoClasse funcao) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setString(1, funcao.getFuncao());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(FuncionarioFuncaoClasse funcao) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, funcao.getFuncao());
            ps.setLong(2, funcao.getId());
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
    public List<FuncionarioFuncaoClasse> refreshTable(String pesquisa) {
        List<FuncionarioFuncaoClasse> funcs = new ArrayList<FuncionarioFuncaoClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_REFRESH_TABLE);
            ps.setString(1, "%"+pesquisa+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                FuncionarioFuncaoClasse func = new FuncionarioFuncaoClasse();
                func.setId(rs.getLong("id_funcao"));
                func.setFuncao(rs.getString("nome"));
                funcs.add(func);
            }
            rs.close();
            ps.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        
        return funcs;
    }

    @Override
    public List<FuncionarioFuncaoClasse> findAll(String pesquisa) {
        List<FuncionarioFuncaoClasse> funcs = new ArrayList<FuncionarioFuncaoClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ps.setString(1, "%"+pesquisa+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                FuncionarioFuncaoClasse func = new FuncionarioFuncaoClasse();
                func.setId(rs.getLong("id_funcao"));
                func.setFuncao(rs.getString("nome"));
                funcs.add(func);
            }
            rs.close();
            ps.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        
        return funcs;
    }

    @Override
    public boolean verificaFuncaoRepetida(String funcao) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_FUNCAO_REPETIDA);
            ps.setString(1, funcao);
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
