/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package barsystems.Login;

import barsystems.conexaoBanco.Class_Conexao_Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class Class_Login {
    
    public ResultSet rs;
    
    /**
     * Este método verifica se o usuário e a senha informados no login estão corretos
     * @param Usuario Nome do usuário
     * @param Senha Senha do usuário
     */
    public void verificaLoginSenha(String Usuario, String Senha) {
        
        Class_Conexao_Banco banco = new Class_Conexao_Banco();
        try
        {
            banco.getConexaoMySQL();
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement("Select Nome, Senha from usuarios "
                    + "where Nome = '"+Usuario+"' and Senha = '"+Senha+"'");
            
            while (rs.next()) {
                JOptionPane.showMessageDialog(null, "Isso ae! rs");
            }
            
            rs.close();
            banco.FecharConexao();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getMessage();
        }
        
    }
    
}
