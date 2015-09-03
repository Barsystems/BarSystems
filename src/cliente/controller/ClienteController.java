/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.controller;

import cliente.classe.ClienteClasse;
import cliente.facade.ClienteFacade;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class ClienteController {
    
    private ClienteFacade facade;
    
    public ClienteController() {
        facade = new ClienteFacade();
    }
    
    public int addCliente (ClienteClasse classe) {
        return facade.save(classe);
    }
    
    public int updateCliente (ClienteClasse classe) {
        return facade.update(classe);
    }
    
    public int removeCliente (Long id) {
        return facade.remove(id);
    }
    
    public List<ClienteClasse> findCliente (String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
    public boolean verificaClienteRepetido (String cpf) {
        return facade.verificaClienteRepetido(cpf);
    }
    
}
