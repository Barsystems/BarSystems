/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_taxa_cartao.dao;

import forma_pagamento_taxa_cartao.classe.FormaPagamentoTaxaCartaoClasse;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface IFormaPagamentoTaxaCartaoDAO {
    
    int save (FormaPagamentoTaxaCartaoClasse classe);
    
    int update (FormaPagamentoTaxaCartaoClasse classe);
    
    int remove (Long id);
    
    List<FormaPagamentoTaxaCartaoClasse> findAll (String pesquisa);
    
    boolean verificaTaxaCartaoExistenteMaquina (String maquina);
    
    boolean verificaTaxaCartaoExistenteFormaPagamento (String forma_pagamento);
    
}
