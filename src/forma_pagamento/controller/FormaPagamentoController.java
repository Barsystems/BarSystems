/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento.controller;

import forma_pagamento.classe.FormaPagamentoClasse;
import forma_pagamento.facade.FormaPagamentoFacade;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoController {
    
    FormaPagamentoFacade facade;
    
    public FormaPagamentoController() {
        facade = new FormaPagamentoFacade();
    }
    
    public int addFormaPagamento(FormaPagamentoClasse classe) {
        return facade.save(classe);
    }
    
    public int updateFormaPagamento(FormaPagamentoClasse classe) {
        return facade.update(classe);
    }
    
    public int removeFormaPagamento(Long id) {
        return facade.remove(id);
    }
    
    public List<FormaPagamentoClasse> findFormaPagamento(String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
    public boolean verificaFormaPagamentoRepetida(String nome) {
        return facade.verificaFormaPagamentoRepetida(nome);
    }
    
}
