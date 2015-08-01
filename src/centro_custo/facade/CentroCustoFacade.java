/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_custo.facade;

import centro_custo.classe.CentroCustoClasse;
import centro_custo.dao.CentroCustoDAO;
import centro_custo.dao.ICentroCustoDAO;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class CentroCustoFacade {
    
    private ICentroCustoDAO dao;
    
    public CentroCustoFacade() {
        dao = new CentroCustoDAO();
    }
    
    public int save (CentroCustoClasse centro) {
        return dao.save(centro);
    }
    
    public int update (CentroCustoClasse centro) {
        return dao.update(centro);
    }
    
    public int remove (Long id) {
        return dao.remove(id);
    }
    
    public List<CentroCustoClasse> findAll (String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
    public boolean verificaCentroCustoRepetido (String nome) {
        return dao.verificaCentroCustoRepetido(nome);
    }
    
    public int diminuiSaldo (Long id, float saldo) {
        return dao.diminuiSaldo(id, saldo);
    }
    
    public int aumentaSaldo (Long id, float saldo) {
        return dao.aumentaSaldo(id, saldo);
    }
    
}
