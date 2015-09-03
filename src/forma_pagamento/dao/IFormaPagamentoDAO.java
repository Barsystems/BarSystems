/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento.dao;

import forma_pagamento.classe.FormaPagamentoClasse;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface IFormaPagamentoDAO {
    
    int save (FormaPagamentoClasse classe);
    
    int update (FormaPagamentoClasse classe);
    
    int remove (Long id);
    
    List<FormaPagamentoClasse> findAll (String pesquisa);
    
    List<FormaPagamentoClasse> findFormaPagamentoTipoEspecifico (String pesquisa);
    
    boolean verificaFormaPagamentoRepetida (String nome);
    
}
