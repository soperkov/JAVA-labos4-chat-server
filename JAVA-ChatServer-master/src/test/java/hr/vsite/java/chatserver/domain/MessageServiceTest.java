package hr.vsite.java.chatserver.domain;

import hr.vsite.java.chatserver.db.Group;
import hr.vsite.java.chatserver.db.Message;
import hr.vsite.java.chatserver.db.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    private User user = new User();
    private Group group = new Group();

    void createTestUser() {
        user.setUserName("testUser");
        user.setName("testName");
        user.setSurname("testSurname");
        userService.createUser(user);
    }

    void createTestGroup() {
        group.setGroupName("testGroup");
        groupService.createGroup(group);
    }

    @Test
    void createMessage() {
        createTestUser();
        createTestGroup();

        Message message = new Message();
        message.setMessage("test");
        message.setUser(user);
        message.setGroup(group);
        int id = messageService.createMessage(message);

        var result = messageService.findMessages(id).getFirst();

        org.assertj.core.api.Assertions
                .assertThat(message)
                .usingRecursiveComparison()
                .isEqualTo(result);
    }

    @Test
    void createMessageNullMessage() {
        createTestUser();
        createTestGroup();

        Message message = new Message();
        message.setUser(user);
        message.setGroup(group);

        org.assertj.core.api.Assertions
                .assertThatThrownBy(() -> messageService.createMessage(message))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Message, user and group must be provided");
    }

    @Test
    void createMessageNullUser() {
        createTestGroup();

        Message message = new Message();
        message.setMessage("test");
        message.setGroup(group);

        org.assertj.core.api.Assertions
                .assertThatThrownBy(() -> messageService.createMessage(message))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Message, user and group must be provided");
    }

    @Test
    void createMessageNullGroup() {
        createTestUser();

        Message message = new Message();
        message.setMessage("test");
        message.setUser(user);

        org.assertj.core.api.Assertions
                .assertThatThrownBy(() -> messageService.createMessage(message))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Message, user and group must be provided");
    }

    @Test
    void createMessageNull() {
        org.assertj.core.api.Assertions
                .assertThatThrownBy(() -> messageService.createMessage(null))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void deleteMessage() {
        createTestUser();
        createTestGroup();

        Message message = new Message();
        message.setMessage("test");
        message.setUser(user);
        message.setGroup(group);
        int id = messageService.createMessage(message);

        message.setId(id);
        messageService.deleteMessage(message);

        var result = messageService.findMessages(id);

        org.assertj.core.api.Assertions
                .assertThat(result)
                .isEmpty();
    }



}
