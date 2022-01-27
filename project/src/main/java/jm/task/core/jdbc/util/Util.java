package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/katadb";
    private static final String User = "root";
    private static final String Pass = "12345";



    public  static Connection connect(){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL , User , Pass);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }



}
