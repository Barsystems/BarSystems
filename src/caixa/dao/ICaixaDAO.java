/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixa.dao;

import caixa.classe.CaixaClasse;

/**
 *
 * @author Marcos
 */
public interface ICaixaDAO {
    
    int abrirCaixa (Long id_centro_custo);
    
    int fecharCaixa (Long id_caixa);
    
    boolean verificaCaixaAberto (Long id_centro_custo);
    
    CaixaClasse getCaixa(Long id_centro_custo);
    
}
