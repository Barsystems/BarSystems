/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.dao;

import java.util.List;
import produto.classe.ProdutoClasse;

/**
 *
 * @author Marcos
 */
public interface IProdutoDAO {
    
    int save(ProdutoClasse prod);
    
    int update(ProdutoClasse prod);
    
    int remove (Long id);
    
    List<ProdutoClasse> findAll(String pesquisa);
    
    boolean verificaProdutoRepetido(String nome);
    
    List<ProdutoClasse> findProdutoVinculadoAServico(Long id_servico);
    
    int addVinculacaoProdutoAServico(Long id_servico, Long id_produto);
    
    int removeVinculacaoProdutoAServico(Long id_servico, Long id_produto);
    
}
