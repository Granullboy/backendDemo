package com.example.moduls;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @ManyToMany(cascade = {CascadeType.ALL} )
    @JoinTable(
            name = "group_members",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "group_id")}
    )
    private Set<Group> groups = new HashSet<Group>();

    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(int user_id){
        this.user_id = user_id;
    }

    public User(int user_id, String username) {
        this.user_id = user_id;
        this.username = username;
    }

    public User(int user_id, String username, String email, String password) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(int user_id, String username, String email, String password, Set<Group> groups) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.groups = groups;
        this.groups.forEach(Group::setTitleOnly);
    }

    public void setOnlyUsername(){
        this.groups = null;
        this.email = null;
        this.password = null;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Set<Group> getGroups() { return groups; }
    public void setGroups(Set<Group> groups) { this.groups = groups; }
    public void addGroup(Group group) { this.groups.add(group); }
    public void removeGroup(Group group) { this.groups.remove(group); }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", groups=" + groups +
                '}';
    }
}

