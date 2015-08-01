/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_maquina_cartao.janela;

import forma_pagamento_maquina_cartao.classe.FormaPagamentoMaquinaCartaoClasse;
import forma_pagamento_maquina_cartao.controller.FormaPagamentoMaquinaCartaoController;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoMaquinaCartaoExcluir {
    
    public boolean excluir (FormaPagamentoMaquinaCartaoClasse classe) {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir a máquina de cartão "+classe.getNome()+"?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0) {
            FormaPagamentoMaquinaCartaoController cont = new FormaPagamentoMaquinaCartaoController();
            cont.removeMaquinaCartao(classe.getId());
            return true;
        }
        return false;
    }
    
}
