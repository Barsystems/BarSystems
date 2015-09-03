/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico.janela;

import javax.swing.JOptionPane;
import servico.classe.ServicoClasse;
import servico.controller.ServicoController;

/**
 *
 * @author Marcos
 */
public class ServicoExcluir {
    
    public boolean excluir(ServicoClasse classe) {
        boolean result = false;
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o serviço "+classe.getNome()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            ServicoController cont = new ServicoController();
            cont.removeServico(classe.getId());
            result = true;
        }
        return result;
    }
    
}
