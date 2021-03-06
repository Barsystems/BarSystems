/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fornecedor.table;

import fornecedor.classe.FornecedorClasse;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marcos
 */
public class FornecedorTableModel extends AbstractTableModel {

    private static final int COL_RAZAO_SOCIAL = 0;
    private static final int COL_NOME_FANTASIA = 1;
    private static final int COL_TELEFONE = 2;
    
    private List<FornecedorClasse> valores;
    
    public FornecedorTableModel(List<FornecedorClasse> valores) {
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
        FornecedorClasse forn = valores.get(rowIndex);
        if (columnIndex == COL_RAZAO_SOCIAL) {
            return forn.getRazao_social();
        } else if (columnIndex == COL_NOME_FANTASIA) {
            return forn.getNome_fantasia();
        } else {
            return forn.getTelefone();
        }
        
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_RAZAO_SOCIAL: coluna = "Razão social"; break;
            case COL_NOME_FANTASIA: coluna = "Nome fantasia"; break;
            case COL_TELEFONE: coluna = "Telefone do fornecedor"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public FornecedorClasse get(int row) {
        return valores.get(row);
    }
    
}
