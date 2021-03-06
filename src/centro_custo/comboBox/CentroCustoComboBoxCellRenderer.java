/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_custo.comboBox;

import centro_custo.classe.CentroCustoClasse;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Marcos
 */
public class CentroCustoComboBoxCellRenderer extends DefaultListCellRenderer {
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list,
            Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
         
        if (value instanceof CentroCustoClasse) {
            CentroCustoClasse centro = (CentroCustoClasse) value;
            setText(centro.getNome());
        }
        return this;
    }
    
}
