/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario.controller;

import funcionario.classe.FuncionarioClasse;
import funcionario.facade.FuncionarioFacade;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FuncionarioController {
    
    FuncionarioFacade facade;
    
    public FuncionarioController() {
        facade = new FuncionarioFacade();
    }
    
    public int addFuncionario(FuncionarioClasse func) {
        return facade.save(func);
    }
    
    public int updateFuncionario(FuncionarioClasse func) {
        return facade.update(func);
    }
    
    public int removeFuncionario(Long id) {
        return facade.remove(id);
    }
    
    public List<FuncionarioClasse> findFuncionario(String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
    public boolean verificaFuncionarioRepetido(String cpf) {
        return facade.verificaFuncionarioRepetido(cpf);
    }
    
}
