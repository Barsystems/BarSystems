/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.table;

import cliente.classe.ClienteClasse;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marcos
 */
public class ClienteTableModel extends AbstractTableModel {
    
    private static final int COL_NOME = 0;
    private static final int COL_CPF = 1;
    private static final int COL_TELEFONE = 2;
    
    private List<ClienteClasse> valores;
    
    public ClienteTableModel(List<ClienteClasse> valores) {
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
        ClienteClasse classe = valores.get(rowIndex);
        if (columnIndex == COL_NOME) {
            return classe.getNome();
        } else if (columnIndex == COL_CPF) {
            return classe.getCpf();
        } else {
            return classe.getTelefone();
        }
        
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_NOME: coluna = "Nome do cliente"; break;
            case COL_CPF: coluna = "Cpf do cliente"; break;
            case COL_TELEFONE: coluna = "Telefone do cliente"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public ClienteClasse get(int row) {
        return valores.get(row);
    }
    
}
