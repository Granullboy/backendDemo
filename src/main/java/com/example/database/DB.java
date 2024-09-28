package com.example.database;

import com.example.moduls.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DB {
    static public void start() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cft.xml")
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            //User user2 = new User("username1", "email1", "password1");
            //session.save(user2);

            List<User> users = session.createNativeQuery("SELECT * FROM users", User.class).list();
            List<User> user = session.createNativeQuery("SELECT * FROM users WHERE username = 'username1'", User.class).list();
            System.out.println(users);
            System.out.println(user);

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
