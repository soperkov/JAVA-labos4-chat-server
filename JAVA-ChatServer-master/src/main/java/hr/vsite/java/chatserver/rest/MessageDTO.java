package hr.vsite.java.chatserver.rest;

import hr.vsite.java.chatserver.db.Group;
import hr.vsite.java.chatserver.db.User;

public class MessageDTO {
    private String message;
    private User user;
    private Group group;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", user=" + user +
                ", group=" + group +
                '}';
    }
}
