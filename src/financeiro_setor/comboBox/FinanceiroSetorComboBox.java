/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financeiro_setor.comboBox;

import financeiro_setor.classe.FinanceiroSetorClasse;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Marcos
 */
public class FinanceiroSetorComboBox extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    
    private List<Object> lista_setor;
    Object selectedSetor;
    
    public FinanceiroSetorComboBox() {
        this.lista_setor = new ArrayList<Object>();
        lista_setor.add("Receita");
        lista_setor.add("Despesa");
        if (getSize() > 0) {
            setSelectedItem(this.lista_setor.get(0));
        }
    }

    @Override
    public int getSize() {
        return lista_setor.size();
    }

    @Override
    public Object getElementAt(int index) {
        return lista_setor.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedSetor = (FinanceiroSetorClasse) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedSetor;
    }
    
}
