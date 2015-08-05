/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico_setor.facade;

import java.util.List;
import servico_setor.classe.ServicoSetorClasse;
import servico_setor.dao.IServicoSetorDAO;
import servico_setor.dao.ServicoSetorDAO;

/**
 *
 * @author Marcos
 */
public class ServicoSetorFacade {
    
    IServicoSetorDAO dao;
    
    public ServicoSetorFacade() {
        dao = new ServicoSetorDAO();
    }
    
    public int save(ServicoSetorClasse setor) {
        return dao.save(setor);
    }
    
    public int update(ServicoSetorClasse setor) {
        return dao.update(setor);
    }
    
    public int remove(Long id) {
        return dao.remove(id);
    }
    
    public List<ServicoSetorClasse> refreshTable(String pesquisa) {
        return dao.refreshTable(pesquisa);
    }
    
    public List<ServicoSetorClasse> findAll(String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
    public boolean verificaSetorRepetido(String nome) {
        return dao.verificaSetorRepetido(nome);
    }
    
}
