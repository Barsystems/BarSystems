/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_maquina_cartao.facade;

import forma_pagamento_maquina_cartao.classe.FormaPagamentoMaquinaCartaoClasse;
import forma_pagamento_maquina_cartao.dao.FormaPagamentoMaquinaCartaoDAO;
import forma_pagamento_maquina_cartao.dao.IFormaPagamentoMaquinaCartaoDAO;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoMaquinaCartaoFacade {
    
    IFormaPagamentoMaquinaCartaoDAO dao;
    
    public FormaPagamentoMaquinaCartaoFacade() {
        dao = new FormaPagamentoMaquinaCartaoDAO();
    }
    
    public int save(FormaPagamentoMaquinaCartaoClasse classe) {
        return dao.save(classe);
    }
    
    public int update(FormaPagamentoMaquinaCartaoClasse classe) {
        return dao.update(classe);
    }
    
    public int remove(Long id) {
        return dao.remove(id);
    }
    
    public List<FormaPagamentoMaquinaCartaoClasse> findAll(String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
    public boolean verificaMaquinaCartaoRepetida(String nome) {
        return dao.verificaMaquinaCartaoRepetida(nome);
    }
    
}
