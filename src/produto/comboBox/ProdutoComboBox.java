/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.comboBox;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import produto.classe.ProdutoClasse;

/**
 *
 * @author Marcos
 */
public class ProdutoComboBox extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    
    private List<ProdutoClasse> lista;
    Object selected;
    
    public ProdutoComboBox() {
        this.lista = new ArrayList<ProdutoClasse>();        
    }
    
    public ProdutoComboBox(List<ProdutoClasse> lista) {
        this.lista = new ArrayList<ProdutoClasse>();
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
        selected = (ProdutoClasse) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }
    
}
