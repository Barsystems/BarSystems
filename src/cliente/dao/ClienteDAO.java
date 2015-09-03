/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.dao;

import cliente.classe.ClienteClasse;
import conexao_banco.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class ClienteDAO implements IClienteDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO cliente (nome, data_nascimento, rg, cpf, telefone, celular, email, "
            + "cep, endereco, bairro, cidade, estado, id_pais_fk, bloqueado, observacao) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_UPDATE = "UPDATE cliente SET nome = ?, data_nascimento = ?, rg = ?, cpf = ?, telefone = ?, "
            + "celular = ?, email = ?, cep = ?, cidade = ?, bairro = ?, cidade = ?, estado = ?, id_pais_fk = ?, bloqueado = ?, "
            + "observacao = ? WHERE id_cliente = ?";
    
    private static final String SQL_REMOVE = "UPDATE cliente SET excluido = 1 WHERE id_cliente = ?";
    
    private static final String SQL_FIND_ALL = "SELECT cliente.id_cliente, cliente.nome, cliente.data_nascimento, cliente.rg, "
            + "cliente.cpf, cliente.telefone, cliente.celular, cliente.email, cliente.cep, cliente.endereco, cliente.bairro, "
            + "cliente.cidade, cliente.estado, cliente.id_pais_fk, cliente.bloqueado, cliente.observacao, pais.nome "
            + "FROM cliente "
            + "INNER JOIN pais ON cliente.id_pais_fk = pais.id_pais "
            + "WHERE cliente.excluido = 0 AND cliente.nome LIKE ? ORDER BY cliente.nome";
    
    private static final String SQL_VERIFICA_CLIENTE_REPETIDO = "SELECT cpf FROM cliente "
            + "WHERE cpf = ? AND cpf != '   .   .   -  ' AND excluido = 0";

    @Override
    public int save(ClienteClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setString(1, classe.getNome());
            ps.setString(2, classe.getData_nascimento());
            ps.setString(3, classe.getRg());
            ps.setString(4, classe.getCpf());
            ps.setString(5, classe.getTelefone());
            ps.setString(6, classe.getCelular());
            ps.setString(7, classe.getEmail());
            ps.setString(8, classe.getCep());
            ps.setString(9, classe.getEndereco());
            ps.setString(10, classe.getBairro());
            ps.setString(11, classe.getCidade());
            ps.setString(12, classe.getEstado());
            ps.setLong(13, classe.getId_pais());
            ps.setBoolean(14, classe.isBloqueado());
            ps.setString(15, classe.getObservacao());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(ClienteClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, classe.getNome());
            ps.setString(2, classe.getData_nascimento());
            ps.setString(3, classe.getRg());
            ps.setString(4, classe.getCpf());
            ps.setString(5, classe.getTelefone());
            ps.setString(6, classe.getCelular());
            ps.setString(7, classe.getEmail());
            ps.setString(8, classe.getCep());
            ps.setString(9, classe.getEndereco());
            ps.setString(10, classe.getBairro());
            ps.setString(11, classe.getCidade());
            ps.setString(12, classe.getEstado());
            ps.setLong(13, classe.getId_pais());
            ps.setBoolean(14, classe.isBloqueado());
            ps.setString(15, classe.getObservacao());
            ps.setLong(16, classe.getId());
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
    public List<ClienteClasse> findAll(String pesquisa) {
        List<ClienteClasse> lista = new ArrayList<ClienteClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ps.setString(1, "%"+pesquisa+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ClienteClasse classe = new ClienteClasse();
                classe.setId(rs.getLong("cliente.id_cliente"));
                classe.setNome(rs.getString("cliente.nome"));
                classe.setData_nascimento(rs.getString("cliente.data_nascimento"));
                classe.setRg(rs.getString("cliente.rg"));
                classe.setCpf(rs.getString("cliente.cpf"));
                classe.setTelefone(rs.getString("cliente.telefone"));
                classe.setCelular(rs.getString("cliente.celular"));
                classe.setEmail(rs.getString("cliente.email"));
                classe.setCep(rs.getString("cliente.cep"));
                classe.setEndereco(rs.getString("cliente.endereco"));
                classe.setBairro(rs.getString("cliente.bairro"));
                classe.setCidade(rs.getString("cliente.cidade"));
                classe.setEstado(rs.getString("cliente.estado"));
                classe.setId_pais(rs.getLong("cliente.id_pais_fk"));
                classe.setPais(rs.getString("pais.nome"));
                classe.setBloqueado(rs.getBoolean("cliente.bloqueado"));
                classe.setObservacao(rs.getString("cliente.observacao"));
                
                lista.add(classe);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean verificaClienteRepetido(String cpf) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_CLIENTE_REPETIDO);
            ps.setString(1, cpf);
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
