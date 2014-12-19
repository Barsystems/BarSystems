/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Marcos
 */
public class Class_Conexao_Banco {
    
    public Connection con;
    public Statement st;
    public ResultSet rs;
    public PreparedStatement ps;
    
    /**
     * Este método conecta ao banco de dados
     */
    public void conectaBanco() {
        
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/estoque", "estoque", "estoque");
            st = con.createStatement();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            e.getMessage();
        }
        
    }
    
    /**
     * Este método desconecta do banco de dados
     */
    public void fechaConexaoBanco() {
            
        try 
        {
            con.close();
            st.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            e.getMessage();
        }

    }  
    
}
