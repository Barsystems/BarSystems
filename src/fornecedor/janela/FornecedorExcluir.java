/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fornecedor.janela;

import fornecedor.classe.FornecedorClasse;
import fornecedor.controller.FornecedorController;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class FornecedorExcluir extends JDialog {
    
    public boolean excluir(FornecedorClasse forn) {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o fornecedor "+forn.getRazao_social()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            FornecedorController cont = new FornecedorController();
            cont.removeFornecedor(forn.getId());
            return true;
        }
        return false;
    }
    
}
