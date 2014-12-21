
package barsystems.Login;

import barsystems.conexaoBanco.Class_Conexao_Banco;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Class_Login {
    
    /**
     * Este método verifica se o usuário e a senha informados no login estão corretos
     * @param Usuario Nome do usuário
     * @param Senha Senha do usuário
     * @return true ou false
     */
    public boolean verificaLoginSenha(String Usuario, String Senha) {
        
        try
        {
            Class_Conexao_Banco banco = new Class_Conexao_Banco();
            PreparedStatement stmt = banco.getConexaoMySQL().prepareStatement("Select Nome, Senha from usuarios "
                    + "where Nome = '"+Usuario+"' and Senha = '"+Senha+"'");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) 
            {
                rs.close();
                stmt.close();
                banco.FecharConexao();
                return true;
            } 
            else 
            {
                rs.close();
                stmt.close();
                banco.FecharConexao();
                return false;
            }
            
        }
        catch (HeadlessException | SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e); 
        }
        
    }
    
}
