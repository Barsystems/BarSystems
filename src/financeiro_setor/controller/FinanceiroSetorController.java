/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financeiro_setor.controller;

import financeiro_setor.classe.FinanceiroSetorClasse;
import financeiro_setor.facade.FinanceiroSetorFacade;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FinanceiroSetorController {
    
    FinanceiroSetorFacade facade;
    
    public FinanceiroSetorController() {
        facade = new FinanceiroSetorFacade();
    }
    
    public int addSetor(FinanceiroSetorClasse func) {
        return facade.save(func);
    }
    
    public int updateSetor(FinanceiroSetorClasse func) {
        return facade.update(func);
    }
    
    public int removeSetor(Long id) {
        return facade.remove(id);
    }
    
    public List<FinanceiroSetorClasse> findSetor(String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
    public boolean verificaSetorRepetido(String nome) {
        return facade.verificaSetorRepetido(nome);
    }
    
}
