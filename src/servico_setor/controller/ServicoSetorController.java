/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico_setor.controller;

import java.util.List;
import servico_setor.classe.ServicoSetorClasse;
import servico_setor.facade.ServicoSetorFacade;

/**
 *
 * @author Marcos
 */
public class ServicoSetorController {
    
    private ServicoSetorFacade facade;
    
    public ServicoSetorController() {
        facade = new ServicoSetorFacade();
    }
    
    public int addSetor(ServicoSetorClasse setor) {
        return facade.save(setor);
    }
    
    public int updateSetor(ServicoSetorClasse setor) {
        return facade.update(setor);
    }
    
    public int removeSetor(Long id) {
        return facade.remove(id);
    }
    
    public List<ServicoSetorClasse> refreshTabela(String pesquisa) {
        return facade.refreshTable(pesquisa);
    }
    
    public List<ServicoSetorClasse> findSetor(String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
    public boolean verificaSetorRepetido(String nome) {
        return facade.verificaSetorRepetido(nome);
    }
    
}
