/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.table;

import java.text.NumberFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import produto.classe.ProdutoClasse;

/**
 *
 * @author Marcos
 */
public class ProdutoTableModel extends AbstractTableModel {

    private static final int COL_NOME = 0;
    private static final int COL_VALOR_COMPRA = 1;
    private static final int COL_VALOR_VENDA = 2;
    
    private int numero_colunas;
    
    private List<ProdutoClasse> valores;
    
    public ProdutoTableModel(List<ProdutoClasse> valores, int numero_colunas) {
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
        ProdutoClasse produto = valores.get(rowIndex);
        if (columnIndex == COL_NOME) {
            return produto.getNome();
        } else if (columnIndex == COL_VALOR_COMPRA) {
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            return nf.format(produto.getValor_compra());
        } else {
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            return nf.format(produto.getValor_venda());
        }
        
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_NOME: coluna = "Nome do produto"; break;
            case COL_VALOR_COMPRA: coluna = "Valor de compra"; break;
            case COL_VALOR_VENDA: coluna = "Valor de venda"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public ProdutoClasse get(int row) {
        return valores.get(row);
    }
    
}
