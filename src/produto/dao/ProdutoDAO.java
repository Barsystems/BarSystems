/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.dao;

import conexao_banco.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import produto.classe.ProdutoClasse;

/**
 *
 * @author Marcos
 */
public class ProdutoDAO implements IProdutoDAO {
    
    ConexaoBanco banco = new ConexaoBanco();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    private static final String SQL_SAVE = "INSERT INTO produto (nome, id_setor_fk, tipo_medida, valor_compra, valor_venda, "
            + "valor_comissao) VALUES (?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_UPDATE = "UPDATE produto SET nome = ?, id_setor_fk = ?, tipo_medida = ?, valor_compra = ?, "
            + "valor_venda = ?, valor_comissao = ? WHERE id_produto = ?";
    
    private static final String SQL_REMOVE = "UPDATE produto SET excluido = 1 WHERE id_produto = ?";
    
    private static final String SQL_FIND_ALL = "SELECT produto.id_produto, produto.nome, produto.id_setor_fk, "
            + "produto.tipo_medida, produto.valor_compra, produto.valor_venda, produto.valor_comissao, produto_setor.nome "
            + "FROM produto "
            + "INNER JOIN produto_setor ON produto.id_setor_fk = produto_setor.id_setor "
            + "WHERE produto.excluido = 0 AND produto.nome LIKE ? ORDER BY produto.nome";
    
    private static final String SQL_VERIFICA_PRODUTO_REPETIDO = "SELECT nome FROM produto WHERE nome = ? AND excluido = 0";

    private static final String SQL_FIND_PRODUTO_VINCULADO_A_SERVICO = "SELECT servico.id_servico, produto.id_produto, "
            + "produto.nome, produto.id_setor_fk, produto.tipo_medida, produto.valor_compra, produto.valor_venda, "
            + "produto.valor_comissao, produto_setor.nome "
            + "FROM servico_produtos_vinculados "
            + "INNER JOIN servico ON servico_produtos_vinculados.id_servico_fk = servico.id_servico "
            + "INNER JOIN produto ON servico_produtos_vinculados.id_produto_fk = produto.id_produto "
            + "INNER JOIN produto_setor ON produto.id_setor_fk = produto_setor.id_setor "
            + "WHERE servico.id_servico = ? AND servico.excluido = 0 AND produto.excluido = 0 "
            + "ORDER BY produto.nome";
    
    private static final String SQL_ADD_VINCULACAO_PRODUTO_A_SERVICO = "INSERT INTO servico_produtos_vinculados "
            + "(id_servico_fk, id_produto_fk) VALUES (?, ?)";
    
    private static final String SQL_REMOVE_VINCULACAO_PRODUTO_A_SERVICO = "DELETE FROM servico_produtos_vinculados "
            + "WHERE id_servico_fk = ? AND id_produto_fk = ?";
    
    @Override
    public int save(ProdutoClasse produto) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_SAVE);
            ps.setString(1, produto.getNome());
            ps.setLong(2, produto.getId_setor());
            ps.setString(3, produto.getTipo_medida());
            ps.setFloat(4, produto.getValor_compra());
            ps.setFloat(5, produto.getValor_venda());
            ps.setFloat(6, produto.getValor_comissao());
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(ProdutoClasse produto) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, produto.getNome());
            ps.setLong(2, produto.getId_setor());
            ps.setString (3, produto.getTipo_medida());
            ps.setFloat(4, produto.getValor_compra());
            ps.setFloat(5, produto.getValor_venda());
            ps.setFloat(6, produto.getValor_comissao());
            ps.setLong(7, produto.getId());
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
    public List<ProdutoClasse> findAll(String pesquisa) {
        List<ProdutoClasse> produtos = new ArrayList<ProdutoClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_ALL);
            ps.setString(1, "%"+pesquisa+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ProdutoClasse produto = new ProdutoClasse();
                produto.setId(rs.getLong("produto.id_produto"));
                produto.setNome(rs.getString("produto.nome"));
                produto.setId_setor(rs.getLong("produto.id_setor_fk"));
                produto.setTipo_medida(rs.getString("produto.tipo_medida"));
                produto.setSetor(rs.getString("produto_setor.nome"));
                produto.setValor_compra(rs.getFloat("produto.valor_compra"));
                produto.setValor_venda(rs.getFloat("produto.valor_venda"));
                produto.setValor_comissao(rs.getFloat("produto.valor_comissao"));
                
                produtos.add(produto);
            }
            rs.close();
            ps.close();
            conn.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        
        return produtos;
    }

    @Override
    public boolean verificaProdutoRepetido(String nome) {
        boolean flag = false;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_VERIFICA_PRODUTO_REPETIDO);
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
    
    @Override
    public List<ProdutoClasse> findProdutoVinculadoAServico(Long id_servico) {
        List<ProdutoClasse> lista = new ArrayList<ProdutoClasse>();
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_FIND_PRODUTO_VINCULADO_A_SERVICO);
            ps.setLong(1, id_servico);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProdutoClasse classe = new ProdutoClasse();
                classe.setId(rs.getLong("produto.id_produto"));
                classe.setNome(rs.getString("produto.nome"));
                classe.setId_setor(rs.getLong("produto.id_setor_fk"));
                classe.setSetor(rs.getString("produto_setor.nome"));
                classe.setTipo_medida(rs.getString("produto.tipo_medida"));
                classe.setValor_compra(rs.getFloat("produto.valor_compra"));
                classe.setValor_venda(rs.getFloat("produto.valor_venda"));
                classe.setValor_comissao(rs.getFloat("produto.valor_comissao"));
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
    public int addVinculacaoProdutoAServico(Long id_servico, Long id_produto) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_ADD_VINCULACAO_PRODUTO_A_SERVICO);
            ps.setLong(1, id_servico);
            ps.setLong(2, id_produto);
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public int removeVinculacaoProdutoAServico(Long id_servico, Long id_produto) {
        int result = 0;
        try {
            conn = banco.getConexaoMySQL();
            ps = conn.prepareStatement(SQL_REMOVE_VINCULACAO_PRODUTO_A_SERVICO);
            ps.setLong(1, id_servico);
            ps.setLong(2, id_produto);
            result = ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
