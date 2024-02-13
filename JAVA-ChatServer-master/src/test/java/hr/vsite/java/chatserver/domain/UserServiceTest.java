package hr.vsite.java.chatserver.domain;

import hr.vsite.java.chatserver.db.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void createUser() {
        User user = new User();
        user.setUserName("test");
        userService.createUser(user);

        var result = userService.findUsers("test").getFirst();

        Assertions.assertEquals(user.getUserName(), result.getUserName());
    }

    @Test
    void createUserNullName() {
        User user = new User();

        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });
    }

    @Test
    void createUserEmptyName() {
        User user = new User();
        user.setUserName("");

        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });
    }

    @Test
    void createUserNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.createUser(null);
        });
    }
}
