/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario.dao;

import conexao_banco.ConexaoBanco;
import funcionario.classe.FuncionarioClasse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FuncionarioDAO implements IFuncionarioDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO funcionario (nome, data_nascimento, cpf, rg, email, telefone, "
            + "celular, id_funcao_fk, salario, cep, endereco, bairro, cidade, estado, id_pais_fk) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_UPDATE = "UPDATE funcionario SET nome = ?, data_nascimento = ?, cpf = ?, rg = ?, "
            + "email = ?, telefone = ?, celular = ?, id_funcao_fk = ?, salario = ?, cep = ?, endereco = ?, bairro = ?, "
            + "cidade = ?, estado = ?, id_pais_fk = ? WHERE id_funcionario = ?";
    
    private static final String SQL_REMOVE = "UPDATE funcionario SET excluido = 1 WHERE id_funcionario = ?";
    
    private static final String SQL_FIND_ALL = "SELECT funcionario.id_funcionario, funcionario.nome, "
            + "funcionario.data_nascimento, funcionario.cpf, funcionario.rg, funcionario.email, funcionario.telefone, "
            + "funcionario.celular, funcionario.id_funcao_fk, funcionario.salario, funcionario.cep, funcionario.endereco, "
            + "funcionario.bairro, funcionario.cidade, funcionario.estado, funcionario.id_pais_fk, funcionario_funcao.nome,"
            + "pais.nome "
            + "FROM funcionario "
            + "INNER JOIN funcionario_funcao ON funcionario.id_funcao_fk = funcionario_funcao.id_funcao "
            + "INNER JOIN pais ON funcionario.id_pais_fk = pais.id_pais "
            + " WHERE funcionario.excluido = 0 AND funcionario.nome LIKE ? ORDER BY funcionario.nome";
    
    private static final String SQL_VERIFICA_FUNCIONARIO_REPETIDO = "SELECT cpf FROM funcionario "
            + "WHERE cpf = ? AND cpf != '   .   .   -  ' AND excluido = 0";

    @Override
    public int save(FuncionarioClasse func) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setString(1, func.getNome());
            ps.setString(2, func.getNascimento());
            ps.setString(3, func.getCpf());
            ps.setString(4, func.getRg());
            ps.setString(5, func.getEmail());
            ps.setString(6, func.getTelefone());
            ps.setString(7, func.getCelular());
            ps.setLong(8, func.getId_funcao());
            ps.setFloat(9, func.getSalario());
            ps.setString(10, func.getCep());
            ps.setString(11, func.getEndereco());
            ps.setString(12, func.getBairro());
            ps.setString(13, func.getCidade());
            ps.setString(14, func.getEstado());
            ps.setLong(15, func.getId_Pais());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(FuncionarioClasse func) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, func.getNome());
            ps.setString(2, func.getNascimento());
            ps.setString(3, func.getCpf());
            ps.setString(4, func.getRg());
            ps.setString(5, func.getEmail());
            ps.setString(6, func.getTelefone());
            ps.setString(7, func.getCelular());
            ps.setLong(8, func.getId_funcao());
            ps.setFloat(9, func.getSalario());
            ps.setString(10, func.getCep());
            ps.setString(11, func.getEndereco());
            ps.setString(12, func.getBairro());
            ps.setString(13, func.getCidade());
            ps.setString(14, func.getEstado());
            ps.setLong(15, func.getId_Pais());
            ps.setLong(16, func.getId());
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
    public List<FuncionarioClasse> findAll(String pesquisa) {
        List<FuncionarioClasse> funcs = new ArrayList<FuncionarioClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ps.setString(1, "%"+pesquisa+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                FuncionarioClasse func = new FuncionarioClasse();
                func.setId(rs.getLong("funcionario.id_funcionario"));
                func.setNome(rs.getString("funcionario.nome"));
                func.setNascimento(rs.getString("funcionario.data_nascimento"));
                func.setCpf(rs.getString("funcionario.cpf"));
                func.setRg(rs.getString("funcionario.rg"));
                func.setEmail(rs.getString("funcionario.email"));
                func.setTelefone(rs.getString("funcionario.telefone"));
                func.setCelular(rs.getString("funcionario.celular"));
                func.setId_funcao(rs.getLong("funcionario.id_funcao_fk"));
                func.setFuncao(rs.getString("funcionario_funcao.nome"));
                func.setSalario(rs.getFloat("funcionario.salario"));
                func.setCep(rs.getString("funcionario.cep"));
                func.setEndereco(rs.getString("funcionario.endereco"));
                func.setBairro(rs.getString("funcionario.bairro"));
                func.setCidade(rs.getString("funcionario.cidade"));
                func.setEstado(rs.getString("funcionario.estado"));
                func.setId_Pais(rs.getLong("funcionario.id_pais_fk"));
                func.setPais(rs.getString("pais.nome"));
                funcs.add(func);
            }
            rs.close();
            ps.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        
        return funcs;
    }

    @Override
    public boolean verificaFuncionarioRepetido(String cpf) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_FUNCIONARIO_REPETIDO);
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            while (rs.next()) {
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
