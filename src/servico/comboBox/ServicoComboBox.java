/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico.comboBox;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import servico.classe.ServicoClasse;

/**
 *
 * @author Marcos
 */
public class ServicoComboBox extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    
    private List<ServicoClasse> lista;
    Object selected;
    
    public ServicoComboBox() {
        this.lista = new ArrayList<ServicoClasse>();        
    }
    
    public ServicoComboBox(List<ServicoClasse> lista) {
        this.lista = new ArrayList<ServicoClasse>();
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
        selected = (ServicoClasse) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }
    
}
