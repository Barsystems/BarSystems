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
import usuario.classe.UsuarioClasse;

/**
 *
 * @author Marcos
 */
public class UsuarioComboBoxModel extends AbstractListModel<Object> implements ComboBoxModel<Object> {

    private List<UsuarioClasse> classe;
    Object selectedTipo;
 
    public UsuarioComboBoxModel(List<UsuarioClasse> lista) {
        this.classe = new ArrayList<UsuarioClasse>();
        this.classe.addAll(lista);
        if (this.classe.size() > 0) {
            setSelectedItem(this.classe.get(0));
        }
    }
    
    @Override
    public int getSize() {
        return classe.size();
    }

    @Override
    public Object getElementAt(int index) {
        return classe.get(index);
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
