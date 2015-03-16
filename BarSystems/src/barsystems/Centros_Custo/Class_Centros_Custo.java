
package barsystems.Centros_Custo;

import barsystems.conexaoBanco.Class_Conexao_Banco;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class Class_Centros_Custo {
    
    Class_Conexao_Banco banco = new Class_Conexao_Banco();
    protected String id_centro_custo, nome, tipo, saldo;
    
    public Class_Centros_Custo() {
        
    }
    
    public String getIdCentroCusto() {
        return this.id_centro_custo;
    }
    
    public String getNomeCentroCusto() {
        return this.nome;
    }
    
    public String getTipoCentroCusto() {
        return this.tipo;
    }
    
    public String getSaldoCentroCusto() {
        return this.saldo;
    }
    
    public DefaultListModel refreshCaixas() {
        DefaultListModel lista = new DefaultListModel();
        try {
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT nome FROM centros_custo WHERE tipo = 'Caixa' "
                    + "AND excluido = 0");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.addElement(rs.getString(1));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public int retornaIdCentroCusto(String nome) {
        int centro_custo = 0;
        try {
            Connection con = banco.getConexaoMySQL();
            PreparedStatement ps = con.prepareStatement("SELECT id_centro_custo FROM centros_custo "
                    + "WHERE nome = '"+nome+"'");
            ResultSet rs = ps.executeQuery();
            rs.next();
            centro_custo = rs.getInt(1);
            rs.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return centro_custo;
    }
    
    public DefaultListModel carregaLista(){
        DefaultListModel listModel = new DefaultListModel();
        
        //ListModel lista = new ListModel();
        try{
            
           String sql = "SELECT nome FROM centros_custo where excluido = 0 order by nome";  
           Connection con = banco.getConexaoMySQL();
           PreparedStatement stmt = con.prepareStatement(sql);    
           
           ResultSet rs = stmt.executeQuery();  
              
            while(rs.next()){  
               listModel.addElement(rs.getString(1));
            }              
            rs.close();  
            stmt.close();
            con.close();
        
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return listModel;
    }// FIM CARREGA LISTA
    
    public void Cadastra(String nome, String tipo){
        
        String sql = "INSERT INTO centros_custo(nome, tipo) VALUES (?, ?)";    
        
        try {
            Connection con = banco.getConexaoMySQL();
            PreparedStatement stmt = con.prepareStatement(sql);    
            stmt.setString(1, nome);
            stmt.setString(2, tipo);
            
            stmt.execute();
            stmt.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Centro de custo cadastrado com sucesso!");
            
        } catch (SQLException e) {    
            e.printStackTrace();  
            JOptionPane.showMessageDialog(null, e.getMessage(), "Atenção", JOptionPane.ERROR_MESSAGE);
        } 
    } // Fim Cadastra
    
    public void carregaCentroCusto(String nome){
        try{
            
            NumberFormat z = NumberFormat.getCurrencyInstance();
            
           String sql = "SELECT id_centro_custo, nome, tipo, saldo FROM centros_custo where excluido = 0 and nome = '"+nome+"'";  
            Connection con = banco.getConexaoMySQL();
                PreparedStatement stmt = con.prepareStatement(sql);    
   
            ResultSet rs = stmt.executeQuery();              
            rs.next();
                    
            this.id_centro_custo = rs.getString(1);
            this.nome = rs.getString(2);
            this.tipo = rs.getString(3);
            this.saldo = z.format(rs.getFloat(4));
                        
            rs.close();  
            stmt.close();
            con.close();
        
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    } // FIM CARREGA CENTROS CUSTO
    
    public void edita(String codigo, String nome) {
        
        String sql = "Update centros_custo set nome= '"+nome+"' WHERE id_centro_custo = "+codigo;
    
        try {    
            Connection con = banco.getConexaoMySQL();
            PreparedStatement stmt = con.prepareStatement(sql);    

            stmt.executeUpdate();
                stmt.close();
                con.close();    
                
                JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException e) {    
                e.printStackTrace();
        } 
    }
    
    public void exclui(String codigo){
        String sql = "UPDATE centros_custo set excluido = 1 where id_centro_custo = '"+codigo+"'";    
    
        try {    
            Connection con = banco.getConexaoMySQL();
            PreparedStatement stmt = con.prepareStatement(sql);    
            stmt.execute();
            stmt.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Centro de custo excluído com sucesso!");
        } catch (SQLException e) {    
            e.printStackTrace();
        } 
    }
    
    public void getCentrosCusto(JTabbedPane painel_centros_custo, int id_usuario, String nome_usuario) {
        try {
            ImageIcon icon = null;
            Component component = null;
            Connection conn = banco.getConexaoMySQL();
            PreparedStatement ps = conn.prepareStatement("SELECT centros_custo.id_centro_custo, centros_custo.nome, "
                    + "centros_custo.tipo, responsaveis_caixa.id_usuario "
                    + "FROM centros_custo "
                    + "INNER JOIN responsaveis_caixa ON centros_custo.id_centro_custo = responsaveis_caixa.id_centro_custo "
                    + "WHERE centros_custo.Excluido = 0 AND responsaveis_caixa.id_usuario = '"+id_usuario+"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("centros_custo.tipo").equals("Caixa")) {
                    icon = new ImageIcon(getClass().getResource("/barsystems/imagens/coins15 (1).png"));
                    Painel_Caixa caixa = new Painel_Caixa(rs.getInt("centros_custo.id_centro_custo"), rs.getString("centros_custo.nome"), id_usuario, nome_usuario);
                    component = caixa;
                } else {
                    icon = new ImageIcon(getClass().getResource("/barsystems/imagens/credit31.png"));
                    Painel_Conta_Bancaria conta_bancaria = new Painel_Conta_Bancaria();
                    component = conta_bancaria;
                }
                painel_centros_custo.addTab(rs.getString("centros_custo.nome")+"   ", icon, component, "Gerencie seus centros de custo!");
                component = null;
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
