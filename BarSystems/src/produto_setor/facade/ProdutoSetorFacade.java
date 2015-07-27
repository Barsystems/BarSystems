/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto_setor.facade;

import java.util.List;
import produto_setor.classe.ProdutoSetorClasse;
import produto_setor.dao.IProdutoSetorDAO;
import produto_setor.dao.ProdutoSetorDAO;

/**
 *
 * @author Marcos
 */
public class ProdutoSetorFacade {
    
    IProdutoSetorDAO dao;
    
    public ProdutoSetorFacade() {
        dao = new ProdutoSetorDAO();
    }
    
    public int save(ProdutoSetorClasse setor) {
        return dao.save(setor);
    }
    
    public int update(ProdutoSetorClasse setor) {
        return dao.update(setor);
    }
    
    public int remove(Long id) {
        return dao.remove(id);
    }
    
    public List<ProdutoSetorClasse> refreshTable(String pesquisa) {
        return dao.refreshTable(pesquisa);
    }
    
    public List<ProdutoSetorClasse> findAll(String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
    public boolean verificaSetorRepetido(String nome) {
        return dao.verificaSetorRepetido(nome);
    }
    
}
