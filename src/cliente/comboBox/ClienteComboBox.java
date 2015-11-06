/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.comboBox;

import cliente.classe.ClienteClasse;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Marcos
 */
public class ClienteComboBox extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    
    private List<ClienteClasse> lista;
    Object selected;
    
    public ClienteComboBox() {
        this.lista = new ArrayList<ClienteClasse>();        
    }
    
    public ClienteComboBox(List<ClienteClasse> lista) {
        this.lista = new ArrayList<ClienteClasse>();
        this.lista.addAll(lista);
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
        selected = (ClienteClasse) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }
    
}
