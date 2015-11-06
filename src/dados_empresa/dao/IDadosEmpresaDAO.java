/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados_empresa.dao;

import dados_empresa.classe.DadosEmpresaClasse;

/**
 *
 * @author Marcos
 */
public interface IDadosEmpresaDAO {
    
    int save (DadosEmpresaClasse classe);
    
    int update (DadosEmpresaClasse classe);
    
    DadosEmpresaClasse findEmpresa ();
    
}
