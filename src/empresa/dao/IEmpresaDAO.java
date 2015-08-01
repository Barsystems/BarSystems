/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.dao;

import empresa.classe.EmpresaClasse;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface IEmpresaDAO {
    
    int save (EmpresaClasse forn);
    
    int update (EmpresaClasse forn);
    
    int remove (Long id);
    
    List<EmpresaClasse> findAll(String pesquisa);
    
    boolean verificaEmpresaRepetidaRazaoSocial(String razao_social);
    
    boolean verificaEmpresaRepetidaNomeFantasia(String nome_fantasia);
    
}
