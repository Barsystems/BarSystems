/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fornecedor.controller;

import fornecedor.classe.FornecedorClasse;
import fornecedor.facade.FornecedorFacade;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FornecedorController {
    
    FornecedorFacade facade;
    
    public FornecedorController() {
        facade = new FornecedorFacade();
    }
    
    public int addFornecedor(FornecedorClasse forn) {
        return facade.save(forn);
    }
    
    public int updateFornecedor(FornecedorClasse forn) {
        return facade.update(forn);
    }
    
    public int removeFornecedor(Long id) {
        return facade.remove(id);
    }
    
    public List<FornecedorClasse> findFornecedor(String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
    public boolean verificaFornecedorRepetidoRazaoSocial(String razao_social) {
        return facade.verificaFornecedorRepetidoRazaoSocial(razao_social);
    }
    
    public boolean verificaFornecedorRepetidoNomeFantasia(String nome_fantasia) {
        return facade.verificaFornecedorRepetidoNomeFantasia(nome_fantasia);
    }
    
}
