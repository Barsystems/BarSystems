/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.janela;

import empresa.classe.EmpresaClasse;
import empresa.controller.EmpresaController;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class EmpresaExcluir extends JDialog {
    
    public boolean excluir(EmpresaClasse empresa) {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir a empresa "+empresa.getRazao_social()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            EmpresaController cont = new EmpresaController();
            cont.removeEmpresa(empresa.getId());
            return true;
        }
        return false;
    }
    
}
