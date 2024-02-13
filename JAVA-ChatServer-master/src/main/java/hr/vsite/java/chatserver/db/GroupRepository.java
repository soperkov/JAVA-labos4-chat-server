package hr.vsite.java.chatserver.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, String>, GroupRepositoryCustom {
    List<Group> findByGroupNameStartsWith(String groupName);
}