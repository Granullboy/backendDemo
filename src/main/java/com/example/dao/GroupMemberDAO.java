package com.example.dao;
/*
import com.example.moduls.GroupMember;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class GroupMemberDAO implements DAO{
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public GroupMemberDAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public GroupMember getGroupMemberById(int id) { return entityManager.find(GroupMember.class, id); }

    public List<GroupMember> getAllGroupMembers() { return entityManager.createQuery("from GroupMember").getResultList(); }
    public List<GroupMember> getGroupMembersByGroupId(int id) { return entityManager.createQuery("from GroupMember where groupId = " + id).getResultList(); }

    public GroupMember addGroupMember(GroupMember groupMember) {
        entityManager.getTransaction().begin();
        entityManager.persist(groupMember);
        entityManager.getTransaction().commit();
        return entityManager.merge(groupMember);
    }

    public GroupMember updateGroupMember(GroupMember groupMember) {
        entityManager.getTransaction().begin();
        entityManager.merge(groupMember);
        entityManager.getTransaction().commit();
        return entityManager.merge(groupMember);
    }

    public void deleteGroupMember(int id) {
        entityManager.getTransaction().begin();
        GroupMember groupMember = entityManager.find(GroupMember.class, id);
        entityManager.remove(groupMember);
        entityManager.getTransaction().commit();
    }
}*/
