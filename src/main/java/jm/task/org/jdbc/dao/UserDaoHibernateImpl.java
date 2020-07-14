package jm.task.org.jdbc.dao;

import jm.task.org.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserDaoHibernateImpl() {


    }


    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }



    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        tx1.commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("delete from User where id = :id")
        .setParameter("UserId", 1);
        tx1.commit();
        session.close();


    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        User user = new User();
        session.delete(user);
        tx1.commit();
        session.close();

    }

}

