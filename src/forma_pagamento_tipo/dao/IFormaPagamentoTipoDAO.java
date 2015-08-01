/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_tipo.dao;

import forma_pagamento_tipo.classe.FormaPagamentoTipoClasse;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface IFormaPagamentoTipoDAO {
    
    List<FormaPagamentoTipoClasse> findAll (String pesquisa);
    
}
