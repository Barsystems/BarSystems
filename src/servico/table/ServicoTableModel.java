/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import servico.classe.ServicoClasse;

/**
 *
 * @author Marcos
 */
public class ServicoTableModel extends AbstractTableModel {

    private static final int COL_NOME = 0;
    private static final int COL_VALOR_VENDA = 1;
    
    private List<ServicoClasse> valores;
    
    public ServicoTableModel(List<ServicoClasse> valores) {
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
        ServicoClasse classe = valores.get(rowIndex);
        if (columnIndex == COL_NOME) {
            return classe.getNome();
        } else {
            return classe.getValor_venda();
        }
        
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_NOME: coluna = "Nome do serviço"; break;
            case COL_VALOR_VENDA: coluna = "Valor de venda"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public ServicoClasse get(int row) {
        return valores.get(row);
    }
    
}
