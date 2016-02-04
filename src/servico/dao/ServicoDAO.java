/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico.dao;

import conexao_banco.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import servico.classe.ServicoClasse;

/**
 *
 * @author Marcos
 */
public class ServicoDAO implements IServicoDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO servico (nome, id_setor_fk, valor_venda, valor_comissao) "
            + "VALUES (?, ?, ?, ?)";
    
    private static final String SQL_UPDATE = "UPDATE servico SET nome = ?, id_setor_fk = ?, valor_venda = ? "
            + "valor_comissao = ? WHERE id_servico = ?";
    
    private static final String SQL_REMOVE = "UPDATE servico SET excluido = 1 WHERE id_servico = ?";
    
    private static final String SQL_FIND_ALL = "SELECT servico.id_servico, servico.nome, servico.id_setor_fk, "
            + "servico.valor_venda, servico.valor_comissao, servico_setor.nome "
            + "FROM servico "
            + "INNER JOIN servico_setor ON servico.id_setor_fk = servico_setor.id_setor "
            + "WHERE servico.excluido = 0 AND servico.nome LIKE ? ORDER BY servico.nome";
    
    private static final String SQL_VERIFICA_SERVICO_REPETIDO = "SELECT nome FROM servico WHERE nome = ? AND excluido = 0";  
    
    @Override
    public int save(ServicoClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setString(1, classe.getNome());
            ps.setLong(2, classe.getId_setor());
            ps.setFloat(3, classe.getValor_venda());
            ps.setFloat(4, classe.getValor_comissao());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(ServicoClasse classe) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, classe.getNome());
            ps.setLong(2, classe.getId_setor());
            ps.setFloat(3, classe.getValor_venda());
            ps.setFloat(4, classe.getValor_comissao());
            ps.setLong(5, classe.getId());
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
    public List<ServicoClasse> findAll(String pesquisa) {
        List<ServicoClasse> lista = new ArrayList<ServicoClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ps.setString(1, "%"+pesquisa+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ServicoClasse classe = new ServicoClasse();
                classe.setId(rs.getLong("servico.id_servico"));
                classe.setNome(rs.getString("servico.nome"));
                classe.setId_setor(rs.getLong("servico.id_setor_fk"));
                classe.setSetor(rs.getString("servico_setor.nome"));
                classe.setValor_venda(rs.getFloat("servico.valor_venda"));
                classe.setValor_comissao(rs.getFloat("servico.valor_comissao"));
                
                lista.add(classe);
            }
            rs.close();
            ps.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        
        return lista;
    }

    @Override
    public boolean verificaServicoRepetido(String nome) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_SERVICO_REPETIDO);
            ps.setString(1, nome);
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
