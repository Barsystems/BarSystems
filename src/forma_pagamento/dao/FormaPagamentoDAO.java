/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento.dao;

import conexao_banco.ConexaoBanco;
import forma_pagamento.classe.FormaPagamentoClasse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoDAO implements IFormaPagamentoDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO forma_pagamento (nome, id_tipo_fk, dias_recebimento) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE forma_pagamento SET nome = ?, id_tipo_fk = ?, dias_recebimento = ? "
            + "WHERE id_forma_pagamento = ?";
    private static final String SQL_REMOVE = "UPDATE forma_pagamento SET excluido = 1 WHERE id_forma_pagamento = ?";
    private static final String SQL_FIND_ALL = "SELECT forma_pagamento.id_forma_pagamento, forma_pagamento.nome, "
            + "forma_pagamento.id_tipo_fk, forma_pagamento.dias_recebimento, forma_pagamento.padrao, "
            + "forma_pagamento_tipo.nome "
            + "FROM forma_pagamento "
            + "INNER JOIN forma_pagamento_tipo ON forma_pagamento.id_tipo_fk = forma_pagamento_tipo.id_tipo "
            + "WHERE forma_pagamento.excluido = 0";
    private static final String SQL_VERIFICA_FORMA_PAGAMENTO_REPETIDA = "SELECT nome FROM forma_pagamento "
            + "WHERE nome = ? AND excluido = 0";

    @Override
    public int save(FormaPagamentoClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setString(1, classe.getNome());
            ps.setLong(2, classe.getId_tipo());
            ps.setInt(3, classe.getDias_recebimento());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(FormaPagamentoClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, classe.getNome());
            ps.setLong(2, classe.getId_tipo());
            ps.setInt(3, classe.getDias_recebimento());
            ps.setLong(4, classe.getId());
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
    public List<FormaPagamentoClasse> findAll(String pesquisa) {
        List<FormaPagamentoClasse> lista_formas = new ArrayList<FormaPagamentoClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                FormaPagamentoClasse forma = new FormaPagamentoClasse();
                forma.setId(rs.getLong("forma_pagamento.id_forma_pagamento"));
                forma.setNome(rs.getString("forma_pagamento.nome"));
                forma.setId_tipo(rs.getLong("forma_pagamento.id_tipo_fk"));
                forma.setTipo(rs.getString("forma_pagamento_tipo.nome"));
                forma.setDias_recebimento(rs.getInt("forma_pagamento.dias_recebimento"));
                forma.setPadrao(rs.getBoolean("forma_pagamento.padrao"));
                
                lista_formas.add(forma);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista_formas;
    }

    @Override
    public boolean verificaFormaPagamentoRepetida(String nome) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_FORMA_PAGAMENTO_REPETIDA);
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
