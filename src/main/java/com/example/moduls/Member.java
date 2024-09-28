package com.example.moduls;

public abstract class Member {
    private User user_id;
    private Group group_id;

    public Member() {}
    public Member(User user_id, Group group_id) {
        this.user_id = user_id;
        this.group_id = group_id;
    }

    public User getUser_id() { return user_id; }
    public Group getGroup_id() { return group_id; }

    @Override
    public String toString() {
        return "Member{" +
                "user_id=" + user_id +
                ", group_id=" + group_id +
                '}';
    }
}
