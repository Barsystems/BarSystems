/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixa_movimentacao.controller;

import caixa_movimentacao.classe.CaixaMovimentacaoClasse;
import caixa_movimentacao.facade.CaixaMovimentacaoFacade;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class CaixaMovimentacaoController {
    
    private CaixaMovimentacaoFacade facade;
    
    public CaixaMovimentacaoController() {
        facade = new CaixaMovimentacaoFacade();
    }
    
    public int addMovimentacao (CaixaMovimentacaoClasse classe) {
        return facade.save(classe);
    }
    
    public int updateMovimentacao (CaixaMovimentacaoClasse classe) {
        return facade.update(classe);
    }
    
    public int removeMovimentacao (Long id) {
        return facade.remove(id);
    }
    
    public List<CaixaMovimentacaoClasse> findMovimentacao (Long id_caixa) {
        return facade.findAll(id_caixa);
    }
    
}
