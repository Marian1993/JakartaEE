package org.eclipse.jakarta.hello.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MysqlConnection {

    private Connection conn = null;
    private static MysqlConnection instance;

    private MysqlConnection(){
        try {
            InputStream input = this.getClass().getClassLoader().getResourceAsStream("application.properties");
            Properties properties = new Properties();
            properties.load(input);

            final String bd = properties.getProperty("mysql.database");
            final String host = properties.getProperty("mysql.host");
            final String usuari = properties.getProperty("mysql.username");
            final String password = properties.getProperty("mysql.password");

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + bd,usuari,password);
            if(conn != null){
                System.out.println("Connectat a la BBDD[" + conn + "] - OK");
            }else {
                System.out.println("No conectat");
            }
        }catch (Exception e){
            System.out.println("Error MYSQL: " + e.getMessage());
        }
    }

    public static MysqlConnection getInstance(){
        if(instance == null){
            instance = new MysqlConnection();
        }
        return instance;
    }

    public Connection getConnexio() {
        return conn;
    }

    public void desconnecta(Connection conn) {
        System.out.println("Desconnectant de la BBDD");
        try {
            if(conn != null){
                conn.close();
            }
        }catch (Exception e){
            System.out.println("No s'ha pogut tancar la BBDD. Motiu " + e.getMessage());
        }

    }
}
