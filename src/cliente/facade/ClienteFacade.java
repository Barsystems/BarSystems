/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.facade;

import cliente.classe.ClienteClasse;
import cliente.dao.ClienteDAO;
import cliente.dao.IClienteDAO;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class ClienteFacade {
    
    private IClienteDAO dao;
    
    public ClienteFacade() {
        dao = new ClienteDAO();
    }
    
    public int save (ClienteClasse classe) {
        return dao.save(classe);
    }
    
    public int update (ClienteClasse classe) {
        return dao.update(classe);
    }
    
    public int remove (Long id) {
        return dao.remove(id);
    }
    
    public List<ClienteClasse> findAll (String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
    public boolean verificaClienteRepetido (String cpf) {
        return dao.verificaClienteRepetido(cpf);
    }
    
}
