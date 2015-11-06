/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.comboBox;

import empresa.classe.EmpresaClasse;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Marcos
 */
public class EmpresaComboBox extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    
    private List<EmpresaClasse> lista;
    Object selected;
    
    public EmpresaComboBox() {
        this.lista = new ArrayList<EmpresaClasse>();        
    }
    
    public EmpresaComboBox(List<EmpresaClasse> lista) {
        this.lista = new ArrayList<EmpresaClasse>();
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
        selected = (EmpresaClasse) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }
    
}
