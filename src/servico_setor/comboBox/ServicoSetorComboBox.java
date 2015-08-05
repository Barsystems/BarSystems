/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico_setor.comboBox;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import servico_setor.classe.ServicoSetorClasse;

/**
 *
 * @author Marcos
 */
public class ServicoSetorComboBox extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    
    private List<ServicoSetorClasse> setores;
    Object selectedSetor;
    
    public ServicoSetorComboBox() {
        this.setores = new ArrayList<ServicoSetorClasse>();        
    }
    
    public ServicoSetorComboBox(List<ServicoSetorClasse> listSetores) {
        this.setores = new ArrayList<ServicoSetorClasse>();
        this.setores.addAll(listSetores);
        if (getSize() > 0) {
            setSelectedItem(this.setores.get(0));
        }
    }

    @Override
    public int getSize() {
        return setores.size();
    }

    @Override
    public Object getElementAt(int index) {
        return setores.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedSetor = (ServicoSetorClasse) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedSetor;
    }
    
}
