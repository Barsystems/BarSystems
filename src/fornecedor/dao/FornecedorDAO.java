/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fornecedor.dao;

import conexao_banco.ConexaoBanco;
import fornecedor.classe.FornecedorClasse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FornecedorDAO implements IFornecedorDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO fornecedor (razao_social, nome_fantasia, cnpj, telefone, email, cep,"
            + "endereco, bairro, cidade, estado, id_pais_fk, inscricao_estadual, observacao) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_UPDATE = "UPDATE fornecedor SET razao_social = ?, nome_fantasia = ?, cnpj = ?, telefone = ?,"
            + "email = ?, cep = ?, endereco = ?, bairro = ?, cidade = ?, estado = ?, id_pais_fk = ?, inscricao_estadual = ?, "
            + "observacao = ? WHERE id_fornecedor = ?";
    
    private static final String SQL_REMOVE = "UPDATE fornecedor SET excluido = 1 WHERE id_fornecedor = ?";
    
    private static final String SQL_FIND_ALL = "SELECT fornecedor.id_fornecedor, fornecedor.razao_social, "
            + "fornecedor.nome_fantasia, fornecedor.cnpj, fornecedor.telefone, fornecedor.email, fornecedor.cep,"
            + "fornecedor.endereco, fornecedor.bairro, fornecedor.cidade, fornecedor.estado, fornecedor.id_pais_fk, "
            + "fornecedor.inscricao_estadual, fornecedor.observacao, pais.nome "
            + "FROM fornecedor "
            + "INNER JOIN pais ON fornecedor.id_pais_fk = pais.id_pais "
            + "WHERE fornecedor.excluido = 0 AND fornecedor.razao_social LIKE ? "
            + "OR fornecedor.excluido = 0 AND fornecedor.nome_fantasia LIKE ? "
            + "ORDER BY fornecedor.razao_social, fornecedor.nome_fantasia";
    
    private static final String SQL_VERIFICA_FORNECEDOR_REPETIDO_RAZAO_SOCIAL = "SELECT razao_social FROM fornecedor "
            + "WHERE razao_social = ? AND excluido = 0";
    
    private static final String SQL_VERIFICA_FORNECEDOR_REPETIDO_NOME_FANTASIA = "SELECT nome_fantasia FROM fornecedor "
            + "WHERE nome_fantasia = ? AND excluido = 0";

    @Override
    public int save(FornecedorClasse forn) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setString(1, forn.getRazao_social());
            ps.setString(2, forn.getNome_fantasia());
            ps.setString(3, forn.getCnpj());
            ps.setString(4, forn.getTelefone());
            ps.setString(5, forn.getEmail());
            ps.setString(6, forn.getCep());
            ps.setString(7, forn.getEndereco());
            ps.setString(8, forn.getBairro());
            ps.setString(9, forn.getCidade());
            ps.setString(10, forn.getEstado());
            ps.setLong(11, forn.getId_Pais());
            ps.setString(12, forn.getInscricao_estadual());
            ps.setString(13, forn.getObservacao());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(FornecedorClasse forn) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, forn.getRazao_social());
            ps.setString(2, forn.getNome_fantasia());
            ps.setString(3, forn.getCnpj());
            ps.setString(4, forn.getTelefone());
            ps.setString(5, forn.getEmail());
            ps.setString(6, forn.getCep());
            ps.setString(7, forn.getEndereco());
            ps.setString(8, forn.getBairro());
            ps.setString(9, forn.getCidade());
            ps.setString(10, forn.getEstado());
            ps.setLong(11, forn.getId_Pais());
            ps.setString(12, forn.getInscricao_estadual());
            ps.setString(13, forn.getObservacao());
            ps.setLong(14, forn.getId());
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
    public List<FornecedorClasse> findAll(String pesquisa) {
        List<FornecedorClasse> fornecedores = new ArrayList<FornecedorClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ps.setString(1, "%"+pesquisa+"%");
            ps.setString(2, "%"+pesquisa+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                FornecedorClasse fornecedor = new FornecedorClasse();
                fornecedor.setId(rs.getLong("fornecedor.id_fornecedor"));
                fornecedor.setRazao_social(rs.getString("fornecedor.razao_social"));
                fornecedor.setNome_fantasia(rs.getString("fornecedor.nome_fantasia"));
                fornecedor.setCnpj(rs.getString("fornecedor.cnpj"));
                fornecedor.setTelefone(rs.getString("fornecedor.telefone"));
                fornecedor.setEmail(rs.getString("fornecedor.email"));
                fornecedor.setCep(rs.getString("fornecedor.cep"));
                fornecedor.setEndereco(rs.getString("fornecedor.endereco"));
                fornecedor.setBairro(rs.getString("fornecedor.bairro"));
                fornecedor.setCidade(rs.getString("fornecedor.cidade"));
                fornecedor.setEstado(rs.getString("fornecedor.estado"));
                fornecedor.setId_Pais(rs.getLong("fornecedor.id_pais_fk"));
                fornecedor.setPais(rs.getString("pais.nome"));
                fornecedor.setInscricao_estadual(rs.getString("fornecedor.inscricao_estadual"));
                fornecedor.setObservacao(rs.getString("fornecedor.observacao"));
                
                fornecedores.add(fornecedor);
            }
            rs.close();
            ps.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        
        return fornecedores;
    }

    @Override
    public boolean verificaFornecedorRepetidoRazaoSocial(String razao_social) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_FORNECEDOR_REPETIDO_RAZAO_SOCIAL);
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
    public boolean verificaFornecedorRepetidoNomeFantasia(String nome_fantasia) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_FORNECEDOR_REPETIDO_NOME_FANTASIA);
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
