
package financeiro;

import conexao_banco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Class_Setores_Financeiros {
    
    public Class_Setores_Financeiros() {
        
    }
    
    public int retornaIdSetorFinanceiro(String setor) {
        int id = 0;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("select id_setor from setores_financeiros "
                    + "where descricao = '"+setor+"' and excluido = 0");
            ResultSet rs = ps.executeQuery();
            rs.next();
            id = rs.getInt(1);
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public void carregaSetoresFinanceirosComboBox(javax.swing.JComboBox combo, String tipo, String todos) {
        
        combo.removeAllItems();
        if (todos.equals("Todos")) {
            combo.addItem("Todos");
        }
        
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT descricao FROM setores_financeiros WHERE excluido = 0 "
                    + "AND tipo = '"+tipo+"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString(1));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public DefaultListModel refreshList() {
        DefaultListModel list = new DefaultListModel();
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT descricao FROM setores_financeiros WHERE excluido = 0 "
                    + "ORDER BY descricao");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.addElement(rs.getString(1));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public String retornaTipoSetor(String descricao) {
        String tipo = null;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT tipo FROM setores_financeiros WHERE excluido = 0 "
                    + "AND descricao = '"+descricao+"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tipo = rs.getString(1);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipo;
    }
    
    public boolean verificaSetorRepetido(String descricao) {
        boolean flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT descricao FROM setores_financeiros "
                    + "WHERE descricao = '"+descricao+"' AND excluido = 0");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    public void cadastraSetorFinanceiro(String descricao, String tipo) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO setores_financeiros (descricao, tipo) VALUES (?, ?)");
            ps.setString(1, descricao);
            ps.setString(2, tipo);
            ps.executeUpdate();
            ps.close();
            conn.close();
            
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void alteraSetorFinanceiro(String descricao, String tipo, int id_setor) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE setores_financeiros SET "
                    + "descricao = '"+descricao+"', "
                    + "tipo = '"+tipo+"' WHERE id_setor = '"+id_setor+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
            
            JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void excluiSetorFinanceiro(String descricao) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("UPDATE setores_financeiros SET "
                    + "excluido = 1 WHERE descricao = '"+descricao+"'");
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean retornaPadrao(String descricao) {
        boolean flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT padrao FROM setores_financeiros "
                    + "WHERE excluido = 0 AND padrao = 1 AND descricao = '"+descricao+"'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
}
