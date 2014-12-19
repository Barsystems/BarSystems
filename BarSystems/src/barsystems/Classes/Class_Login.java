/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;

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
            banco.conectaBanco();
            rs = banco.st.executeQuery("Select Nome, Senha from usuarios "
                    + "where Nome = '"+Usuario+"' and Senha = '"+Senha+"'");
            while (rs.next()) {
                JOptionPane.showMessageDialog(null, "Isso ae! rs");
            }
            
            rs.close();
            banco.fechaConexaoBanco();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getMessage();
        }
        
    }
    
}
