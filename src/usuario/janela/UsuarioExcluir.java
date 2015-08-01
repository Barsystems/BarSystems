/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.janela;

import javax.swing.JOptionPane;
import usuario.classe.UsuarioClasse;
import usuario.controller.UsuarioController;

/**
 *
 * @author Marcos
 */
public class UsuarioExcluir {
    
    public boolean excluir(UsuarioClasse user) {
        boolean result = false;
        if (user.getNome().equals("Administrador")) {
            return result;
        } else if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o usuário "+user.getNome()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            UsuarioController cont = new UsuarioController();
            cont.removeUsuario(user.getId());
            result = true;
        }
        return result;
    }
    
}
