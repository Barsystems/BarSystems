/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.comboBox;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Marcos
 */
public class ProdutoComboBoxTipoMedida extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    
    private List<Object> lista;
    Object selected;
    
    public ProdutoComboBoxTipoMedida() {
        this.lista = new ArrayList<Object>();
        this.lista.add("Unidade");
        this.lista.add("Metro");
        if (getSize() > 0) {
            setSelectedItem(this.lista.get(0));
        }
    }

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Object getElementAt(int index) {
        return lista.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selected = anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }
    
}
