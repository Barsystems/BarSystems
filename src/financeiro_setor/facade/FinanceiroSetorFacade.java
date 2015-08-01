/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financeiro_setor.facade;

import financeiro_setor.classe.FinanceiroSetorClasse;
import financeiro_setor.dao.FinanceiroSetorDAO;
import financeiro_setor.dao.IFinanceiroSetorDAO;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FinanceiroSetorFacade {
    
    IFinanceiroSetorDAO dao;
    
    public FinanceiroSetorFacade() {
        dao = new FinanceiroSetorDAO();
    }
    
    public int save(FinanceiroSetorClasse func) {
        return dao.save(func);
    }
    
    public int update(FinanceiroSetorClasse func) {
        return dao.update(func);
    }
    
    public int remove(Long id) {
        return dao.remove(id);
    }
    
    public List<FinanceiroSetorClasse> findAll(String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
    public boolean verificaSetorRepetido(String nome) {
        return dao.verificaSetorRepetido(nome);
    }
    
}
