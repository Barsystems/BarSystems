/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario_funcao.facade;

import funcionario_funcao.classe.FuncionarioFuncaoClasse;
import funcionario_funcao.dao.FuncionarioFuncaoDAO;
import funcionario_funcao.dao.IFuncionarioFuncaoDAO;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FuncionarioFuncaoFacade {
    
    IFuncionarioFuncaoDAO dao;
    
    public FuncionarioFuncaoFacade() {
        dao = new FuncionarioFuncaoDAO();
    }
    
    public int save(FuncionarioFuncaoClasse func) {
        return dao.save(func);
    }
    
    public int update(FuncionarioFuncaoClasse func) {
        return dao.update(func);
    }
    
    public int remove(Long id) {
        return dao.remove(id);
    }
    
    public List<FuncionarioFuncaoClasse> refreshTable(String pesquisa) {
        return dao.refreshTable(pesquisa);
    }
    
    public List<FuncionarioFuncaoClasse> findAll(String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
    public boolean verificaFuncaoRepetida(String nome) {
        return dao.verificaFuncaoRepetida(nome);
    }
    
}
