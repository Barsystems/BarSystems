/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pais.comboBox;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import pais.classe.PaisClasse;

/**
 *
 * @author Marcos
 */
public class PaisComboBoxModel extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    
    private List<PaisClasse> paises;
    Object selectedPais;
    
    public PaisComboBoxModel() {
        this.paises = new ArrayList<PaisClasse>();        
    }
    
    public PaisComboBoxModel(List<PaisClasse> listPaises) {
        this.paises = new ArrayList<PaisClasse>();
        this.paises.addAll(listPaises);
        if (getSize() > 0) {
            setSelectedItem(this.paises.get(0));
        }
    }

    @Override
    public int getSize() {
        return paises.size();
    }

    @Override
    public Object getElementAt(int index) {
        return paises.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedPais = (PaisClasse) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedPais;
    }
    
}
