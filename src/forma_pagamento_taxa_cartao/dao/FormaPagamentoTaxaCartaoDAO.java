/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_taxa_cartao.dao;

import conexao_banco.ConexaoBanco;
import forma_pagamento_taxa_cartao.classe.FormaPagamentoTaxaCartaoClasse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoTaxaCartaoDAO implements IFormaPagamentoTaxaCartaoDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO forma_pagamento_taxa_cartao (id_forma_pagamento_fk, "
            + "id_maquina_cartao_fk, taxa) VALUES (?, ?, ?)";
    
    private static final String SQL_UPDATE = "UPDATE forma_pagamento_taxa_cartao SET id_forma_pagamento_fk = ?,"
            + "id_maquina_cartao_fk = ?, taxa = ? WHERE id_taxa_cartao = ?";
    
    private static final String SQL_REMOVE = "UPDATE forma_pagamento_taxa_cartao SET excluido = 1 WHERE id_taxa_cartao = ?";
    
    private static final String SQL_FIND_ALL = "SELECT forma_pagamento_taxa_cartao.id_taxa_cartao, "
            + "forma_pagamento_taxa_cartao.id_forma_pagamento_fk, forma_pagamento_taxa_cartao.id_maquina_cartao_fk, "
            + "forma_pagamento_taxa_cartao.taxa, forma_pagamento.nome, forma_pagamento_maquina_cartao.nome "
            + "FROM forma_pagamento_taxa_cartao"
            + "INNER JOIN forma_pagamento ON forma_pagamento_taxa_cartao.id_forma_pagamento_fk = forma_pagamento.id_forma_pagamento "
            + "INNER JOIN forma_pagamento_maquina_cartao ON forma_pagamento_taxa_cartao.id_maquina_cartao_fk = forma_pagamento_maquina_cartao.id_maquina_cartao "
            + "WHERE forma_pagamento_taxa_cartao.excluido = 0 AND forma_pagamento.nome LIKE ? "
            + "OR forma_pagamento_taxa_cartao.excluido = 0 AND forma_pagamento_maquina_cartao.nome LIKE ? "
            + "ORDER BY forma_pagamento.nome, forma_pagamento_maquina_cartao.nome";
    
    //private static final String SQL_VERIFICA_TAXA_CARTAO_REPETIDA_FORMA_PAGAMENTO = "SELECT nome FROM forma_pagamento_maquina_cartao  WHERE nome = ? AND excluido = 0";

    @Override
    public int save(FormaPagamentoTaxaCartaoClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setLong(1, classe.getId_forma_pagamento());
            ps.setLong(2, classe.getId_maquina_cartao());
            ps.setFloat(3, classe.getTaxa());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(FormaPagamentoTaxaCartaoClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setLong(1, classe.getId_forma_pagamento());
            ps.setLong(2, classe.getId_maquina_cartao());
            ps.setFloat(3, classe.getTaxa());
            ps.setLong(4, classe.getId_maquina_cartao());
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
    public List<FormaPagamentoTaxaCartaoClasse> findAll(String pesquisa) {
        List<FormaPagamentoTaxaCartaoClasse> lista_taxa = new ArrayList<FormaPagamentoTaxaCartaoClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ps.setString(1, "%"+pesquisa+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                FormaPagamentoTaxaCartaoClasse taxa = new FormaPagamentoTaxaCartaoClasse();
                taxa.setId(rs.getLong("id_taxa_cartao"));
                taxa.setId_forma_pagamento(rs.getLong("id_forma_pagamento_fk"));
                taxa.setId_maquina_cartao(rs.getLong("id_maquina_cartao_fk"));
                taxa.setTaxa(rs.getFloat("taxa"));
                lista_taxa.add(taxa);
            }
            rs.close();
            ps.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        
        return lista_taxa;
    }

    @Override
    public boolean verificaTaxaCartaoRepetidaMaquina(String maquina) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verificaTaxaCartaoRepetidaFormaPagamento(String forma_pagamento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
