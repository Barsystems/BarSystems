/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto_setor.comboBox;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import produto_setor.classe.ProdutoSetorClasse;

/**
 *
 * @author Marcos
 */
public class ProdutoSetorComboBox extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    
    private List<ProdutoSetorClasse> setores;
    Object selectedSetor;
    
    public ProdutoSetorComboBox() {
        this.setores = new ArrayList<ProdutoSetorClasse>();        
    }
    
    public ProdutoSetorComboBox(List<ProdutoSetorClasse> listSetores) {
        this.setores = new ArrayList<ProdutoSetorClasse>();
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
        selectedSetor = (ProdutoSetorClasse) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedSetor;
    }
    
}
