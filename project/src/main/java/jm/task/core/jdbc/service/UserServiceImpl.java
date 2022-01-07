package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final String URL = "jdbc:mysql://localhost:3306/katadb";
    private final String user = "root";
    private final String pass = "12345";


    Statement statement = null;

    public UserServiceImpl(){
        try {
            statement = connect().createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }





    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS user (\n" +
                "  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                "  `name` NVARCHAR(45) NULL,\n" +
                "  `lastName` NVARCHAR(45) NULL,\n" +
                "  `age` VARCHAR(45) NULL,\n" +
                "  PRIMARY KEY (`id`));";
        try {
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS user;";
        try {
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into user(name , lastName , age)\n" +
                "values( '"+name+"' , '"+lastName+"' , "+age+");";
        try {
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String SQL = "SELECT * FROM user;";

        try{

            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte)resultSet.getInt(4));
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        dropUsersTable();
        createUsersTable();
    }

    Connection connect(){

        Connection connection = null;
        try {
           connection = DriverManager.getConnection(URL , user , pass);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
