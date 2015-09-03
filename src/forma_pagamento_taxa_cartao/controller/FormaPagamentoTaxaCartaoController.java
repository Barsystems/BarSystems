/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_taxa_cartao.controller;

import forma_pagamento_taxa_cartao.classe.FormaPagamentoTaxaCartaoClasse;
import forma_pagamento_taxa_cartao.facade.FormaPagamentoTaxaCartaoFacade;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoTaxaCartaoController {
    
    FormaPagamentoTaxaCartaoFacade facade;
    
    public FormaPagamentoTaxaCartaoController() {
        facade = new FormaPagamentoTaxaCartaoFacade();
    }
    
    public int addTaxaCartao(FormaPagamentoTaxaCartaoClasse classe) {
        return facade.save(classe);
    }
    
    public int updateTaxaCartao(FormaPagamentoTaxaCartaoClasse classe) {
        return facade.update(classe);
    }
    
    public int removeTaxaCartao(Long id) {
        return facade.remove(id);
    }
    
    public List<FormaPagamentoTaxaCartaoClasse> findTaxaCartao(String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
    public boolean verificaTaxaCartaoRepetida(Long id_forma_pagamento, Long id_maquina) {
        return facade.verificaTaxaCartaoRepetida(id_forma_pagamento, id_maquina);
    }
    
}
