/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados_empresa.controller;

import dados_empresa.classe.DadosEmpresaClasse;
import dados_empresa.facade.DadosEmpresaFacade;

/**
 *
 * @author Marcos
 */
public class DadosEmpresaController {
    
    DadosEmpresaFacade facade;
    
    public DadosEmpresaController() {
        facade = new DadosEmpresaFacade();
    }
    
    public int addDadosEmpresa (DadosEmpresaClasse classe) {
        return facade.save(classe);
    }
    
    public int updateDadosEmpresa (DadosEmpresaClasse classe) {
        return facade.update(classe);
    }
    
    public DadosEmpresaClasse findEmpresa () {
        return facade.findEmpresa();
    }
    
}
