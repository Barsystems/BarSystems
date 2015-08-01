/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_maquina_cartao.controller;

import forma_pagamento_maquina_cartao.classe.FormaPagamentoMaquinaCartaoClasse;
import forma_pagamento_maquina_cartao.facade.FormaPagamentoMaquinaCartaoFacade;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoMaquinaCartaoController {
    
    FormaPagamentoMaquinaCartaoFacade facade;
    
    public FormaPagamentoMaquinaCartaoController() {
        facade = new FormaPagamentoMaquinaCartaoFacade();
    }
    
    public int addMaquinaCartao(FormaPagamentoMaquinaCartaoClasse classe) {
        return facade.save(classe);
    }
    
    public int updateMaquinaCartao(FormaPagamentoMaquinaCartaoClasse classe) {
        return facade.update(classe);
    }
    
    public int removeMaquinaCartao(Long id) {
        return facade.remove(id);
    }
    
    public List<FormaPagamentoMaquinaCartaoClasse> findMaquinaCartao(String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
    public boolean verificaMaquinaCartaoRepetida(String nome) {
        return facade.verificaMaquinaCartaoRepetida(nome);
    }
    
}
