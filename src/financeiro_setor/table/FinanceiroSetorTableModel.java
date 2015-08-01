/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financeiro_setor.table;

import financeiro_setor.classe.FinanceiroSetorClasse;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marcos
 */
public class FinanceiroSetorTableModel extends AbstractTableModel {
    
    private static final int COL_SETOR = 0;
    private static final int COL_TIPO = 1;
    
    private List<FinanceiroSetorClasse> valores;
    
    public FinanceiroSetorTableModel(List<FinanceiroSetorClasse> valores) {
        this.valores = valores;
    }

    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FinanceiroSetorClasse setor = valores.get(rowIndex);
        if (columnIndex == COL_SETOR) {
            return setor.getNome();
        } else if (columnIndex == COL_TIPO) {
            return setor.getTipo();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_SETOR: coluna = "Nome do setor"; break;
            case COL_TIPO: coluna = "Tipo do setor"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public FinanceiroSetorClasse get(int row) {
        return valores.get(row);
    }
    
}
