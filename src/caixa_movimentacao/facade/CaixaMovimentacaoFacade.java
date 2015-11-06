/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixa_movimentacao.facade;

import caixa_movimentacao.classe.CaixaMovimentacaoClasse;
import caixa_movimentacao.dao.CaixaMovimentacaoDAO;
import caixa_movimentacao.dao.ICaixaMovimentacaoDAO;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class CaixaMovimentacaoFacade {
    
    private ICaixaMovimentacaoDAO dao;
    
    public CaixaMovimentacaoFacade() {
        dao = new CaixaMovimentacaoDAO();
    }
    
    public int save (CaixaMovimentacaoClasse classe) {
        return dao.save(classe);
    }
    
    public int update (CaixaMovimentacaoClasse classe) {
        return dao.update(classe);
    }
    
    public int remove (Long id) {
        return dao.remove(id);
    }
    
    public List<CaixaMovimentacaoClasse> findAll (Long id_caixa) {
        return dao.findAll(id_caixa);
    }
    
}
