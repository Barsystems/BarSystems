/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico_setor.janela;

import javax.swing.JOptionPane;
import servico_setor.classe.ServicoSetorClasse;
import servico_setor.controller.ServicoSetorController;

/**
 *
 * @author Marcos
 */
public class ServicoSetorExcluir {
    
    public boolean excluir(ServicoSetorClasse setor) {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o setor "+setor.getSetor()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            ServicoSetorController cont = new ServicoSetorController();
            cont.removeSetor(setor.getId());
            return true;
        }
        return false;
    }
    
}
