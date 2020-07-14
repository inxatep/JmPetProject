package jm.task.org.jdbc.service;

import jm.task.org.jdbc.dao.UserDao;
import jm.task.org.jdbc.dao.UserDaoHibernateImpl;
import jm.task.org.jdbc.dao.UserDaoJDBCImpl;
import jm.task.org.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.println("Пользователь с именем " + name + " добавлен в таблицу");

    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);

    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
