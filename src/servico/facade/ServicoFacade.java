/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico.facade;

import java.util.List;
import servico.classe.ServicoClasse;
import servico.dao.IServicoDAO;
import servico.dao.ServicoDAO;

/**
 *
 * @author Marcos
 */
public class ServicoFacade {
    
    IServicoDAO dao;
    
    public ServicoFacade() {
        dao = new ServicoDAO();
    }
    
    public int save(ServicoClasse prod) {
        return dao.save(prod);
    }
    
    public int update (ServicoClasse prod) {
        return dao.update(prod);
    }
    
    public int remove (Long id) {
        return dao.remove(id);
    }
    
    public List<ServicoClasse> findAll(String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
    public boolean verificaServicoRepetido(String nome) {
        return dao.verificaServicoRepetido(nome);
    }
    
}
