/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento.table;

import forma_pagamento.classe.FormaPagamentoClasse;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoTableModel extends AbstractTableModel {

    private static final int COL_NOME = 0;
    private static final int COL_DIAS_RECEBIMENTO = 1;
    
    private List<FormaPagamentoClasse> valores;
    
    public FormaPagamentoTableModel(List<FormaPagamentoClasse> valores) {
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
        FormaPagamentoClasse classe = valores.get(rowIndex);
        if (columnIndex == COL_NOME) {
            return classe.getNome();
        } else {
            if (classe.getDias_recebimento() == 0) {
                return "Sem dias de espera";
            } else {
                return classe.getDias_recebimento();
            }
        }
        
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_NOME: coluna = "Nome da forma de pagamento"; break;
            case COL_DIAS_RECEBIMENTO: coluna = "Dias de espera para o recebimento"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public FormaPagamentoClasse get(int row) {
        return valores.get(row);
    }
    
}
