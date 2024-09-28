package com.example.moduls;

public abstract class Message {
    private User user_id;
    private String text;
    private boolean edited;

    public Message() {}

    public Message(User user_id, String text, boolean edited) {
        this.user_id = user_id;
        this.text = text;
        this.edited = edited;
    }

    public User getUser_id() { return user_id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public boolean isEdited() { return edited; }
    public void setEdited(boolean edited) { this.edited = edited; }

    @Override
    public String toString() {
        return "Message{" +
                "user_id=" + user_id +
                ", text='" + text + '\'' +
                ", edited=" + edited +
                '}';
    }
}
