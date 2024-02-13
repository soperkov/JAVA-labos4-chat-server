package hr.vsite.java.chatserver.domain;

import hr.vsite.java.chatserver.db.Group;
import hr.vsite.java.chatserver.db.GroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public List<Group> findGroups(String groupName) {
        return groupRepository.search(groupName);
    }

    public void createGroup(Group group) {
        if (!StringUtils.hasText(group.getGroupName())) {
            throw new RuntimeException("Group name must be provided");
        }
        groupRepository.save(group);
    }

    public void deleteGroup(String groupName) {
        groupRepository.deleteById(groupName);
    }
}