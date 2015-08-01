/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario_funcao.controller;

import funcionario_funcao.classe.FuncionarioFuncaoClasse;
import funcionario_funcao.facade.FuncionarioFuncaoFacade;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FuncionarioFuncaoController {
    
    FuncionarioFuncaoFacade facade;
    
    public FuncionarioFuncaoController() {
        facade = new FuncionarioFuncaoFacade();
    }
    
    public int addFuncao(FuncionarioFuncaoClasse func) {
        return facade.save(func);
    }
    
    public int updateFuncao(FuncionarioFuncaoClasse func) {
        return facade.update(func);
    }
    
    public int removeFuncao(Long id) {
        return facade.remove(id);
    }
    
    public List<FuncionarioFuncaoClasse> refreshTabela(String pesquisa) {
        return facade.refreshTable(pesquisa);
    }
    
    public List<FuncionarioFuncaoClasse> findFuncao(String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
    public boolean verificaFuncaoRepetida(String nome) {
        return facade.verificaFuncaoRepetida(nome);
    }
    
}
