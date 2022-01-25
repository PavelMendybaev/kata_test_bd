package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static  UserService service = new UserServiceImpl();


    public static void main(String[] args) throws SQLException {

        service.createUsersTable();

        service.saveUser("pavel" , "petrov" , (byte)12);
        service.saveUser("sasha" , "petro2" , (byte)13);
        service.saveUser("pavel" , "petrov3" , (byte)14);
        service.saveUser("pavel" , "petrov4" , (byte)75);
        for(User user : service.getAllUsers()){
            System.out.println(user.toString());
        }
        //service.removeUserById(12);
        service.cleanUsersTable();
        service.dropUsersTable();
        System.out.println("всё прошло");
    }



}
