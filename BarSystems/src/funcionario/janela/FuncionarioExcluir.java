/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario.janela;

import funcionario.classe.FuncionarioClasse;
import funcionario.controller.FuncionarioController;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class FuncionarioExcluir {
    
    public boolean excluir(FuncionarioClasse func) {
        boolean result = false;
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o funcionário "+func.getNome()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            FuncionarioController cont = new FuncionarioController();
            cont.removeFuncionario(func.getId());
            result = true;
        }
        return result;
    }
    
}
