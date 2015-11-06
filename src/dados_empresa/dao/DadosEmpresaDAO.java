/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados_empresa.dao;

import conexao_banco.ConexaoBanco;
import dados_empresa.classe.DadosEmpresaClasse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Marcos
 */
public class DadosEmpresaDAO implements IDadosEmpresaDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO dados_empresa (razao_social, nome_fantasia, cnpj, telefone, email, "
            + "cep, endereco, bairro, cidade, estado, id_pais_fk, inscricao_estadual, logomarca) "
            + "VALUES (?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,?)";
    
    private static final String SQL_UPDATE = "UPDATE dados_empresa set razao_social = ?, nome_fantasia = ?, cnpj = ?, "
            + "telefone = ?, email = ?, cep = ?, endereco = ?, bairro = ?, cidade = ?, estado = ?, id_pais_fk = ?, "
            + "inscricao_estadual = ?, logomarca = ?";
    
    private static final String SQL_FIND_EMPRESA = "SELECT dados_empresa.razao_social, dados_empresa.nome_fantasia, "
            + "dados_empresa.cnpj, dados_empresa.telefone, dados_empresa.email, dados_empresa.cep, dados_empresa.endereco, "
            + "dados_empresa.bairro, dados_empresa.cidade, dados_empresa.estado, dados_empresa.id_pais_fk, "
            + "dados_empresa.inscricao_estadual, dados_empresa.logomarca, pais.nome "
            + "FROM dados_empresa "
            + "INNER JOIN pais ON dados_empresa.id_pais_fk = pais.id_pais";

    @Override
    public int save(DadosEmpresaClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setString(1, classe.getRazao_social());
            ps.setString(2, classe.getNome_fantasia());
            ps.setString(3, classe.getCnpj());
            ps.setString(4, classe.getTelefone());
            ps.setString(5, classe.getEmail());
            ps.setString(6, classe.getCep());
            ps.setString(7, classe.getEndereco());
            ps.setString(8, classe.getBairro());
            ps.setString(9, classe.getCidade());
            ps.setString(10, classe.getEstado());
            ps.setLong(11, classe.getId_pais());
            ps.setString(12, classe.getInscricao_estadual());
            ps.setBytes(13, classe.getLogomarca());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(DadosEmpresaClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, classe.getRazao_social());
            ps.setString(2, classe.getNome_fantasia());
            ps.setString(3, classe.getCnpj());
            ps.setString(4, classe.getTelefone());
            ps.setString(5, classe.getEmail());
            ps.setString(6, classe.getCep());
            ps.setString(7, classe.getEndereco());
            ps.setString(8, classe.getBairro());
            ps.setString(9, classe.getCidade());
            ps.setString(10, classe.getEstado());
            ps.setLong(11, classe.getId_pais());
            ps.setString(12, classe.getInscricao_estadual());
            ps.setBytes(13, classe.getLogomarca());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public DadosEmpresaClasse findEmpresa() {
        DadosEmpresaClasse classe = new DadosEmpresaClasse();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_EMPRESA);
            rs = ps.executeQuery();
            if (rs.next()) {
                classe.setRazao_social(rs.getString("dados_empresa.razao_social"));
                classe.setNome_fantasia(rs.getString("dados_empresa.nome_fantasia"));
                classe.setCnpj(rs.getString("dados_empresa.cnpj"));
                classe.setTelefone(rs.getString("dados_empresa.telefone"));
                classe.setEmail(rs.getString("dados_empresa.email"));
                classe.setCep(rs.getString("dados_empresa.cep"));
                classe.setEndereco(rs.getString("dados_empresa.endereco"));
                classe.setBairro(rs.getString("dados_empresa.bairro"));
                classe.setCidade(rs.getString("dados_empresa.cidade"));
                classe.setEstado(rs.getString("dados_empresa.estado"));
                classe.setId_pais(rs.getLong("dados_empresa.id_pais_fk"));
                classe.setPais(rs.getString("pais.nome"));
                classe.setInscricao_estadual(rs.getString("dados_empresa.inscricao_estadual"));
                classe.setLogomarca(rs.getBytes("dados_empresa.logomarca"));
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
