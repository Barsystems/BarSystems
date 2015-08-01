/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_tipo.comboBox;

import forma_pagamento_tipo.classe.FormaPagamentoTipoClasse;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoTipoComboBox extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    
    private List<FormaPagamentoTipoClasse> lista_tipo;
    Object selectedFuncao;
    
    public FormaPagamentoTipoComboBox() {
        this.lista_tipo = new ArrayList<FormaPagamentoTipoClasse>();        
    }
    
    public FormaPagamentoTipoComboBox(List<FormaPagamentoTipoClasse> list) {
        this.lista_tipo = new ArrayList<FormaPagamentoTipoClasse>();
        this.lista_tipo.addAll(list);
        if (getSize() > 0) {
            setSelectedItem(this.lista_tipo.get(0));
        }
    }

    @Override
    public int getSize() {
        return lista_tipo.size();
    }

    @Override
    public Object getElementAt(int index) {
        return lista_tipo.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedFuncao = (FormaPagamentoTipoClasse) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedFuncao;
    }
    
}
