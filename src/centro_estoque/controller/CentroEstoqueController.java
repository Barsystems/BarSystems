/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_estoque.controller;

import centro_estoque.classe.CentroEstoqueClasse;
import centro_estoque.facade.CentroEstoqueFacade;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class CentroEstoqueController {
    
    CentroEstoqueFacade facade;
    
    public CentroEstoqueController() {
        facade = new CentroEstoqueFacade();
    }
    
    public int addCentroEstoque (CentroEstoqueClasse classe) {
        return facade.save(classe);
    }
    
    public int updateCentroEstoque (CentroEstoqueClasse classe) {
        return facade.update(classe);
    }
    
    public int removeCentroEstoque (Long id) {
        return facade.remove(id);
    }
    
    public List<CentroEstoqueClasse> findCentroEstoque (String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
    public boolean verificaCentroEstoqueRepetido (String nome) {
        return facade.verificaCentroEstoqueRepetido(nome);
    }
    
}
