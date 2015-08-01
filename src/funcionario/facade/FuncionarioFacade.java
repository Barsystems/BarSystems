/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario.facade;

import funcionario.classe.FuncionarioClasse;
import funcionario.dao.FuncionarioDAO;
import funcionario.dao.IFuncionarioDAO;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FuncionarioFacade {
    
    IFuncionarioDAO dao;
    
    public FuncionarioFacade() {
        dao = new FuncionarioDAO();
    }
    
    public int save(FuncionarioClasse func) {
        return dao.save(func);
    }
    
    public int update(FuncionarioClasse func) {
        return dao.update(func);
    }
    
    public int remove(Long id) {
        return dao.remove(id);
    }
    
    public List<FuncionarioClasse> findAll(String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
    public boolean verificaFuncionarioRepetido(String cpf) {
        return dao.verificaFuncionarioRepetido(cpf);
    }
    
}
