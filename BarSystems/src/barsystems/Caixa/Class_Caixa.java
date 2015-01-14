
package barsystems.Caixa;

import barsystems.conexaoBanco.Class_Conexao_Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Class_Caixa {
    
    Class_Conexao_Banco banco = new Class_Conexao_Banco();
    protected int id_caixa, id_usuario;
    
    public Class_Caixa() {
        
    }
    
    public boolean verificaCaixaAberto(String id_usuario) {
        
        boolean flag = false;
        
        try {
            String query = "select * from caixas where fechado = 0 and id_usuario = '"+id_usuario+"'";
            PreparedStatement ps = banco.getConexaoMySQL().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                flag = true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
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
    
    public void abrirCaixa() {
        
        try 
        {
            String query = "insert into caixas (id_usuario) values (?)";
            PreparedStatement ps = banco.getConexaoMySQL().prepareStatement(query);
            ps.setInt(1, id_usuario);
            ps.executeUpdate();
            
            ps.close();
            banco.FecharConexao();
            
            JOptionPane.showMessageDialog(null, "Caixa aberto com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
    public void registraMovimentacaoCaixa(int id_caixa, String descricao, String forma_pagamento, int numero_parcelas, 
            float valor, String tipo, int id_usuario) {
        
        try 
        {
            String query = "insert into movimentacoes_caixa (id_caixa, descricao, forma_pagamento, numero_parcelas,"
                    + "valor, tipo, id_usuario) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = banco.getConexaoMySQL().prepareStatement(query);
            ps.setInt(1, id_caixa);
            ps.setString(2, descricao);
            ps.setString(3, forma_pagamento);
            ps.setInt(4, numero_parcelas);
            ps.setFloat(5, valor);
            ps.setString(6, tipo);
            ps.setInt(7, id_usuario);
            ps.executeUpdate();
            
            ps.close();
            banco.FecharConexao();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
    public int getIdCaixa(String id_usuario) {
        
        int id_caixa = 0;
        
        try 
        {
            String query = "select id_caixa from caixas where fechado = 0 and id_usuario = '"+id_usuario+"'";
            PreparedStatement ps = banco.getConexaoMySQL().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id_caixa = rs.getInt("id_caixa");
            }
            rs.close();
            ps.close();
            banco.FecharConexao();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return id_caixa;
    }
}
