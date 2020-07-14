package jm.task.org.jdbc.dao;

import jm.task.org.jdbc.model.User;
import jm.task.org.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.getConnection();
    private Statement statement = null;
    private PreparedStatement smt = null;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            System.out.println("Creating table in selected database...");
            statement  = connection.createStatement();

            String SQL = "CREATE TABLE USER " +
                    "(ID int NOT NULL AUTO_INCREMENT, " +
                    " name VARCHAR(50), " +
                    " lastName VARCHAR (50), " +
                    " age int, " +
                    " PRIMARY KEY (ID))";
            statement.executeUpdate(SQL);
            System.out.println("Table successfully created...");
        } catch (SQLException e) {
            System.out.println("Table is not created!");
            e.printStackTrace();
        } finally {
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void dropUsersTable() {
        try {
            System.out.println("Droping table...");
            statement = connection.createStatement();

            String SQL = "DROP TABLE USER";

            statement.executeUpdate(SQL);
            System.out.println("Table successfully droped...");
        } catch (SQLException e) {
            System.out.println("Table is not droped!");
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        int updatesCount = 0;
        try {
            System.out.println("Save User...");
            try {
                try (PreparedStatement stmt = connection.prepareStatement(
                        "INSERT INTO USER (name, lastName, age) values (?, ?, ?)")
                ) {
                    stmt.setString(1, name);
                    stmt.setString(2, lastName);
                    stmt.setByte(3, age);
                    updatesCount = stmt.executeUpdate();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if (updatesCount != 1) {
                throw new IllegalStateException("Error while adding client!");
            }
            System.out.println("User successfully save...");
        } finally {
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void removeUserById(long id) {
        try {
            System.out.println("Delete User...");
            statement = connection.createStatement();

            String SQL = ("delete from USER where id = '" + id + "';");

            statement.executeUpdate(SQL);
            System.out.println("User successfully deleted...");
        } catch (SQLException e) {
            System.out.println("User is not deleted!");
            e.printStackTrace();
        } finally {
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<User> getAllUsers() {
        String query = "select * from USER";
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSert = statement.executeQuery(query);


            while (resultSert.next()) {
                User user = new User();
                user.setId(resultSert.getLong("id"));
                user.setName(resultSert.getString("name"));
                user.setLastName(resultSert.getString("lastName"));
                user.setAge(resultSert.getByte("age"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return users;
    }


    public void cleanUsersTable() {
        try {
            System.out.println("Clean Table...");
            statement = connection.createStatement();

            String SQL = "DELETE FROM USER";

            statement.executeUpdate(SQL);
            System.out.println("Table successfully clean...");
        } catch (SQLException e) {
            System.out.println("Table is not clean!");
            e.printStackTrace();
        } finally {
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
