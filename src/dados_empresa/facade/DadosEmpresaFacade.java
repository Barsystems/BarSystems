/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados_empresa.facade;

import dados_empresa.classe.DadosEmpresaClasse;
import dados_empresa.dao.DadosEmpresaDAO;
import dados_empresa.dao.IDadosEmpresaDAO;

/**
 *
 * @author Marcos
 */
public class DadosEmpresaFacade {
    
    IDadosEmpresaDAO dao;
    
    public DadosEmpresaFacade() {
        dao = new DadosEmpresaDAO();
    }
    
    public int save (DadosEmpresaClasse classe) {
        return dao.save(classe);
    }
    
    public int update (DadosEmpresaClasse classe) {
        return dao.update(classe);
    }
    
    public DadosEmpresaClasse findEmpresa () {
        return dao.findEmpresa();
    }
    
}
