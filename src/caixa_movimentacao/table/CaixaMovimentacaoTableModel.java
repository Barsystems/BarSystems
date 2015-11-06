/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixa_movimentacao.table;

import caixa_movimentacao.classe.CaixaMovimentacaoClasse;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marcos
 */
public class CaixaMovimentacaoTableModel extends AbstractTableModel {
    
    private static final int COL_DESCRICAO = 0;
    private static final int COL_FORMA_PAGAMENTO = 1;
    private static final int COL_VALOR = 2;
    private static final int COL_TIPO = 3;
    private static final int COL_DATA = 4;
    
    private List<CaixaMovimentacaoClasse> valores;
    
    public CaixaMovimentacaoTableModel(List<CaixaMovimentacaoClasse> valores) {
        this.valores = valores;
    }
    
    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CaixaMovimentacaoClasse classe = valores.get(rowIndex);
        if (columnIndex == COL_DESCRICAO) {
            return classe.getDescricao();
        } else if (columnIndex == COL_FORMA_PAGAMENTO) {
            return classe.getForma_pagamento();
        } else if (columnIndex == COL_VALOR) {
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            return nf.format(classe.getValor());
        } else if (columnIndex == COL_TIPO) {
            return classe.getTipo();
        } else {
            return classe.getData();
        }
        
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_DESCRICAO: coluna = "Descrição da movimentação"; break;
            case COL_FORMA_PAGAMENTO: coluna = "Forma de pagamento"; break;
            case COL_VALOR: coluna = "Valor"; break;
            case COL_TIPO: coluna = "Tipo"; break;
            case COL_DATA: coluna = "Data"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public CaixaMovimentacaoClasse get(int row) {
        return valores.get(row);
    }
    
}
