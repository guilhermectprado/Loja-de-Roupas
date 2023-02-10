package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBD {
	public static Connection connection = null; //conexao com banco de dados
    public static Statement statement = null; //gerencia consultas
    
    public static Connection conectar() {
        String hostname = "motty.db.elephantsql.com";
        int port = 5432;
        String database = "xhsbkeym";
        String username = "xhsbkeym";
        String password = "zror7czHk4uyaHwx2Sfm0QfaHeHmjive";
        String servidor = "jdbc:postgresql://" + hostname + ":" + port + "/" + database;

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(servidor, username, password);
            statement = connection.createStatement();
            System.out.println("CONECTOU");

        } catch (SQLException ex) {
            System.err.println("Erro na conexao" + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Erro geral" + ex.getMessage());
        }
        return connection;
    }


    public static void close() {
        try {
            connection.close();
        } catch (Exception ex) {
            System.err.println("Erro ao desconectar" + ex.getMessage());
        }
    }

}
