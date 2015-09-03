/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_taxa_cartao.facade;

import forma_pagamento_taxa_cartao.classe.FormaPagamentoTaxaCartaoClasse;
import forma_pagamento_taxa_cartao.dao.FormaPagamentoTaxaCartaoDAO;
import forma_pagamento_taxa_cartao.dao.IFormaPagamentoTaxaCartaoDAO;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoTaxaCartaoFacade {
    
    IFormaPagamentoTaxaCartaoDAO dao;
    
    public FormaPagamentoTaxaCartaoFacade() {
        dao = new FormaPagamentoTaxaCartaoDAO();
    }
    
    public int save(FormaPagamentoTaxaCartaoClasse classe) {
        return dao.save(classe);
    }
    
    public int update(FormaPagamentoTaxaCartaoClasse classe) {
        return dao.update(classe);
    }
    
    public int remove(Long id) {
        return dao.remove(id);
    }
    
    public List<FormaPagamentoTaxaCartaoClasse> findAll(String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
    public boolean verificaTaxaCartaoRepetida(Long id_forma_pagamento, Long id_maquina) {
        return dao.verificaTaxaCartaoRepetida(id_forma_pagamento, id_maquina);
    }
    
}
