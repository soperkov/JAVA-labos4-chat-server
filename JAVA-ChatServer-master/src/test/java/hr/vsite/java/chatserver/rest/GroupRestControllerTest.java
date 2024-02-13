package hr.vsite.java.chatserver.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class GroupRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createGroup() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.put("/groups/test")
                .content("{\"groupName\":\"test\"}")
                .contentType("application/json"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is(200));

        mockMvc
                .perform(MockMvcRequestBuilders.get("/groups")
                        .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
