/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.controller;

import java.util.List;
import usuario.classe.UsuarioClasse;
import usuario.facade.UsuarioFacade;

/**
 *
 * @author Marcos
 */
public class UsuarioController {
    
    UsuarioFacade facade;
    
    public UsuarioController() {
        facade = new UsuarioFacade();
    }
    
    public int addUsuario(UsuarioClasse user) {
        return facade.save(user);
    }
    
    public int updateUsuario(UsuarioClasse user) {
        return facade.update(user);
    }
    
    public int removeUsuario(Long id) {
        return facade.remove(id);
    }
    
    public List<UsuarioClasse> findUsuario(String pesquisa) {
        return facade.findAll(pesquisa);
    }
    
    public boolean validaUsuario(String usuario, String senha) {
        return facade.validaUsuario(usuario, senha);
    }
    
    public UsuarioClasse getUsuario(String usuario) {
        return facade.getUsuario(usuario);
    }
    
    public boolean verificaUsuarioRepetido(String nome) {
        return facade.verificaUsuarioRepetido(nome);
    }
    
}
