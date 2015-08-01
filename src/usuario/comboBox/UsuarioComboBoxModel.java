/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.comboBox;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Marcos
 */
public class UsuarioComboBoxModel extends AbstractListModel<Object> implements ComboBoxModel<Object> {

    private List<Object> tipos;
    Object selectedTipo;
 
    public UsuarioComboBoxModel() {
        this.tipos = new ArrayList<>();
        this.tipos.add("Administrador");
        this.tipos.add("Normal");
        if (this.tipos.size() > 0) {
            setSelectedItem(this.tipos.get(0));
        }
    }
    
    public UsuarioComboBoxModel(List<Object> obj) {
        this.tipos = obj;
        if (this.tipos.size() > 0) {
            setSelectedItem(this.tipos.get(0));
        }
    }
    
    @Override
    public int getSize() {
        return tipos.size();
    }

    @Override
    public Object getElementAt(int index) {
        return tipos.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedTipo = anItem;
        
    }

    @Override
    public Object getSelectedItem() {
        return selectedTipo;
    }    
}
