/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico.dao;

import java.util.List;
import servico.classe.ServicoClasse;

/**
 *
 * @author Marcos
 */
public interface IServicoDAO {
    
    int save(ServicoClasse classe);
    
    int update(ServicoClasse classe);
    
    int remove (Long id);
    
    List<ServicoClasse> findAll(String pesquisa);
    
    boolean verificaServicoRepetido(String nome);
    
}
