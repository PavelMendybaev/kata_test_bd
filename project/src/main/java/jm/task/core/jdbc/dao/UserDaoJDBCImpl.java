package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection conect = Util.connect();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {



        String sql = "CREATE TABLE IF NOT EXISTS user (\n" +
                "  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                "  `name` NVARCHAR(45) NULL,\n" +
                "  `lastName` NVARCHAR(45) NULL,\n" +
                "  `age` TINYINT NULL,\n" +
                "  PRIMARY KEY (`id`));";

        try {
            PreparedStatement statement = conect.prepareStatement(sql);
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS user;";
        try {
            PreparedStatement statement = conect.prepareStatement(sql);
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try {
            PreparedStatement statement = conect.prepareStatement("insert into user(name , lastName , age) values( ? , ? , ?);");
            statement.setString(1 ,name);
            statement.setString(2 , lastName);
            statement.setByte(3 , age);
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {


        try {
            PreparedStatement statement = conect.prepareStatement("DELETE FROM user where id = ?;");
            statement.setLong(1 , id);
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM user;";

        try{
            PreparedStatement statement = conect.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

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
        String sql = "TRUNCATE TABLE user;";
        try {
            PreparedStatement statement = conect.prepareStatement(sql);
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
