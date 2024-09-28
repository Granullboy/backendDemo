package com.example.moduls;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="groups")
public class Group {

    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int group_id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "createdByUser")
    @NotNull
    private User user_id;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "group_members",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = { @JoinColumn(name = "user_id")}
    )
    private Set<User> users = new HashSet<User>();

    @Column(name = "title")
    private String title;

    @Column(name = "createdBy")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdBy;

    @Column(name = "_private")
    private boolean _private;

    public Group() {}

    public Group(int group_id) {
        this.group_id = group_id;
    }

    public Group(String title, Date createdBy, boolean _private) {
        this.title = title;
        this.createdBy = createdBy;
        this._private = _private;
    }

    public Group(int group_id, String title, Date createdBy, boolean _private) {
        this.group_id = group_id;
        this.title = title;
        this.createdBy = createdBy;
        this._private = _private;
    }

    public Group(int group_id, User user_id, Set<User> users, String title, Date createdBy, boolean _private) {
        this.group_id = group_id;
        this.user_id = user_id;
        users.forEach(user -> this.user_id.setOnlyUsername());
        this.users = users;
        this.title = title;
        this.createdBy = createdBy;
        this._private = _private;
    }

    public void setTitleOnly(){ setTitleOnly(true); }

    public void setTitleOnly(boolean _users){
        this.user_id = null;
        if (_users)
            this.users = null;
        this.createdBy = null;
        if (_private)
            this.title = "private";
    }
    public int getGroup_id() {return group_id;}

    public User getUser_id() {return user_id;}
    public void setUser_id(User user_id) {this.user_id = user_id;}
    public void addUser(User user) {
        if (_private && users.size() >= 2)
            return;
        this.users.add(user);
    }

    public Set<User> getUsers() { return users; }
    public void setUsers(Set<User> users) { this.users = users; }

    public String getTitle() {return title;}

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Date createdBy) {}

    public boolean is_private() { return _private; }
    public void set_private(boolean _private) { this._private = _private; }
    @Override
    public String toString() {
        return "Group{" +
                "group_id=" + group_id +
                ", user_id=" + user_id +
                ", title='" + title + '\'' +
                ", createdBy=" + createdBy + '\'' +
                ", is_private=" + _private +
                '}';
    }
}

