/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fornecedor.dao;

import fornecedor.classe.FornecedorClasse;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface IFornecedorDAO {
    
    int save (FornecedorClasse forn);
    
    int update (FornecedorClasse forn);
    
    int remove (Long id);
    
    List<FornecedorClasse> findAll(String pesquisa);
    
    boolean verificaFornecedorRepetidoRazaoSocial(String razao_social);
    
    boolean verificaFornecedorRepetidoNomeFantasia(String nome_fantasia);
    
}
