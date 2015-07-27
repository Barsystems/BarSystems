/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto_setor.dao;

import java.util.List;
import produto_setor.classe.ProdutoSetorClasse;

/**
 *
 * @author Marcos
 */
public interface IProdutoSetorDAO {
    
    int save(ProdutoSetorClasse user);
    
    int update(ProdutoSetorClasse user);
    
    int remove (Long id);
    
    List<ProdutoSetorClasse> refreshTable(String pesquisa);
    
    List<ProdutoSetorClasse> findAll(String pesquisa);
    
    boolean verificaSetorRepetido(String nome);
    
}
