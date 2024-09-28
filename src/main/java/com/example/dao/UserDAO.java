package com.example.dao;

import com.example.moduls.Group;
import com.example.moduls.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Set;

public class UserDAO implements DAO {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public UserDAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public User getUserById(int userId) {
        User user = entityManager.find(User.class, userId);
        if (user == null) { return null; }

        for (Group group : user.getGroups()) {
            group.setTitleOnly(false);
        }
        return user;
    }


    public User getUserByUsername(String username) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE username = \'" + username + "\'", User.class).getResultList().get(0);
        if (user == null) { return null; }
        for (Group group : user.getGroups()) {
            group.setTitleOnly();
        }
        return user;
    }

    public User loginUser(String username, String password) {
        User user = getUserByUsername(username);
        if (user == null) { return null; }
        if (!user.getPassword().equals(password)) { return null; }

        user.setGroups(null);
        return user;
    }

    public User registerUser(User user) {
        User checkUser = null;
        try {
            checkUser = getUserByUsername(user.getUsername());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (checkUser != null) {
            return null;
        } else {
            addUser(user);
            return user;
        }
    }

    public Set<Group> getGroupsByUser(int userId) {
        User user = getUserById(userId);
        if (user == null) { return null; }
        if (user.getGroups() == null) { return null; }
        for (Group group : user.getGroups()) {
            group.getUsers().forEach(u -> {
                if (group.is_private() && u.getUser_id() != userId) {
                    group.setTitle(u.getUsername());
                }
            });
            group.setUsers(null);
        }
        return user.getGroups();
    }

    public List<User> getAllUsers() {
        List<User> users = entityManager.createQuery("SELECT new User(user_id, username, email, password) FROM User u", User.class).getResultList();
        if (users.isEmpty()) { return null; }

        users.forEach(User::setOnlyUsername);
        return users;
    }

    public List<User> getUsersByName(String username) {
        List<User> users = entityManager.createQuery("SELECT new User(user_id, username) FROM User u WHERE username = \'" + username + "\'", User.class).getResultList();
        return users;
    }

    public void addUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public User updateUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        return user;
    }

    public void deleteUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }
}
