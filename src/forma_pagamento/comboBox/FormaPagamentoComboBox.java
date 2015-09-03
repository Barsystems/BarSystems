/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento.comboBox;

import forma_pagamento.classe.FormaPagamentoClasse;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoComboBox extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    
    private List<FormaPagamentoClasse> lista;
    Object selected;
    
    public FormaPagamentoComboBox() {
        this.lista = new ArrayList<FormaPagamentoClasse>();
    }
    
    public FormaPagamentoComboBox(List<FormaPagamentoClasse> lista) {
        this.lista = new ArrayList<FormaPagamentoClasse>();
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
        selected = (FormaPagamentoClasse) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }
    
}
