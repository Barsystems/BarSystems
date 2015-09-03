/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_estoque.facade;

import centro_estoque.classe.CentroEstoqueClasse;
import centro_estoque.dao.CentroEstoqueDAO;
import centro_estoque.dao.ICentroEstoqueDAO;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class CentroEstoqueFacade {
    
    private ICentroEstoqueDAO dao;
    
    public CentroEstoqueFacade() {
        dao = new CentroEstoqueDAO();
    }
    
    public int save (CentroEstoqueClasse classe) {
        return dao.save(classe);
    }
    
    public int update (CentroEstoqueClasse classe) {
        return dao.update(classe);
    }
    
    public int remove (Long id) {
        return dao.remove(id);
    }
    
    public List<CentroEstoqueClasse> findAll (String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
    public boolean verificaCentroEstoqueRepetido (String nome) {
        return dao.verificaCentroEstoqueRepetido(nome);
    }
    
}
