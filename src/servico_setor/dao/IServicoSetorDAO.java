/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico_setor.dao;

import java.util.List;
import servico_setor.classe.ServicoSetorClasse;

/**
 *
 * @author Marcos
 */
public interface IServicoSetorDAO {
    
    int save(ServicoSetorClasse classe);
    
    int update(ServicoSetorClasse classe);
    
    int remove (Long id);
    
    List<ServicoSetorClasse> refreshTable(String pesquisa);
    
    List<ServicoSetorClasse> findAll(String pesquisa);
    
    boolean verificaSetorRepetido(String nome);
    
}
