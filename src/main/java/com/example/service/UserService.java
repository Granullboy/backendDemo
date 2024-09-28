package com.example.service;

import com.example.dao.UserDAO;
import com.example.moduls.Group;
import com.example.moduls.GroupMessage;
import com.example.moduls.User;
import java.util.List;
import java.util.Set;

public class UserService {

    private final UserDAO userDao;
    public UserService() { this.userDao = new UserDAO(); }
    public User Login(String username, String password) { return this.userDao.loginUser(username, password); }
    public User Register(User user) { return this.userDao.registerUser(user); }
    public User getUserById(int userId) {
        User user = userDao.getUserById(userId);
        user.getGroups().forEach(Group::setTitleOnly);
        return user;
    }
    public Set<Group> getGroupsByUser(int userId) {return userDao.getGroupsByUser(userId); }
    public List<User> getAllUsers() { return userDao.getAllUsers(); }
    public void addUser(User user) { userDao.addUser(user); }
    public User updateUser(User user) { return userDao.updateUser(user); }
    public void deleteUser(User user) { userDao.deleteUser(user); }
    public List<User> getUsersByName(String username) { return userDao.getUsersByName(username); }
}
