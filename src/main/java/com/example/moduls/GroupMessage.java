package com.example.moduls;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "messages")
public class GroupMessage extends Message {
    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int message_id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @NotNull
    private User user_id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "group_id")
    @NotNull
    private Group group_id;

    @Column(name = "text")
    @NotNull
    private String text;

    @Column(name = "edited", nullable = false)
    private boolean edited;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date created_at;

    @Column(name = "edited_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date edited_at;

    public GroupMessage() {}

    public GroupMessage(int message_id, User user_id, String text, boolean edited, Date created_at, Date edited_at) {
        this.message_id = message_id;
        this.user_id = user_id;
        this.user_id.setOnlyUsername();
        this.text = text;
        this.edited = edited;
        this.created_at = created_at;
        this.edited_at = edited_at;
    }
    public GroupMessage(int message_id, User user_id, Group group_id, String text, boolean edited, Date created_at, Date edited_at) {
        this.message_id = message_id;
        this.user_id = user_id;
        this.user_id.setOnlyUsername();
        this.group_id = group_id;
        this.group_id.setUser_id(null);
        this.group_id.setUsers(null);
        this.text = text;
        this.edited = edited;
        this.created_at = created_at;
        this.edited_at = edited_at;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    @Override
    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Group getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Group group_id) {
        this.group_id = group_id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean isEdited() {
        return edited;
    }

    @Override
    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public Date getCreated_at() { return this.created_at; }

    public void setCreated_at(Date created_at) { this.created_at = created_at; }

    public Date getEdited_at() { return this.edited_at; }

    public void setEdited_at(Date edited_at) {}

    @Override
    public String toString() {
        return "GroupMessage{" +
                "message_id=" + message_id +
                ", user_id=" + user_id +
                ", group_id=" + group_id +
                ", text='" + text + '\'' +
                ", edited=" + edited +
                ", created_at=" + created_at +
                ", edited_at=" + edited_at +
                '}';
    }
}
