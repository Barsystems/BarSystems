/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_maquina_cartao.comboBox;

import forma_pagamento_maquina_cartao.classe.FormaPagamentoMaquinaCartaoClasse;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoMaquinaCartaoComboBox extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    
    private List<FormaPagamentoMaquinaCartaoClasse> lista;
    Object selected;
    
    public FormaPagamentoMaquinaCartaoComboBox() {
        this.lista = new ArrayList<FormaPagamentoMaquinaCartaoClasse>();        
    }
    
    public FormaPagamentoMaquinaCartaoComboBox(List<FormaPagamentoMaquinaCartaoClasse> lista) {
        this.lista = new ArrayList<FormaPagamentoMaquinaCartaoClasse>();
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
        selected = (FormaPagamentoMaquinaCartaoClasse) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }
    
}
