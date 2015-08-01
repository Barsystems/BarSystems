/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_maquina_cartao.dao;

import conexao_banco.ConexaoBanco;
import forma_pagamento_maquina_cartao.classe.FormaPagamentoMaquinaCartaoClasse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoMaquinaCartaoDAO implements IFormaPagamentoMaquinaCartaoDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO forma_pagamento_maquina_cartao (nome) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE forma_pagamento_maquina_cartao SET nome = ? WHERE id_maquina_cartao = ?";
    private static final String SQL_REMOVE = "UPDATE forma_pagamento_maquina_cartao SET excluido = 1 WHERE id_maquina_cartao = ?";
    private static final String SQL_FIND_ALL = "SELECT id_maquina_cartao, nome FROM forma_pagamento_maquina_cartao WHERE excluido = 0 AND nome LIKE ? ORDER BY nome";
    private static final String SQL_VERIFICA_MAQUINA_CARTAO_REPETIDA = "SELECT nome FROM forma_pagamento_maquina_cartao  WHERE nome = ? AND excluido = 0";

    @Override
    public int save(FormaPagamentoMaquinaCartaoClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setString(1, classe.getNome());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(FormaPagamentoMaquinaCartaoClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, classe.getNome());
            ps.setLong(2, classe.getId());
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
    public List<FormaPagamentoMaquinaCartaoClasse> findAll(String pesquisa) {
        List<FormaPagamentoMaquinaCartaoClasse> lista_maquina = new ArrayList<FormaPagamentoMaquinaCartaoClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ps.setString(1, "%"+pesquisa+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                FormaPagamentoMaquinaCartaoClasse maquina = new FormaPagamentoMaquinaCartaoClasse();
                maquina.setId(rs.getLong("id_maquina_cartao"));
                maquina.setNome(rs.getString("nome"));
                lista_maquina.add(maquina);
            }
            rs.close();
            ps.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        
        return lista_maquina;
    }

    @Override
    public boolean verificaMaquinaCartaoRepetida(String nome) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_MAQUINA_CARTAO_REPETIDA);
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
