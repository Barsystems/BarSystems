/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto_setor.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import produto_setor.classe.ProdutoSetorClasse;

/**
 *
 * @author Marcos
 */
public class ProdutoSetorTableModel extends AbstractTableModel {

    private static final int COL_NOME = 0;
    
    private List<ProdutoSetorClasse> valores;
    
    public ProdutoSetorTableModel(List<ProdutoSetorClasse> valores) {
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
        ProdutoSetorClasse setor = valores.get(rowIndex);
        if (columnIndex == COL_NOME) {
            return setor.getSetor();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_NOME: coluna = "Nome do setor"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public ProdutoSetorClasse get(int row) {
        return valores.get(row);
    }
    
}
