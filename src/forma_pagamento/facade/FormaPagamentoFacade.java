/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento.facade;

import forma_pagamento.classe.FormaPagamentoClasse;
import forma_pagamento.dao.FormaPagamentoDAO;
import forma_pagamento.dao.IFormaPagamentoDAO;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoFacade {
    
    IFormaPagamentoDAO dao;
    
    public FormaPagamentoFacade() {
        dao = new FormaPagamentoDAO();
    }
    
    public int save(FormaPagamentoClasse classe) {
        return dao.save(classe);
    }
    
    public int update(FormaPagamentoClasse classe) {
        return dao.update(classe);
    }
    
    public int remove(Long id) {
        return dao.remove(id);
    }
    
    public List<FormaPagamentoClasse> findAll(String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
    public List<FormaPagamentoClasse> findFormaPagamentoTipoEspecifico(String pesquisa) {
        return dao.findFormaPagamentoTipoEspecifico(pesquisa);
    }
    
    public boolean verificaFormaPagamentoRepetida(String nome) {
        return dao.verificaFormaPagamentoRepetida(nome);
    }
    
}
