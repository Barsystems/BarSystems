/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.comboBox;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import produto.classe.ProdutoClasse;

/**
 *
 * @author Marcos
 */
public class ProdutoComboBoxCellRenderer extends DefaultListCellRenderer {
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list,
            Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
         
        if (value instanceof ProdutoClasse) {
            ProdutoClasse classe = (ProdutoClasse) value;
            setText(classe.getNome());
        }
        return this;
    }
    
}
