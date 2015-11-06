/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixa.controller;

import caixa.classe.CaixaClasse;
import caixa.facade.CaixaFacade;

/**
 *
 * @author Marcos
 */
public class CaixaController {
    
    private CaixaFacade facade;
    
    public CaixaController() {
        facade = new CaixaFacade();
    }
    
    public int abrirCaixa (Long id_centro_custo) {
        return facade.abrirCaixa(id_centro_custo);
    }
    
    public int fecharCaixa (Long id_caixa) {
        return facade.fecharCaixa(id_caixa);
    }
    
    public boolean verificaCaixaAberto(Long id_centro_custo) {
        return facade.verificaCaixaAberto(id_centro_custo);
    }
    
    public CaixaClasse getCaixa (Long id_centro_custo) {
        return facade.getCaixa(id_centro_custo);
    }
    
}
