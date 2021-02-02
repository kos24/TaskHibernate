package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = Util.getDBConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {


        String sql = "CREATE TABLE USER (id INT NOT NULL AUTO_INCREMENT," +
                                                    "name VARCHAR(45) NULL," +
                                                    "lastName VARCHAR(55) NULL," +
                                                    "age INT NULL," +
                                                    "PRIMARY KEY (id)" +
                                                    ");";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
            System.out.println("Таблица USER создана");
        } catch (SQLException e) {
            System.out.println("Таблица USER уже существует");
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE USER;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
            System.out.println("Таблица USER удалена");
        } catch (SQLException e) {
            System.out.println("Таблица USER не существует");
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USER(name, lastName, age) VALUES (?,?,?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Не удалось добавить данные в таблицу USER");
        }
    }

    public void removeUserById(long id) {
        String getUserId = "DELETE FROM USER WHERE ID = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(getUserId)) {
           preparedStatement.setLong(1,id);
           preparedStatement.executeUpdate();
            System.out.println("User с номером ID = " + id + "удален из базы");
        } catch (SQLException e) {
            e.printStackTrace();
//            System.out.println("Не найден User с ID = " + id);
        }

    }

    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();
        String getAll = "SELECT * FROM USER;";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAll);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));

                userList.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String cleanUsers = "DELETE FROM USER;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(cleanUsers)) {
            preparedStatement.execute();
            System.out.println("Таблица USER очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
