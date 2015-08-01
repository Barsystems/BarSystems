/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financeiro_setor.janela;

import financeiro_setor.classe.FinanceiroSetorClasse;
import financeiro_setor.controller.FinanceiroSetorController;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class FinanceiroSetorExcluir {
    
    public boolean excluir(FinanceiroSetorClasse setor) {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o setor financeiro "+setor.getNome()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            FinanceiroSetorController cont = new FinanceiroSetorController();
            cont.removeSetor(setor.getId());
            return true;
        }
        return false;
    }
    
}
