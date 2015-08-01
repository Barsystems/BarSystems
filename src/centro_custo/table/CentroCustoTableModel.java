/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_custo.table;

import centro_custo.classe.CentroCustoClasse;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marcos
 */
public class CentroCustoTableModel extends AbstractTableModel {
    
    private static final int COL_NOME = 0;
    private static final int COL_TIPO = 1;
    private static final int COL_SALDO = 2;
    
    private List<CentroCustoClasse> valores;
    
    public CentroCustoTableModel(List<CentroCustoClasse> valores) {
        this.valores = valores;
    }
    
    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CentroCustoClasse centro = valores.get(rowIndex);
        if (columnIndex == COL_NOME) {
            return centro.getNome();
        } else if (columnIndex == COL_TIPO) {
            return centro.getTipo();
        } else {
            return centro.getSaldo();
        }
        
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_NOME: coluna = "Nome do centro de custo"; break;
            case COL_TIPO: coluna = "Tipo do centro de custo"; break;
            case COL_SALDO: coluna = "Saldo atual disponível"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public CentroCustoClasse get(int row) {
        return valores.get(row);
    }
    
}
