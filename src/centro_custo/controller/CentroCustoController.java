/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_custo.controller;

import centro_custo.classe.CentroCustoClasse;
import centro_custo.facade.CentroCustoFacade;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class CentroCustoController {
    
    private CentroCustoFacade facade;
    
    public CentroCustoController() {
        facade = new CentroCustoFacade();
    }
    
    public int addCentroCusto (CentroCustoClasse centro) {
        return facade.save(centro);
    }
    
    public int updateCentroCusto (CentroCustoClasse centro) {
        return facade.update(centro);
    }
    
    public int removeCentroCusto (Long id) {
        return facade.remove(id);
    }
    
    public List<CentroCustoClasse> findCentroCusto (String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
    public boolean verificaCentroCustoRepetido (String nome) {
        return facade.verificaCentroCustoRepetido(nome);
    }
    
    public int diminuiSaldo (Long id, float saldo) {
        return facade.diminuiSaldo(id, saldo);
    }
    
    public int aumentaSaldo (Long id, float saldo) {
        return facade.aumentaSaldo(id, saldo);
    }
    
}
