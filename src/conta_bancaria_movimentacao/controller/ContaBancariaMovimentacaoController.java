/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conta_bancaria_movimentacao.controller;

import conta_bancaria_movimentacao.classe.ContaBancariaMovimentacaoClasse;
import conta_bancaria_movimentacao.facade.ContaBancariaMovimentacaoFacade;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class ContaBancariaMovimentacaoController {
    
    private ContaBancariaMovimentacaoFacade facade;
    
    public ContaBancariaMovimentacaoController() {
        facade = new ContaBancariaMovimentacaoFacade();
    }
    
    public int addMovimentacao (ContaBancariaMovimentacaoClasse classe) {
        return facade.save(classe);
    }
    
    public int updateMovimentacao (ContaBancariaMovimentacaoClasse classe) {
        return facade.update(classe);
    }
    
    public int removeMovimentacao (Long id) {
        return facade.remove(id);
    }
    
    public List<ContaBancariaMovimentacaoClasse> findMovimentacao (Long id_centro_custo, Date data_inicial, Date data_final) {
        return facade.findAll(id_centro_custo, data_inicial, data_final);
    }
    
}
