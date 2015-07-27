/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.facade;

import java.util.List;
import produto.classe.ProdutoClasse;
import produto.dao.IProdutoDAO;
import produto.dao.ProdutoDAO;

/**
 *
 * @author Marcos
 */
public class ProdutoFacade {
    
    IProdutoDAO dao;
    
    public ProdutoFacade() {
        dao = new ProdutoDAO();
    }
    
    public int save(ProdutoClasse prod) {
        return dao.save(prod);
    }
    
    public int update (ProdutoClasse prod) {
        return dao.update(prod);
    }
    
    public int remove (Long id) {
        return dao.remove(id);
    }
    
    public List<ProdutoClasse> findAll(String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
    public boolean verificaProdutoRepetido(String nome) {
        return dao.verificaProdutoRepetido(nome);
    }
    
}
