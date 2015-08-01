/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pais.dao;

import conexao_banco.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pais.classe.PaisClasse;

/**
 *
 * @author Marcos
 */
public class PaisDAO implements IPaisDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private String SQL_FIND_ALL = "SELECT id_pais, nome FROM pais WHERE excluido = 0 ORDER BY nome";

    @Override
    public List<PaisClasse> findAll() {
        List<PaisClasse> paises = new ArrayList<PaisClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                PaisClasse pais = new PaisClasse();
                pais.setId(rs.getLong("id_pais"));
                pais.setPais(rs.getString("nome"));
                
                paises.add(pais);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paises;
    }
    
}
