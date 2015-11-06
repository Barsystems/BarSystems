/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orcamento_produto.table;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import orcamento_produto.classe.OrcamentoProdutoClasse;

/**
 *
 * @author Marcos
 */
public class OrcamentoProdutoTableModel extends AbstractTableModel {

    private static final int COL_NOME = 0;
    private static final int COL_VALOR_UNITARIO = 1;
    private static final int COL_QUANTIDADE = 2;
    private static final int COL_VALOR_TOTAL = 3;
    
    private List<OrcamentoProdutoClasse> valores;
    
    public OrcamentoProdutoTableModel() {
        this.valores = new ArrayList<OrcamentoProdutoClasse>();
    }
    
    public OrcamentoProdutoTableModel(List<OrcamentoProdutoClasse> valores) {
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
        OrcamentoProdutoClasse classe = valores.get(rowIndex);
        NumberFormat nb = NumberFormat.getCurrencyInstance();
        if (columnIndex == COL_NOME) {
            return classe.getNome_produto();
        } else if (columnIndex == COL_VALOR_UNITARIO) {
            return  nb.format(classe.getValor_cobrado());
        } else if (columnIndex == COL_QUANTIDADE) {
            String medida;
            if (classe.getTipo_medida().equals("Unidade")) {
                if (classe.getQuantidade() == 1) {
                    medida = "Unidade";
                } else {
                    medida = "Unidades";
                }
            } else {
                if (classe.getQuantidade() == 1) {
                    medida = "Metro";
                } else {
                    medida = "Metros";
                }
            }
            return classe.getQuantidade() + " " + medida;
        } else {
            return nb.format(classe.getQuantidade() * classe.getValor_cobrado());
        }
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_NOME: coluna = "Nome do produto"; break;
            case COL_VALOR_UNITARIO: coluna = "Valor unitário"; break;
            case COL_QUANTIDADE: coluna = "Quantidade"; break;
            case COL_VALOR_TOTAL: coluna = "Valor total"; break;
            default: throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
}
