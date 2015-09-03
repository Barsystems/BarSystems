/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centro_estoque.table;

import centro_estoque.classe.CentroEstoqueClasse;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marcos
 */
public class CentroEstoqueTableModel extends AbstractTableModel {
    
    private static final int COL_NOME = 0;
    
    private List<CentroEstoqueClasse> valores;
    
    public CentroEstoqueTableModel(List<CentroEstoqueClasse> valores) {
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
        CentroEstoqueClasse classe = valores.get(rowIndex);
        if (columnIndex == COL_NOME) {
            return classe.getNome();
        }
        return null;        
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_NOME: coluna = "Nome do centro de estoque"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public CentroEstoqueClasse get(int row) {
        return valores.get(row);
    }
    
}
