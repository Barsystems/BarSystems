/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico.controller;

import java.util.List;
import servico.classe.ServicoClasse;
import servico.facade.ServicoFacade;

/**
 *
 * @author Marcos
 */
public class ServicoController {
    
    ServicoFacade facade;
    
    public ServicoController() {
        facade = new ServicoFacade();
    }
    
    public int addServico (ServicoClasse prod) {
        return facade.save(prod);
    }
    
    public int updateServico (ServicoClasse prod) {
        return facade.update(prod);
    }
    
    public int removeServico (Long id) {
        return facade.remove(id);
    }
    
    public List<ServicoClasse> findServico (String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
    public boolean verificaServicoRepetido (String nome) {
        return facade.verificaServicoRepetido(nome);
    }
    
}
