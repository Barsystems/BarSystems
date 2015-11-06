/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conta_bancaria_movimentacao.dao;

import conexao_banco.ConexaoBanco;
import conta_bancaria_movimentacao.classe.ContaBancariaMovimentacaoClasse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class ContaBancariaMovimentacaoDAO implements IContaBancariaMovimentacaoDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO conta_bancaria_movimentacao (id_centro_custo_fk, descricao, "
            + "id_forma_pagamento_fk, valor, tipo) VALUES (?, ?, ?, ?, ?)";
    
    private static final String SQL_UPDATE = "UPDATE conta_bancaria_movimentacao SET id_centro_custo_fk = ?, descricao = ?, "
            + "id_forma_pagamento_fk = ?, valor = ?, tipo = ? WHERE id_conta_bancaria_movimentacao = ?";
    
    private static final String SQL_REMOVE = "UPDATE conta_bancaria_movimentacao SET excluido = 1 "
            + "WHERE id_conta_bancaria_movimentacao = ?";
    
    private static final String SQL_FIND_ALL = "SELECT conta_bancaria_movimentacao.id_conta_bancaria_movimentacao, "
            + "conta_bancaria_movimentacao.id_centro_custo_fk, conta_bancaria_movimentacao.descricao, "
            + "conta_bancaria_movimentacao.id_forma_pagamento_fk, conta_bancaria_movimentacao.valor, "
            + "conta_bancaria_movimentacao.tipo, conta_bancaria_movimentacao.data, forma_pagamento.nome "
            + "FROM conta_bancaria_movimentacao "
            + "INNER JOIN forma_pagamento ON conta_bancaria_movimentacao.id_forma_pagamento_fk = forma_pagamento.id_forma_pagamento "
            + "WHERE conta_bancaria_movimentacao.id_centro_custo_fk = ? AND conta_bancaria_movimentacao.excluido = 0 "
            + "AND conta_bancaria_movimentacao.data BETWEEN ? AND ? "
            + "ORDER BY conta_bancaria_movimentacao.data";

    @Override
    public int save(ContaBancariaMovimentacaoClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setLong(1, classe.getId_centro_custo());
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
    public int update(ContaBancariaMovimentacaoClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setLong(1, classe.getId_centro_custo());
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
    public List<ContaBancariaMovimentacaoClasse> findAll(Long id_centro_custo, Date data_inicial, Date data_final) {
        List<ContaBancariaMovimentacaoClasse> lista = new ArrayList<ContaBancariaMovimentacaoClasse>();
        SimpleDateFormat sdfPesquisa = new SimpleDateFormat("yyyy/MM//dd HH:mm:ss");
        SimpleDateFormat sdfResult = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ps.setLong(1, id_centro_custo);
            ps.setString(2, sdfPesquisa.format(data_inicial));
            ps.setString(3, sdfPesquisa.format(data_final));
            rs = ps.executeQuery();
            while (rs.next()) {
                ContaBancariaMovimentacaoClasse classe = new ContaBancariaMovimentacaoClasse();
                classe.setId(rs.getLong("conta_bancaria_movimentacao.id_conta_bancaria_movimentacao"));
                classe.setId_centro_custo(rs.getLong("conta_bancaria_movimentacao.id_centro_custo_fk"));
                classe.setDescricao(rs.getString("conta_bancaria_movimentacao.descricao"));
                classe.setId_forma_pagamento(rs.getLong("conta_bancaria_movimentacao.id_forma_pagamento_fk"));
                classe.setForma_pagamento(rs.getString("forma_pagamento.nome"));
                classe.setValor(rs.getFloat("conta_bancaria_movimentacao.valor"));
                classe.setTipo(rs.getString("conta_bancaria_movimentacao.tipo"));
                classe.setData(sdfResult.format(rs.getTimestamp("conta_bancaria_movimentacao.data")));
                
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
