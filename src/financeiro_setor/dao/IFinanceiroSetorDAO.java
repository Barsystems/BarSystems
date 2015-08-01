/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financeiro_setor.dao;

import financeiro_setor.classe.FinanceiroSetorClasse;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface IFinanceiroSetorDAO {
    
    int save (FinanceiroSetorClasse setor);
    
    int update (FinanceiroSetorClasse setor);
    
    int remove (Long id);
    
    List<FinanceiroSetorClasse> findAll(String pesquisa);
    
    boolean verificaSetorRepetido (String setor);
    
}
