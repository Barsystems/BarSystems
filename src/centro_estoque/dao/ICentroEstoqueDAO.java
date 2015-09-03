/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_estoque.dao;

import centro_estoque.classe.CentroEstoqueClasse;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface ICentroEstoqueDAO {
    
    int save (CentroEstoqueClasse classe);
    
    int update (CentroEstoqueClasse classe);
    
    int remove (Long id);
    
    List<CentroEstoqueClasse> findAll (String pesquisa);
    
    boolean verificaCentroEstoqueRepetido (String nome);
    
}
