package hr.vsite.java.chatserver.db;

import java.util.List;

public interface GroupRepositoryCustom {
    List<Group> search(String groupName);
}