/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixa_movimentacao.dao;

import caixa_movimentacao.classe.CaixaMovimentacaoClasse;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface ICaixaMovimentacaoDAO {
    
    int save (CaixaMovimentacaoClasse classe);
    
    int update (CaixaMovimentacaoClasse classe);
    
    int remove (Long id);
    
    List<CaixaMovimentacaoClasse> findAll (Long id_caixa);
    
}
