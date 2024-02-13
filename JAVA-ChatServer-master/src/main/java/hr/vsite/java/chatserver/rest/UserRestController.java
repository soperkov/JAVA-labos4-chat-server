package hr.vsite.java.chatserver.rest;

import hr.vsite.java.chatserver.db.User;
import hr.vsite.java.chatserver.domain.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class UserRestController {
    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/users/{userName}")
    public void createUser(@PathVariable String userName, @RequestBody UserDTO userDTO) {
        logger.info("Creating user {} - {}", userName, userDTO);
        User user = new User();
        user.setUserName(userName);
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        userService.createUser(user);
    }

    @DeleteMapping("/users/{userName}")
    public void deleteUser(@PathVariable String userName) {
        logger.info("Deleting user {}", userName);
        userService.deleteUser(userName);
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers(@RequestParam(required = false) String userName) {
        logger.info("Getting users {}", userName);
        List<User> users;
        if (StringUtils.hasText(userName)) {
            users = userService.findUsers(userName);
        } else {
            users = userService.getUsers();
        }
        List<UserDTO> userDTOS = new LinkedList<>();
        for (User user : users) {
            userDTOS.add(toDto(user));
        }
        return userDTOS;
    }

    private UserDTO toDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserName(user.getUserName());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        return dto;
    }

}
