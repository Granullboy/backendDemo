package com.example.moduls;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/*
@Entity
@Table(name = "group_members")
public class GroupMember extends Member {
    @Id
    private Long id;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "users",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "user_id")}
    )
    private Set<User> users = new HashSet<User>();
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "groups",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = { @JoinColumn(name = "group_id")}
    )
    private Set<Group> groups = new HashSet<Group>();

    public GroupMember() {}

    public GroupMember(Long id) { this.id = id; }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setUsers(Set<User> users) { this.users = users; }
    public void addUser(User user) { this.users.add(user); }
    public Set<User> getUsers() { return users; }
    public void setGroups(Set<Group> groups) { this.groups = groups; }
    public void addGroup(Group group) { this.groups.add(group); }
    public Set<Group> getGroups() { return groups; }

}*/
