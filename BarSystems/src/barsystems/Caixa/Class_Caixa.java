
package barsystems.Caixa;

import barsystems.conexaoBanco.Class_Conexao_Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

public class Class_Caixa {
    
    Class_Conexao_Banco banco = new Class_Conexao_Banco();
    String id_caixa;
    
    public void getIdCaixa(String id_caixa) {
        this.id_caixa = id_caixa;
    }
    
    public String setIdCaixa() {
        return this.id_caixa;
    }
    
    public Class_Caixa() {
        
    }
    
    public boolean verificaCaixaAberto() {
        
        
        return true;
    }
    
    public void carregaMovimentacoesCaixa(DefaultTableModel tabela) {
        
        tabela.setRowCount(0);
        String valor;
        DecimalFormat dffloat = new DecimalFormat("##,###.00");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        try {
            String query = "select * from movimentacoes_caixa where id_caixa = '"+id_caixa+"'";
            PreparedStatement ps = banco.getConexaoMySQL().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                valor = dffloat.format(rs.getFloat("valor"));
                if (valor.equals(",00")) {
                    valor = "0,00";
                }
                
                tabela.addRow(new Object[] {
                    rs.getString("descricao"),
                    rs.getString("forma_pagamento"),
                    rs.getInt("numero_parcelas"),
                    valor,
                    rs.getString("tipo"),
                    sdf.format(rs.getDate("data_cadastro")),
                    rs.getInt("id_movimentacao_caixa")
                });
            }
            rs.close();
            ps.close();
            banco.FecharConexao();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
