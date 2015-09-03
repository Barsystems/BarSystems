/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.dao;

import cliente.classe.ClienteClasse;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface IClienteDAO {
    
    int save (ClienteClasse classe);
    
    int update (ClienteClasse classe);
    
    int remove (Long id);
    
    List<ClienteClasse> findAll (String pesquisa);
    
    boolean verificaClienteRepetido (String cpf);
    
}
