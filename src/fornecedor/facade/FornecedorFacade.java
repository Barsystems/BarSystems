/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fornecedor.facade;

import fornecedor.classe.FornecedorClasse;
import fornecedor.dao.FornecedorDAO;
import fornecedor.dao.IFornecedorDAO;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FornecedorFacade {
    
    IFornecedorDAO dao;
    
    public FornecedorFacade() {
        dao = new FornecedorDAO();
    }
    
    public int save(FornecedorClasse forn) {
        return dao.save(forn);
    }
    
    public int update(FornecedorClasse forn) {
        return dao.update(forn);
    }
    
    public int remove(Long id) {
        return dao.remove(id);
    }
    
    public List<FornecedorClasse> findAll(String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
    public boolean verificaFornecedorRepetidoRazaoSocial(String razao_social) {
        return dao.verificaFornecedorRepetidoRazaoSocial(razao_social);
    }
    
    public boolean verificaFornecedorRepetidoNomeFantasia(String nome_fantasia) {
        return dao.verificaFornecedorRepetidoNomeFantasia(nome_fantasia);
    }
    
}
