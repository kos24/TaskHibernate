package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserDao user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Alex", "Sidorov", (byte) 27);
        user.saveUser("Ivan", "Ivanov", (byte) 18);
        user.saveUser("Dmitriy", "Dmitriev", (byte) 35);
        user.saveUser("Alexander", "Eliseev", (byte) 37);
//        user.removeUserById(1L);
        user.getAllUsers();
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
