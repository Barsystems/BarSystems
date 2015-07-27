/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario_funcao.comboBox;

import funcionario_funcao.classe.FuncionarioFuncaoClasse;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Marcos
 */
public class FuncionarioFuncaoComboBox extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    
    private List<FuncionarioFuncaoClasse> funcoes;
    Object selectedFuncao;
    
    public FuncionarioFuncaoComboBox() {
        this.funcoes = new ArrayList<FuncionarioFuncaoClasse>();        
    }
    
    public FuncionarioFuncaoComboBox(List<FuncionarioFuncaoClasse> listFuncoes) {
        this.funcoes = new ArrayList<FuncionarioFuncaoClasse>();
        this.funcoes.addAll(listFuncoes);
        if (getSize() > 0) {
            setSelectedItem(this.funcoes.get(0));
        }
    }

    @Override
    public int getSize() {
        return funcoes.size();
    }

    @Override
    public Object getElementAt(int index) {
        return funcoes.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedFuncao = (FuncionarioFuncaoClasse) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedFuncao;
    }
    
    
    
}
