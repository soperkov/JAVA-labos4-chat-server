package hr.vsite.java.chatserver.rest;


import hr.vsite.java.chatserver.db.Group;
import hr.vsite.java.chatserver.db.User;
import hr.vsite.java.chatserver.domain.GroupService;
import hr.vsite.java.chatserver.domain.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
    void createMessage() throws Exception {
        createTestUser();
        createTestGroup();

        mockMvc
            .perform(MockMvcRequestBuilders.put("/messages")
                .content("{\"message\":\"testMessage\", \"user\": {\"userName\":\"testUser\"}, \"group\": {\"groupName\":\"testGroup\"}}")
                .contentType("application/json"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is(200));

        mockMvc
                .perform(MockMvcRequestBuilders.get("/messages")
                        .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
