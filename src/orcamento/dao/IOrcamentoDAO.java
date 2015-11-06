/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orcamento.dao;

import java.util.Date;
import java.util.List;
import orcamento.classe.OrcamentoClasse;

/**
 *
 * @author Marcos
 */
public interface IOrcamentoDAO {
    
    int save (OrcamentoClasse classe);
    
    int update (OrcamentoClasse classe);
    
    int remove (Long id);
    
    List<OrcamentoClasse> findAll (String parametro_pesquisa, String pesquisa, Date data);
    
}
