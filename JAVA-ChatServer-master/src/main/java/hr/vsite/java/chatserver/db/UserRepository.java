package hr.vsite.java.chatserver.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom {
    List<User> findByUserNameStartsWith(String userName);
}
