/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixa.dao;

import caixa.classe.CaixaClasse;
import conexao_banco.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marcos
 */
public class CaixaDAO implements ICaixaDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_ABRIR_CAIXA = "INSERT INTO caixa (id_centro_custo) VALUES (?)";
    private static final String SQL_FECHAR_CAIXA = "UPDATE caixa SET data_fechamento = ? WHERE id_caixa = ?";
    private static final String SQL_VERIFICA_CAIXA_ABERTO = "SELECT data_fechamento FROM caixa WHERE id_centro_custo = ? "
            + "AND data_fechamento is null AND excluido = 0";
    private static final String SQL_GET_CAIXA = "SELECT id_caixa, data_abertura, data_fechamento FROM caixa "
            + "WHERE id_centro_custo = ? AND data_fechamento is null AND excluido = 0";

    @Override
    public int abrirCaixa(Long id_centro_custo) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_ABRIR_CAIXA);
            ps.setLong(1, id_centro_custo);
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int fecharCaixa(Long id_caixa) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FECHAR_CAIXA);
            ps.setString(1, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
            ps.setLong(2, id_caixa);
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean verificaCaixaAberto(Long id_centro_custo) {
        boolean result = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_CAIXA_ABERTO);
            ps.setLong(1, id_centro_custo);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
            
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public CaixaClasse getCaixa(Long id_centro_custo) {
        CaixaClasse classe = new CaixaClasse();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_GET_CAIXA);
            ps.setLong(1, id_centro_custo);
            rs = ps.executeQuery();
            if (rs.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                classe.setId(rs.getLong("id_caixa"));
                classe.setData_abertura(sdf.format(rs.getTimestamp("data_abertura")));
                if (rs.getTimestamp("data_fechamento") != null) {
                    classe.setData_fechamento(sdf.format(rs.getTimestamp("data_fechamento")));
                }
            }
            
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classe;
    }
    
}
