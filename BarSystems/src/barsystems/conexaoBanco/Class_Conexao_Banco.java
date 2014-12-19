/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barsystems.conexaoBanco;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;
    import java.sql.PreparedStatement;
/**
 *
 * @author Lucas
 */
public class Class_Conexao_Banco {
   public static String status = "Não conectou...";
    public Class_Conexao_Banco(){
        
    }
    //Método de Conexão//

public java.sql.Connection getConexaoMySQL() {

    Connection connection = null;          //atributo do tipo Connection

try {

// Carregando o JDBC Driver padrão

String driverName = "com.mysql.jdbc.Driver";                        

Class.forName(driverName);

 

// Configurando a nossa conexão com um banco de dados//

            String serverName = "localhost";    //caminho do servidor do BD

            String mydatabase ="barsystems";        //nome do seu banco de dados

            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

            String username = "root";        //nome de um usuário de seu BD      

            String password = "";      //sua senha de acesso

            connection = DriverManager.getConnection(url, username, password);

 

            //Testa sua conexão//  

            if (connection != null) {

                status = ("STATUS--->Conectado com sucesso!");

            } else {

                status = ("STATUS--->Não foi possivel realizar conexão");

            }


            return connection;

        } catch (ClassNotFoundException e) {  //Driver não encontrado

            System.out.println("O driver expecificado nao foi encontrado.");

            return null;

        } catch (SQLException e) {

            //Não conseguindo se conectar ao banco

            System.out.println("Nao foi possivel conectar ao Banco de Dados.");

            return null;

        }
} //Fim do metodo getConexao//

//status conexao
public String statusConection() {

        System.out.println(status);
        return status;

}//fim do metodo status da conexao


//Método que fecha sua conexão//
public boolean FecharConexao() {

        try {

           getConexaoMySQL().close();

            return true;

        } catch (SQLException e) {

            return false;

        }

 

} // fim do metodo de fechar conexao

}
