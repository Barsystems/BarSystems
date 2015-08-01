/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_tipo.facade;

import forma_pagamento_tipo.classe.FormaPagamentoTipoClasse;
import forma_pagamento_tipo.dao.FormaPagamentoTipoDAO;
import forma_pagamento_tipo.dao.IFormaPagamentoTipoDAO;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoTipoFacade {
    
    IFormaPagamentoTipoDAO dao;
    
    public FormaPagamentoTipoFacade() {
        dao = new FormaPagamentoTipoDAO();
    }
    
    public List<FormaPagamentoTipoClasse> findAll (String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
}
