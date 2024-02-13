package hr.vsite.java.chatserver.rest;

import hr.vsite.java.chatserver.db.Group;
import hr.vsite.java.chatserver.domain.GroupService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController()
public class GroupRestController {
    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GroupRestController.class);

    private final GroupService groupService;

    public GroupRestController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PutMapping("/groups/{groupName}")
    public void createGroup(@PathVariable String groupName, @RequestBody GroupDTO groupDTO) {
        logger.info("Creating group {} - {}", groupName, groupDTO);
        Group group = new Group();
        group.setGroupName(groupName);
        groupService.createGroup(group);
    }

    @DeleteMapping("/groups/{groupName}")
    public void deleteGroup(@PathVariable String groupName) {
        logger.info("Deleting group {}", groupName);
        groupService.deleteGroup(groupName);
    }

    @GetMapping("/groups")
    public List<GroupDTO> getGroups(String groupName) {
        logger.info("Getting groups {}", groupName);
        List<Group> groups;
        if (StringUtils.hasText(groupName)) {
            groups = groupService.findGroups(groupName);
        } else {
            groups = groupService.getGroups();
        }
        List<GroupDTO> groupDTOS = new LinkedList<>();
        for (Group group : groups) {
            groupDTOS.add(toDto(group));
        }
        return groupDTOS;
    }

    private GroupDTO toDto(Group group) {
        GroupDTO dto = new GroupDTO();
        dto.setGroupName(group.getGroupName());
        return dto;
    }
}