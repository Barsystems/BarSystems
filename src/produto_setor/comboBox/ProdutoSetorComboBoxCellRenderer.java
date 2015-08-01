/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto_setor.comboBox;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import produto_setor.classe.ProdutoSetorClasse;

/**
 *
 * @author Marcos
 */
public class ProdutoSetorComboBoxCellRenderer extends DefaultListCellRenderer {
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list,
            Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
         
        if (value instanceof ProdutoSetorClasse) {
            ProdutoSetorClasse setor = (ProdutoSetorClasse) value;
            setText(setor.getSetor());
        }
        return this;
    }
    
}
