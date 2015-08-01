/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pais.comboBox;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import pais.classe.PaisClasse;

/**
 *
 * @author Marcos
 */
public class PaisComboBoxCellRenderer extends DefaultListCellRenderer {
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list,
            Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
         
        if (value instanceof PaisClasse) {
            PaisClasse pais = (PaisClasse) value;
            setText(pais.getPais());
        }
        return this;
    }
    
}
