/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.dao;

import conexao_banco.ConexaoBanco;
import empresa.classe.EmpresaClasse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class EmpresaDAO implements IEmpresaDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO empresa (razao_social, nome_fantasia, cnpj, telefone, email, cep,"
            + "endereco, bairro, cidade, estado, id_pais_fk, inscricao_estadual, observacao) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_UPDATE = "UPDATE empresa SET razao_social = ?, nome_fantasia = ?, cnpj = ?, telefone = ?,"
            + "email = ?, cep = ?, endereco = ?, bairro = ?, cidade = ?, estado = ?, id_pais_fk = ?, inscricao_estadual = ?, "
            + "observacao = ? WHERE id_empresa = ?";
    
    private static final String SQL_REMOVE = "UPDATE empresa SET excluido = 1 WHERE id_empresa = ?";
    
    private static final String SQL_FIND_ALL = "SELECT empresa.id_empresa, empresa.razao_social, empresa.nome_fantasia, "
            + "empresa.cnpj, empresa.telefone, empresa.email, empresa.cep, empresa.endereco, empresa.bairro, empresa.cidade, "
            + "empresa.estado, empresa.id_pais_fk, empresa.inscricao_estadual, empresa.observacao, pais.nome "
            + "FROM empresa "
            + "INNER JOIN pais ON empresa.id_pais_fk = pais.id_pais "
            + "WHERE empresa.excluido = 0 AND empresa.razao_social LIKE ? "
            + "OR empresa.excluido = 0 AND empresa.nome_fantasia LIKE ? "
            + "ORDER BY empresa.razao_social, empresa.nome_fantasia";
    
    private static final String SQL_VERIFICA_EMPRESA_REPETIDA_RAZAO_SOCIAL = "SELECT razao_social FROM empresa "
            + "WHERE razao_social = ? AND excluido = 0";
    
    private static final String SQL_VERIFICA_EMPRESA_REPETIDA_NOME_FANTASIA = "SELECT nome_fantasia FROM empresa "
            + "WHERE nome_fantasia = ? AND excluido = 0";

    @Override
    public int save(EmpresaClasse empresa) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setString(1, empresa.getRazao_social());
            ps.setString(2, empresa.getNome_fantasia());
            ps.setString(3, empresa.getCnpj());
            ps.setString(4, empresa.getTelefone());
            ps.setString(5, empresa.getEmail());
            ps.setString(6, empresa.getCep());
            ps.setString(7, empresa.getEndereco());
            ps.setString(8, empresa.getBairro());
            ps.setString(9, empresa.getCidade());
            ps.setString(10, empresa.getEstado());
            ps.setLong(11, empresa.getId_Pais());
            ps.setString(12, empresa.getInscricao_estadual());
            ps.setString(13, empresa.getObservacao());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(EmpresaClasse empresa) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, empresa.getRazao_social());
            ps.setString(2, empresa.getNome_fantasia());
            ps.setString(3, empresa.getCnpj());
            ps.setString(4, empresa.getTelefone());
            ps.setString(5, empresa.getEmail());
            ps.setString(6, empresa.getCep());
            ps.setString(7, empresa.getEndereco());
            ps.setString(8, empresa.getBairro());
            ps.setString(9, empresa.getCidade());
            ps.setString(10, empresa.getEstado());
            ps.setLong(11, empresa.getId_Pais());
            ps.setString(12, empresa.getInscricao_estadual());
            ps.setString(13, empresa.getObservacao());
            ps.setLong(14, empresa.getId());
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
    public List<EmpresaClasse> findAll(String pesquisa) {
        List<EmpresaClasse> empresas = new ArrayList<EmpresaClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ps.setString(1, "%"+pesquisa+"%");
            ps.setString(2, "%"+pesquisa+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                EmpresaClasse empresa = new EmpresaClasse();
                empresa.setId(rs.getLong("empresa.id_empresa"));
                empresa.setRazao_social(rs.getString("empresa.razao_social"));
                empresa.setNome_fantasia(rs.getString("empresa.nome_fantasia"));
                empresa.setCnpj(rs.getString("empresa.cnpj"));
                empresa.setTelefone(rs.getString("empresa.telefone"));
                empresa.setEmail(rs.getString("empresa.email"));
                empresa.setCep(rs.getString("empresa.cep"));
                empresa.setEndereco(rs.getString("empresa.endereco"));
                empresa.setBairro(rs.getString("empresa.bairro"));
                empresa.setCidade(rs.getString("empresa.cidade"));
                empresa.setEstado(rs.getString("empresa.estado"));
                empresa.setId_Pais(rs.getLong("empresa.id_pais_fk"));
                empresa.setPais(rs.getString("pais.nome"));
                empresa.setInscricao_estadual(rs.getString("empresa.inscricao_estadual"));
                empresa.setObservacao(rs.getString("empresa.observacao"));
                
                empresas.add(empresa);
            }
            rs.close();
            ps.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        
        return empresas;
    }

    @Override
    public boolean verificaEmpresaRepetidaRazaoSocial(String razao_social) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_EMPRESA_REPETIDA_RAZAO_SOCIAL);
            ps.setString(1, razao_social);
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

    @Override
    public boolean verificaEmpresaRepetidaNomeFantasia(String nome_fantasia) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_EMPRESA_REPETIDA_NOME_FANTASIA);
            ps.setString(1, nome_fantasia);
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
