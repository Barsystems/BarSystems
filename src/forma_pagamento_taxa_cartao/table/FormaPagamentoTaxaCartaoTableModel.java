/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forma_pagamento_taxa_cartao.table;

import forma_pagamento_taxa_cartao.classe.FormaPagamentoTaxaCartaoClasse;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marcos
 */
public class FormaPagamentoTaxaCartaoTableModel extends AbstractTableModel {

    private static final int COL_FORMA_PAGAMENTO = 0;
    private static final int COL_MAQUINA_CARTAO = 1;
    private static final int COL_TAXA = 2;
    
    private List<FormaPagamentoTaxaCartaoClasse> valores;
    
    public FormaPagamentoTaxaCartaoTableModel(List<FormaPagamentoTaxaCartaoClasse> valores) {
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
        FormaPagamentoTaxaCartaoClasse classe = valores.get(rowIndex);
        if (columnIndex == COL_FORMA_PAGAMENTO) {
            return classe.getForma_pagamento();
        } else if (columnIndex == COL_MAQUINA_CARTAO) {
            return classe.getMaquina_cartao();
        } else {
            return classe.getTaxa();
        }
        
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_FORMA_PAGAMENTO: coluna = "Nome do cartão"; break;
            case COL_MAQUINA_CARTAO: coluna = "Máquina de cartão"; break;
            case COL_TAXA: coluna = "Taxa cobrada em porcentagem"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public FormaPagamentoTaxaCartaoClasse get(int row) {
        return valores.get(row);
    }
    
}
