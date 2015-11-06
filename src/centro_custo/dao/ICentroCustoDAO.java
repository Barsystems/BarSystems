/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_custo.dao;

import centro_custo.classe.CentroCustoClasse;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface ICentroCustoDAO {
    
    int save (CentroCustoClasse centro);
    
    int update (CentroCustoClasse centro);
    
    int remove (Long id);
    
    List<CentroCustoClasse> findAll (String pesquisa);
    
    boolean verificaCentroCustoRepetido (String nome);
    
    int diminuiSaldo (Long id, float saldo);
    
    int aumentaSaldo (Long id, float saldo);
    
    List<CentroCustoClasse> findCentroCustoDoResponsavel(Long id_usuario);
    
}
