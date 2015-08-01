/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_custo.comboBox;

import centro_custo.classe.CentroCustoClasse;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Marcos
 */
public class CentroCustoComboBox extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    
    private List<CentroCustoClasse> centros_custo;
    Object selectedCentro;
    
    public CentroCustoComboBox() {
        this.centros_custo = new ArrayList<CentroCustoClasse>();        
    }
    
    public CentroCustoComboBox(List<CentroCustoClasse> listCentros) {
        this.centros_custo = new ArrayList<CentroCustoClasse>();
        this.centros_custo.addAll(listCentros);
        if (getSize() > 0) {
            setSelectedItem(this.centros_custo.get(0));
        }
    }

    @Override
    public int getSize() {
        return centros_custo.size();
    }

    @Override
    public Object getElementAt(int index) {
        return centros_custo.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedCentro = (CentroCustoClasse) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedCentro;
    }
    
}
