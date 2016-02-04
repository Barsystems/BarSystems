/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.table;

import empresa.classe.EmpresaClasse;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marcos
 */
public class EmpresaTableModel extends AbstractTableModel {

    private static final int COL_RAZAO_SOCIAL = 0;
    private static final int COL_NOME_FANTASIA = 1;
    private static final int COL_EMAIL = 2;
    private static final int COL_COD = 3;
    
    private List<EmpresaClasse> valores;
    
    public EmpresaTableModel(List<EmpresaClasse> valores) {
        this.valores = valores;
    }
    
    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EmpresaClasse empresa = valores.get(rowIndex);
        if (columnIndex == COL_RAZAO_SOCIAL) {
            return empresa.getRazao_social();
        } else if (columnIndex == COL_NOME_FANTASIA) {
            return empresa.getNome_fantasia();
        } else if (columnIndex == COL_EMAIL) {
            return empresa.getEmail();
        } else {
            return empresa.getId();
        }
        
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_RAZAO_SOCIAL: coluna = "Razão social"; break;
            case COL_NOME_FANTASIA: coluna = "Nome fantasia"; break;
            case COL_EMAIL: coluna = "Email da empresa"; break;
            case COL_COD: coluna = "ID da empresa"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public EmpresaClasse get(int row) {
        return valores.get(row);
    }
    
}
