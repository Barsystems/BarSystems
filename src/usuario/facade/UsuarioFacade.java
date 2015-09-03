/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.facade;

import java.util.List;
import usuario.classe.UsuarioClasse;
import usuario.dao.IUsuarioDAO;
import usuario.dao.UsuarioDAO;

/**
 *
 * @author Marcos
 */
public class UsuarioFacade {
    
    IUsuarioDAO dao;
    
    public UsuarioFacade() {
        dao = new UsuarioDAO();
    }
    
    public int save(UsuarioClasse user) {
        return dao.save(user);
    }
    
    public int update(UsuarioClasse user) {
        return dao.update(user);
    }
    
    public int remove(Long id) {
        return dao.remove(id);
    }
    
    public List<UsuarioClasse> findAll(String pesquisa) {
        return dao.findAll(pesquisa);
    }
    
    public boolean validaUsuario(String usuario, String senha) {
        return dao.validaUsuario(usuario, senha);
    }
    
    public UsuarioClasse getUsuario(String usuario) {
        return dao.getUsuario(usuario);
    }
    
    public boolean verificaUsuarioRepetido(String nome) {
        return dao.verificaUsuarioRepetido(nome);
    }
    
    public List<UsuarioClasse> findUsuarioResponsavelCentroCusto(Long id_centro_custo) {
        return dao.findUsuarioResponsavelCentroCusto(id_centro_custo);
    }
    
    public int addResponsavelCentroCusto (Long id_centro_custo, Long id_usuario) {
        return dao.addResponsavelCentroCusto(id_centro_custo, id_usuario);
    }
    
    public int removeResponsavelCentroCusto (Long id_centro_custo, Long id_usuario) {
        return dao.removeResponsavelCentroCusto(id_centro_custo, id_usuario);
    }
    
}
