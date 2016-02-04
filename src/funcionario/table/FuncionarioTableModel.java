/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario.table;

import funcionario.classe.FuncionarioClasse;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marcos
 */
public class FuncionarioTableModel extends AbstractTableModel {
    
    private static final int COL_NOME = 0;
    private static final int COL_FUNCAO = 1;
    private static final int COL_TELEFONE = 2;
    private static final int COL_CODIGO = 3;
    
    private List<FuncionarioClasse> valores;
    
    public FuncionarioTableModel(List<FuncionarioClasse> valores) {
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
        FuncionarioClasse user = valores.get(rowIndex);
        if (columnIndex == COL_NOME) {
            return user.getNome();
        } else if (columnIndex == COL_FUNCAO) {
            return user.getFuncao();
        } else if (columnIndex == COL_TELEFONE) {
            return user.getTelefone();
        } else {
            return user.getId();
        }
    }
    
    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_NOME: coluna = "Nome do funcionário"; break;
            case COL_FUNCAO: coluna = "Função do funcionário"; break;
            case COL_TELEFONE: coluna = "Telefone do funcionário"; break;
            case COL_CODIGO: coluna = "ID do funcionário"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public FuncionarioClasse get(int row) {
        return valores.get(row);
    }
    
}
