package hr.vsite.java.chatserver.db;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> search(String userName);
}
