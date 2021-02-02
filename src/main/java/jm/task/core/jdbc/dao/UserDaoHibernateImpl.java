package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();
    private Transaction transaction = null;
    private Session session = null;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "CREATE TABLE USER (id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(45) NULL," +
                    "lastName VARCHAR(55) NULL," +
                    "age INT NULL," +
                    "PRIMARY KEY (id)" +
                    ");";
            session.createSQLQuery(hql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Таблица USER уже существует");
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "DROP TABLE USER;";
            session.createSQLQuery(hql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Таблица USER не существует");

        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try {
            session = sessionFactory.getCurrentSession();
            User user = new User(name, lastName, age);
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
//            User user = (User) session.get(User.class, id);
//            session.delete(user);
            String hql = "delete from User where id = :id";
            session.createQuery(hql).setLong("id", id).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "from User";
            list = session.createQuery(hql).list();
            transaction.commit();
            for (User user : list) {
                System.out.println(user);
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "Delete from User";
            session.createQuery(hql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
