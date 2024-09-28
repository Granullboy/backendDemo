package com.example.service;

import com.example.dao.GroupDAO;
import com.example.moduls.Group;

import java.util.List;

public class GroupService {
    private final GroupDAO groupDAO;
    public GroupService() { this.groupDAO = new GroupDAO(); }
    public List<Group> getAllGroups() { return groupDAO.getAllGroups(); }
    public Group getGroupById(int id) { return groupDAO.getGroupById(id); }
    public List<Group> getGroupByOwnerId(int id) { return groupDAO.getGroupByOwnerId(id); }
    public Group addGroup(Group group) { return groupDAO.addGroup(group); }
    public Group updateGroup(Group group) { return groupDAO.updateGroup(group); }
    public void deleteGroup(Group group) {groupDAO.deleteGroup(group); }
}
