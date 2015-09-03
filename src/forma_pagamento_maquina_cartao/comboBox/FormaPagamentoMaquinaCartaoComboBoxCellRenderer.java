/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_maquina_cartao.comboBox;

import forma_pagamento_maquina_cartao.classe.FormaPagamentoMaquinaCartaoClasse;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoMaquinaCartaoComboBoxCellRenderer extends DefaultListCellRenderer {
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list,
            Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
         
        if (value instanceof FormaPagamentoMaquinaCartaoClasse) {
            FormaPagamentoMaquinaCartaoClasse classe = (FormaPagamentoMaquinaCartaoClasse) value;
            setText(classe.getNome());
        }
        return this;
    }
    
}
