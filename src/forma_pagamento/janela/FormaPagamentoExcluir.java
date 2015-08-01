/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento.janela;

import forma_pagamento.classe.FormaPagamentoClasse;
import forma_pagamento.controller.FormaPagamentoController;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoExcluir {
    
    public boolean excluir(FormaPagamentoClasse classe) {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir a forma de pagamento "+classe.getNome()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            FormaPagamentoController cont = new FormaPagamentoController();
            cont.removeFormaPagamento(classe.getId());
            return true;
        }
        return false;
    }
    
}
