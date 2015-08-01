/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_tipo.dao;

import conexao_banco.ConexaoBanco;
import forma_pagamento_tipo.classe.FormaPagamentoTipoClasse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoTipoDAO implements IFormaPagamentoTipoDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_FIND_ALL = "SELECT id_tipo, nome FROM forma_pagamento_tipo WHERE excluido = 0";

    @Override
    public List<FormaPagamentoTipoClasse> findAll(String pesquisa) {
        List<FormaPagamentoTipoClasse> lista_tipo = new ArrayList<FormaPagamentoTipoClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                FormaPagamentoTipoClasse tipo = new FormaPagamentoTipoClasse();
                tipo.setId(rs.getLong("id_tipo"));
                tipo.setNome(rs.getString("nome"));
                
                lista_tipo.add(tipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista_tipo;
    }
    
}
