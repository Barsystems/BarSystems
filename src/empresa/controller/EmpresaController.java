/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controller;

import empresa.classe.EmpresaClasse;
import empresa.facade.EmpresaFacade;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class EmpresaController {
    
    EmpresaFacade facade;
    
    public EmpresaController() {
        facade = new EmpresaFacade();
    }
    
    public int addEmpresa(EmpresaClasse forn) {
        return facade.save(forn);
    }
    
    public int updateEmpresa(EmpresaClasse forn) {
        return facade.update(forn);
    }
    
    public int removeEmpresa(Long id) {
        return facade.remove(id);
    }
    
    public List<EmpresaClasse> findEmpresa(String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
    public boolean verificaEmpresaRepetidaRazaoSocial(String razao_social) {
        return facade.verificaEmpresaRepetidaRazaoSocial(razao_social);
    }
    
    public boolean verificaEmpresaRepetidaNomeFantasia(String nome_fantasia) {
        return facade.verificaEmpresaRepetidaNomeFantasia(nome_fantasia);
    }
    
}
