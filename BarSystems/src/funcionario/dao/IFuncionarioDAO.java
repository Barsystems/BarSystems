/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario.dao;

import funcionario.classe.FuncionarioClasse;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface IFuncionarioDAO {
    
    int save (FuncionarioClasse func);
    
    int update (FuncionarioClasse func);
    
    int remove (Long id);
    
    List<FuncionarioClasse> findAll (String pesquisa);
    
    boolean verificaFuncionarioRepetido (String cpf);
    
}
