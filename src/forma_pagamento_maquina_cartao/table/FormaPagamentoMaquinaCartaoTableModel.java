/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_maquina_cartao.table;

import forma_pagamento_maquina_cartao.classe.FormaPagamentoMaquinaCartaoClasse;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoMaquinaCartaoTableModel extends AbstractTableModel {
    
    private static final int COL_MAQUINA = 0;
    
    private List<FormaPagamentoMaquinaCartaoClasse> valores;
    
    public FormaPagamentoMaquinaCartaoTableModel(List<FormaPagamentoMaquinaCartaoClasse> valores) {
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
        FormaPagamentoMaquinaCartaoClasse func = valores.get(rowIndex);
        if (columnIndex == COL_MAQUINA) {
            return func.getNome();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_MAQUINA: coluna = "Nome da máquina"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    } 
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public FormaPagamentoMaquinaCartaoClasse get(int row) {
        return valores.get(row);
    }
    
}
