/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.controller;

import java.util.List;
import produto.classe.ProdutoClasse;
import produto.facade.ProdutoFacade;

/**
 *
 * @author Marcos
 */
public class ProdutoController {
    
    ProdutoFacade facade;
    
    public ProdutoController() {
        facade = new ProdutoFacade();
    }
    
    public int addProduto (ProdutoClasse prod) {
        return facade.save(prod);
    }
    
    public int updateProduto (ProdutoClasse prod) {
        return facade.update(prod);
    }
    
    public int removeProduto (Long id) {
        return facade.remove(id);
    }
    
    public List<ProdutoClasse> findProduto (String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
    public boolean verificaProdutoRepetido (String nome) {
        return facade.verificaProdutoRepetido(nome);
    }
    
    public List<ProdutoClasse> findProdutoVinculadoAServico(Long id_servico) {
        return facade.findProdutoVinculadoAServico(id_servico);
    }
    
    public int addVinculacaoProdutoAServico(Long id_servico, Long id_produto) {
        return facade.addVinculacaoProdutoAServico(id_servico, id_produto);
    }
    
    public int removeVinculacaoProdutoAServico(Long id_servico, Long id_produto) {
        return facade.removeVinculacaoProdutoAServico(id_servico, id_produto);
    }
    
}
