
package mesas;

import conexao_banco.Class_Conexao_Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Class_Mesas {
    
    public Class_Mesas() {
        
    }
    
    public DefaultListModel refreshList(String numero) {
        DefaultListModel list = new DefaultListModel();
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("SELECT numero FROM mesas WHERE excluido = 0 "
                    + "AND numero LIKE '%"+numero+"%' ORDER BY numero");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.addElement(rs.getString(1));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    
    }
    
    public boolean verificaNumeroRepetidoMesa(int numero) {
        boolean flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("SELECT numero FROM mesas "
                    + "WHERE numero = '"+numero+"'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
    
    public void cadastraMesa(int numero) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("INSERT INTO mesas (numero) VALUES (?)");
            ps.setInt(1, numero);
            ps.executeUpdate();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Mesa cadastrada com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editaMesa(int numero, int novo_numero) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("UPDATE mesas SET "
                    + "numero = '"+novo_numero+"' WHERE numero = '"+numero+"'");
            ps.executeUpdate();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Mesa editada com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int retornaIdMesa(int numero) {
        int id = 0;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("SELECT id_mesa FROM mesas "
                    + "WHERE numero = '"+numero+"' AND excluido = 0");
            ResultSet rs = ps.executeQuery();
            rs.next();
            id = rs.getInt(1);
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public boolean verificaMesaPadrao(int numero) {
        boolean flag = false;
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("SELECT padrao FROM mesas "
                    + "WHERE numero = '"+numero+"' AND padrao = 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
    
    public void excluiMesa(int id_setor) {
        try {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("UPDATE mesas SET "
                    + "excluido = 1 WHERE id_mesa = '"+id_setor+"'");
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
