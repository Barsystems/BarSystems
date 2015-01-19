
package barsystems.Caixa;

import barsystems.Class_Troca_Virgula_Por_Ponto;
import barsystems.conexaoBanco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Class_Caixa {
    
    Class_Conexao_Banco banco = new Class_Conexao_Banco();
    
    public Class_Caixa() {
        
    }
    
    public boolean verificaCaixaAberto(int id_usuario) {
        
        boolean flag = false;
        
        try {
            String query = "select * from caixas where fechado = 0 and id_usuario = '"+id_usuario+"'";
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                flag = true;
            }
            rs.close();
            ps.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    public void carregaMovimentacoesCaixa(DefaultTableModel tabela, int id_caixa) {
        
        tabela.setRowCount(0);
        String valor;
        DecimalFormat dffloat = new DecimalFormat("##,###.00");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        try {
            String query = "select * from movimentacoes_caixa where id_caixa = '"+id_caixa+"'";
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement(query);
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
                    sdf.format(rs.getTimestamp("data_cadastro")),
                    rs.getInt("id_movimentacao_caixa")
                });
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void abrirCaixa(int id_usuario) {
        
        try 
        {
            String query = "insert into caixas (id_usuario) values (?)";
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id_usuario);
            ps.executeUpdate();
            
            ps.close();
            con.close();
            
            JOptionPane.showMessageDialog(null, "Caixa aberto com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
    public void registraMovimentacaoCaixa(int id_caixa, String descricao, String forma_pagamento, int numero_parcelas, 
            String valor, String tipo, int id_usuario) {
        
        Class_Troca_Virgula_Por_Ponto troca = new Class_Troca_Virgula_Por_Ponto();
        float Valor = troca.trocaVirgulaPorPonto(valor);
        
        try 
        {
            String query = "insert into movimentacoes_caixa (id_caixa, descricao, forma_pagamento, numero_parcelas,"
                    + "valor, tipo, id_usuario) values (?, ?, ?, ?, ?, ?, ?)";
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id_caixa);
            ps.setString(2, descricao);
            ps.setString(3, forma_pagamento);
            ps.setInt(4, numero_parcelas);
            ps.setFloat(5, Valor);
            ps.setString(6, tipo);
            ps.setInt(7, id_usuario);
            ps.executeUpdate();
            
            ps.close();
            con.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
    
    public int getIdCaixa(int id_usuario) {
        
        int id_caixa = 0;
        
        try 
        {
            String query = "select id_caixa from caixas where fechado = 0 and id_usuario = '"+id_usuario+"'";
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id_caixa = rs.getInt("id_caixa");
            }
            rs.close();
            ps.close();
            con.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return id_caixa;
    }
    
    public void fecharCaixa(String id_caixa) {
        
        try 
        {
            String query = "update caixas set data_fechamento = '"+new Date()+"', fechado = 1 "
                    + "where id_caixa = '"+id_caixa+"'";
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
            
            ps.close();
            con.close();
            
            JOptionPane.showMessageDialog(null, "Caixa aberto com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
}
