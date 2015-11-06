/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixa_movimentacao.dao;

import caixa_movimentacao.classe.CaixaMovimentacaoClasse;
import conexao_banco.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class CaixaMovimentacaoDAO implements ICaixaMovimentacaoDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO caixa_movimentacao (id_caixa_fk, descricao, id_forma_pagamento_fk, "
            + "valor, tipo) VALUES (?, ?, ?, ?, ?)";
    
    private static final String SQL_UPDATE = "UPDATE caixa_movimentacao SET id_caixa_fk = ?, descricao = ?, "
            + "id_forma_pagamento_fk = ?, valor = ?, tipo = ? WHERE id_caixa_movimentacao = ?";
    
    private static final String SQL_REMOVE = "UPDATE caixa_movimentacao SET excluido = 1 WHERE id_caixa_movimentacao = ?";
    
    private static final String SQL_FIND_ALL = "SELECT caixa_movimentacao.id_caixa_movimentacao, "
            + "caixa_movimentacao.id_caixa_fk, caixa_movimentacao.descricao, caixa_movimentacao.id_forma_pagamento_fk, "
            + "caixa_movimentacao.valor, caixa_movimentacao.tipo, caixa_movimentacao.data, forma_pagamento.nome "
            + "FROM caixa_movimentacao "
            + "INNER JOIN forma_pagamento ON caixa_movimentacao.id_forma_pagamento_fk = forma_pagamento.id_forma_pagamento "
            + "WHERE caixa_movimentacao.id_caixa_fk = ? AND caixa_movimentacao.excluido = 0 ORDER BY caixa_movimentacao.data";

    @Override
    public int save(CaixaMovimentacaoClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setLong(1, classe.getId_caixa());
            ps.setString(2, classe.getDescricao());
            ps.setLong(3, classe.getId_forma_pagamento());
            ps.setFloat(4, classe.getValor());
            ps.setString(5, classe.getTipo());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(CaixaMovimentacaoClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setLong(1, classe.getId_caixa());
            ps.setString(2, classe.getDescricao());
            ps.setLong(3, classe.getId_forma_pagamento());
            ps.setFloat(4, classe.getValor());
            ps.setString(5, classe.getTipo());
            ps.setLong(6, classe.getId());
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
    public List<CaixaMovimentacaoClasse> findAll(Long id_caixa) {
        List<CaixaMovimentacaoClasse> lista = new ArrayList<CaixaMovimentacaoClasse>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ps.setLong(1, id_caixa);
            rs = ps.executeQuery();
            while (rs.next()) {
                CaixaMovimentacaoClasse classe = new CaixaMovimentacaoClasse();
                classe.setId(rs.getLong("caixa_movimentacao.id_caixa_movimentacao"));
                classe.setId_caixa(rs.getLong("caixa_movimentacao.id_caixa_fk"));
                classe.setDescricao(rs.getString("caixa_movimentacao.descricao"));
                classe.setId_forma_pagamento(rs.getLong("caixa_movimentacao.id_forma_pagamento_fk"));
                classe.setForma_pagamento(rs.getString("forma_pagamento.nome"));
                classe.setValor(rs.getFloat("caixa_movimentacao.valor"));
                classe.setTipo(rs.getString("caixa_movimentacao.tipo"));
                classe.setData(sdf.format(rs.getTimestamp("caixa_movimentacao.data")));
                
                lista.add(classe);
            }
            rs.close();
            ps.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        
        return lista;
    }
    
}
