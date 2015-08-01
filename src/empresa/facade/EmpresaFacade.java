/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.facade;

import empresa.classe.EmpresaClasse;
import empresa.dao.EmpresaDAO;
import empresa.dao.IEmpresaDAO;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class EmpresaFacade {
    
    IEmpresaDAO dao;
    
    public EmpresaFacade() {
        dao = new EmpresaDAO();
    }
    
    public int save(EmpresaClasse forn) {
        return dao.save(forn);
    }
    
    public int update(EmpresaClasse forn) {
        return dao.update(forn);
    }
    
    public int remove(Long id) {
        return dao.remove(id);
    }
    
    public List<EmpresaClasse> findAll(String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
    public boolean verificaEmpresaRepetidaRazaoSocial(String razao_social) {
        return dao.verificaEmpresaRepetidaRazaoSocial(razao_social);
    }
    
    public boolean verificaEmpresaRepetidaNomeFantasia(String nome_fantasia) {
        return dao.verificaEmpresaRepetidaNomeFantasia(nome_fantasia);
    }
    
}
