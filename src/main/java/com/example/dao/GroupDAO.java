package com.example.dao;

import com.example.moduls.Group;
import com.example.moduls.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class GroupDAO implements DAO {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public GroupDAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Group getGroupById(int id) {
        Group group = entityManager.find(Group.class, id);
        if (group == null) { return null; }

        group.getUser_id().setOnlyUsername();
        for (User user : group.getUsers()) {
            user.setOnlyUsername();
        }
        return group;
    }

    public List<Group> getGroupByOwnerId(int id) {
        List<Group> groups = entityManager.createQuery("SELECT new Group(group_id, title, createdBy, _private) FROM Group g WHERE user_id = " + id, Group.class).getResultList();
        if (groups.isEmpty()) { return null; }
        return groups;
    }

    public List<Group> getAllGroups() {
        List<Group> groups = entityManager.createQuery("SELECT new Group(group_id, title, createdBy, _private) FROM Group g", Group.class).getResultList();
        if (groups.isEmpty()) { return null; }

        groups.forEach(group -> group.setUsers(null));
        return groups;
    }

    public Group addGroup(Group group) {
        String[] title = group.getTitle().split("¶"); //¶
        group.set_private(title[0].equals("true"));
        group.setTitle(title[1]);
        System.out.println(group);
        if (!group.is_private()) return null;

        entityManager.getTransaction().begin();
        int user_id = group.getUser_id().getUser_id();
        User user = entityManager.find(User.class, user_id);
        group.setUser_id(user);
        entityManager.persist(group);
        entityManager.getTransaction().commit();
        return group;
    }
    public Group updateGroup(Group group) {
        entityManager.getTransaction().begin();
        entityManager.merge(group);
        entityManager.getTransaction().commit();
        return group;
    }
    public void deleteGroup(Group group) {
        entityManager.getTransaction().begin();
        entityManager.remove(group);
        entityManager.getTransaction().commit();
    }
}
