/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_tipo.controller;

import forma_pagamento_tipo.classe.FormaPagamentoTipoClasse;
import forma_pagamento_tipo.facade.FormaPagamentoTipoFacade;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoTipoController {
    
    FormaPagamentoTipoFacade facade;
    
    public FormaPagamentoTipoController() {
        facade = new FormaPagamentoTipoFacade();
    }
    
    public List<FormaPagamentoTipoClasse> findTipo (String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
}
