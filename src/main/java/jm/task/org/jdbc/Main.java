package jm.task.org.jdbc;


import jm.task.org.jdbc.model.User;
import jm.task.org.jdbc.service.UserService;
import jm.task.org.jdbc.service.UserServiceImpl;



public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vasa","Vaska",(byte)22);
        userService.saveUser("Peta","Petka",(byte)26);
        userService.saveUser("Masha","Mashka",(byte)29);
        userService.saveUser("Kolya","Kolka",(byte)21);
        for(User emp : userService.getAllUsers()) {
            System.out.println(emp);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
