package hr.vsite.java.chatserver.db;

import java.util.List;

public interface MessageRepositoryCustom {
    List<Message> search(int id);
}
