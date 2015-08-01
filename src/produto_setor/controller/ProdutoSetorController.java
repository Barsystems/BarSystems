/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto_setor.controller;

import java.util.List;
import produto_setor.classe.ProdutoSetorClasse;
import produto_setor.facade.ProdutoSetorFacade;

/**
 *
 * @author Marcos
 */
public class ProdutoSetorController {
    
    private ProdutoSetorFacade facade;
    
    public ProdutoSetorController() {
        facade = new ProdutoSetorFacade();
    }
    
    public int addSetor(ProdutoSetorClasse setor) {
        return facade.save(setor);
    }
    
    public int updateSetor(ProdutoSetorClasse setor) {
        return facade.update(setor);
    }
    
    public int removeSetor(Long id) {
        return facade.remove(id);
    }
    
    public List<ProdutoSetorClasse> refreshTabela(String pesquisa) {
        return facade.refreshTable(pesquisa);
    }
    
    public List<ProdutoSetorClasse> findSetor(String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
    public boolean verificaSetorRepetido(String nome) {
        return facade.verificaSetorRepetido(nome);
    }
    
}
