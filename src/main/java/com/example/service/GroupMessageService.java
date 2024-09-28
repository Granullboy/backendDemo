package com.example.service;

import com.example.dao.GroupDAO;
import com.example.dao.GroupMessageDAO;
import com.example.moduls.Group;
import com.example.moduls.GroupMessage;

import java.util.List;

public class GroupMessageService {
    private final GroupMessageDAO messageDAO;
    public GroupMessageService() { this.messageDAO = new GroupMessageDAO(); }
    public List<GroupMessage> getAllGroupMessages() { return messageDAO.getAllGroupMessages(); }
    public GroupMessage getGroupMessageById(int id) { return messageDAO.getGroupMessageById(id); }
    public List<GroupMessage> getMessagesByGroupId(int id) { return messageDAO.getMessagesByGroupId(id); }

    public GroupMessage addGroup(GroupMessage message) { return messageDAO.addGroupMessage(message); }
    public GroupMessage updateGroup(GroupMessage message) { return messageDAO.updateGroupMessage(message); }
    public void deleteGroup(int id) {messageDAO.deleteGroupMessage(id); }
}
