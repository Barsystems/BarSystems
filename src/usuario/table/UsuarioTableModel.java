/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import usuario.classe.UsuarioClasse;

/**
 *
 * @author Marcos
 */
public class UsuarioTableModel extends AbstractTableModel {

    private static final int COL_NOME = 0;
    private static final int COL_TIPO = 1;
    private static final int COL_COD = 2;
    
    private int numero_colunas;
    
    private List<UsuarioClasse> valores;
    
    public UsuarioTableModel(List<UsuarioClasse> valores, int numero_colunas) {
        this.valores = valores;
        this.numero_colunas = numero_colunas;
    }
    
    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return numero_colunas;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        UsuarioClasse user = valores.get(rowIndex);
        if (columnIndex == COL_NOME) {
            return user.getNome();
        } else if (columnIndex == COL_TIPO) {
            return user.getTipo();
        } else {
            return user.getId();
        }
        
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_NOME: coluna = "Nome do usuário"; break;
            case COL_TIPO: coluna = "Tipo do usuário"; break;
            case COL_COD: coluna = "ID do usuário"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public UsuarioClasse get(int row) {
        return valores.get(row);
    }
    
}
