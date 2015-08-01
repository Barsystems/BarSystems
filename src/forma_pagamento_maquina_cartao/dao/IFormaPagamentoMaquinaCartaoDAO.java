/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_maquina_cartao.dao;

import forma_pagamento_maquina_cartao.classe.FormaPagamentoMaquinaCartaoClasse;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface IFormaPagamentoMaquinaCartaoDAO {
    
    int save (FormaPagamentoMaquinaCartaoClasse classe);
    
    int update (FormaPagamentoMaquinaCartaoClasse classe);
    
    int remove (Long id);
    
    List<FormaPagamentoMaquinaCartaoClasse> findAll (String pesquisa);
    
    boolean verificaMaquinaCartaoRepetida (String nome);
    
}
