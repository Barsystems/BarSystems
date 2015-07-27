/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.dao;

import java.util.List;
import usuario.classe.UsuarioClasse;

/**
 *
 * @author Marcos
 */
public interface IUsuarioDAO {
    
    int save(UsuarioClasse user);
    
    int update(UsuarioClasse user);
    
    int remove (Long id);
    
    List<UsuarioClasse> findAll(String pesquisa);
    
    boolean validaUsuario(String usuario, String senha);
    
    UsuarioClasse getUsuario(String usuario);
    
    boolean verificaUsuarioRepetido(String nome);
    
}
