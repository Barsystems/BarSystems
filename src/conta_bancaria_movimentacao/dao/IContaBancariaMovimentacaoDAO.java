/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conta_bancaria_movimentacao.dao;

import conta_bancaria_movimentacao.classe.ContaBancariaMovimentacaoClasse;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface IContaBancariaMovimentacaoDAO {
    
    int save (ContaBancariaMovimentacaoClasse classe);
    
    int update (ContaBancariaMovimentacaoClasse classe);
    
    int remove (Long id);
    
    List<ContaBancariaMovimentacaoClasse> findAll (Long id_centro_custo, Date data_inicial, Date data_final);
    
}
