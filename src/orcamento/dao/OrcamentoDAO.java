/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orcamento.dao;

import conexao_banco.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import orcamento.classe.OrcamentoClasse;

/**
 *
 * @author Marcos
 */
public class OrcamentoDAO implements IOrcamentoDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "";
    private static final String SQL_UPDATE = "";
    private static final String SQL_REMOVE = "";

    @Override
    public int save(OrcamentoClasse classe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(OrcamentoClasse classe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrcamentoClasse> findAll(String parametro_pesquisa, String pesquisa, Date data) {
        List<OrcamentoClasse> lista = new ArrayList<OrcamentoClasse>();
        
        String SQL_FIND_ALL = "";
        
        if (parametro_pesquisa.equals("Nome do cliente")) {
            SQL_FIND_ALL = "SELECT orcamento.id_orcamento, orcamento.id_cliente_fk, "
            + "orcamento.data, orcamento.desconto, SUM(orcamento_servico.valor_cobrado), "
            + "SUM(orcamento_produto.valor_cobrado), cliente.nome "
            + "FROM orcamento, orcamento_servico, orcamento_produto "
            + "INNER JOIN cliente ON orcamento.id_cliente_fk = cliente.id_cliente "
            + "WHERE orcamento.id_orcamento = ? "
            + "AND orcamento_servico.id_orcamento_fk = orcamento.id_orcamento "
            + "AND orcamento_produto.id_orcamento_fk = orcamento.id_orcamento "
            + "AND orcamento.excluido = 0 "
            + "AND cliente.nome LIKE ?";
        } else if (parametro_pesquisa.equals("Nome da empresa")) {
            
        } else if (parametro_pesquisa.equals("Data")) {
            
        }
        
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
}
