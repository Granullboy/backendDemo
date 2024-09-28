package com.example.dao;

import com.example.moduls.Group;
import com.example.moduls.GroupMessage;
import com.example.moduls.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Date;
import java.util.List;

public class GroupMessageDAO implements DAO{
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public GroupMessageDAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public GroupMessage getGroupMessageById(int id) {
        GroupMessage groupMessage = entityManager.find(GroupMessage.class, id);
        if (groupMessage == null) { return null; }

        groupMessage.getGroup_id().setTitleOnly();
        groupMessage.getUser_id().setOnlyUsername();
        return groupMessage;
    }

    public List<GroupMessage> getMessagesByGroupId(int id) {
        List<GroupMessage> groupMessages = null;
        try {
            groupMessages = entityManager.createQuery("SELECT new GroupMessage(message_id, user_id, text, edited, created_at, edited_at) FROM GroupMessage g WHERE group_id = " + id, GroupMessage.class).getResultList();
        } catch (Exception e) { e.printStackTrace(); }
        System.out.println(groupMessages);
        if (groupMessages == null) { return null; }
        if (groupMessages.isEmpty()) { return null; }

        groupMessages.forEach(groupMessage -> {
            groupMessage.getUser_id().setOnlyUsername();
        });
        return groupMessages;
    }

    public List<GroupMessage> getAllGroupMessages() {
        List<GroupMessage> groupMessages = entityManager.createQuery("from GroupMessage", GroupMessage.class).getResultList();
        if (groupMessages.isEmpty()) { return null; }

        groupMessages.forEach(groupMessage -> {
            groupMessage.getGroup_id().setTitleOnly();
            groupMessage.getUser_id().setOnlyUsername();
        });
        return groupMessages;
    }

    public GroupMessage addGroupMessage(GroupMessage groupMessage) {
        entityManager.getTransaction().begin();

        int user_id = groupMessage.getUser_id().getUser_id();
        int group_id = groupMessage.getMessage_id();

        User user = entityManager.find(User.class, user_id);
        groupMessage.setUser_id(user);

        Group group = entityManager.find(Group.class, group_id);
        groupMessage.setGroup_id(group);

        //groupMessage.getGroup_id().setUsers(null);
        //groupMessage.getUser_id().setGroups(null);
        groupMessage.setCreated_at(new Date());
        groupMessage.setEdited_at(groupMessage.getCreated_at());
        groupMessage.setMessage_id(0);
        entityManager.persist(groupMessage);
        entityManager.getTransaction().commit();

        groupMessage.getGroup_id().setTitleOnly();
        groupMessage.getUser_id().setOnlyUsername();
        return groupMessage;
    }

    public GroupMessage updateGroupMessage(GroupMessage groupMessage) {
        entityManager.getTransaction().begin();
        groupMessage.setEdited(true);
        groupMessage.setEdited_at(new Date());
        entityManager.merge(groupMessage);
        entityManager.getTransaction().commit();
        return groupMessage;
    }

    public GroupMessage deleteGroupMessage(int id) {
        entityManager.getTransaction().begin();
        GroupMessage groupMessage = entityManager.find(GroupMessage.class, id);
        entityManager.remove(groupMessage);
        entityManager.getTransaction().commit();
        return groupMessage;
    }
}
