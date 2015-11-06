/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixa.facade;

import caixa.classe.CaixaClasse;
import caixa.dao.CaixaDAO;
import caixa.dao.ICaixaDAO;

/**
 *
 * @author Marcos
 */
public class CaixaFacade {
    
    private ICaixaDAO dao;
    
    public CaixaFacade() {
        dao = new CaixaDAO();
    }
    
    public int abrirCaixa (Long id_centro_custo) {
        return dao.abrirCaixa(id_centro_custo);
    }
    
    public int fecharCaixa (Long id_caixa) {
        return dao.fecharCaixa(id_caixa);
    }
    
    public boolean verificaCaixaAberto(Long id_centro_custo) {
        return dao.verificaCaixaAberto(id_centro_custo);
    }
    
    public CaixaClasse getCaixa (Long id_centro_custo) {
        return dao.getCaixa(id_centro_custo);
    }
    
}
