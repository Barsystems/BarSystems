/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico.comboBox;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import servico.classe.ServicoClasse;

/**
 *
 * @author Marcos
 */
public class ServicoComboBoxCellRenderer extends DefaultListCellRenderer {
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list,
            Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
         
        if (value instanceof ServicoClasse) {
            ServicoClasse classe = (ServicoClasse) value;
            setText(classe.getNome());
        }
        return this;
    }
    
}
