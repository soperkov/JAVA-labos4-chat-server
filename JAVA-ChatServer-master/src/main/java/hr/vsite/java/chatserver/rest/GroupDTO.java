package hr.vsite.java.chatserver.rest;

public class GroupDTO {
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "groupName='" + groupName + '\'' +
                '}';
    }
}
