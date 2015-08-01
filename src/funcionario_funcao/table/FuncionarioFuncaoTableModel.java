/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario_funcao.table;

import funcionario_funcao.classe.FuncionarioFuncaoClasse;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marcos
 */
public class FuncionarioFuncaoTableModel extends AbstractTableModel {
    
    private static final int COL_FUNCAO = 0;
    
    private List<FuncionarioFuncaoClasse> valores;
    
    public FuncionarioFuncaoTableModel(List<FuncionarioFuncaoClasse> valores) {
        this.valores = valores;
    }

    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FuncionarioFuncaoClasse func = valores.get(rowIndex);
        if (columnIndex == COL_FUNCAO) {
            return func.getFuncao();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_FUNCAO: coluna = "Nome da função"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public FuncionarioFuncaoClasse get(int row) {
        return valores.get(row);
    }
    
}
