/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario_funcao.dao;

import funcionario_funcao.classe.FuncionarioFuncaoClasse;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface IFuncionarioFuncaoDAO {
    
    int save (FuncionarioFuncaoClasse funcao);
    
    int update (FuncionarioFuncaoClasse funcao);
    
    int remove (Long id);
    
    List<FuncionarioFuncaoClasse> refreshTable(String pesquisa);
    
    List<FuncionarioFuncaoClasse> findAll(String pesquisa);
    
    boolean verificaFuncaoRepetida (String funcao);
    
}
