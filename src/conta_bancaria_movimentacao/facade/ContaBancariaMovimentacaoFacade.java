/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conta_bancaria_movimentacao.facade;

import conta_bancaria_movimentacao.classe.ContaBancariaMovimentacaoClasse;
import conta_bancaria_movimentacao.dao.ContaBancariaMovimentacaoDAO;
import conta_bancaria_movimentacao.dao.IContaBancariaMovimentacaoDAO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class ContaBancariaMovimentacaoFacade {
    
    private IContaBancariaMovimentacaoDAO dao;
    
    public ContaBancariaMovimentacaoFacade() {
        dao = new ContaBancariaMovimentacaoDAO();
    }
    
    public int save (ContaBancariaMovimentacaoClasse classe) {
        return dao.save(classe);
    }
    
    public int update (ContaBancariaMovimentacaoClasse classe) {
        return dao.update(classe);
    }
    
    public int remove (Long id) {
        return dao.remove(id);
    }
    
    public List<ContaBancariaMovimentacaoClasse> findAll (Long id_centro_custo, Date data_inicial, Date data_final) {
        return dao.findAll(id_centro_custo, data_inicial, data_final);
    }
    
}
