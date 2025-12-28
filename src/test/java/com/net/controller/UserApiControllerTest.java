package com.net.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.net.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_return_all_users() throws Exception {
        mockMvc.perform(get("/mysql/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[0].email").value("alice@example.com"));
    }

    @Test
    public void should_return_user_by_id() throws Exception {
        mockMvc.perform(get("/mysql/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.email").value("alice@example.com"));
    }

    @Test
    public void should_create_user() throws Exception {
        User newUser = new User();
        newUser.setName("Bob");

        String json = objectMapper.writeValueAsString(newUser);

        mockMvc.perform(post("/mysql/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Bob"))
                .andExpect(jsonPath("$.email").value("bob@example.com"));
    }

    @Test
    public void should_update_user() throws Exception {
        User updatedUser = new User();
        updatedUser.setName("Bob");

        String json = objectMapper.writeValueAsString(updatedUser);

        mockMvc.perform(put("/mysql/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Bob Updated"))
                .andExpect(jsonPath("$.email").value("bob.updated@example.com"));
    }

    @Test
    public void should_delete_user() throws Exception {
        mockMvc.perform(delete("/mysql/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_return_404_if_user_not_found() throws Exception {
        mockMvc.perform(get("/mysql/users/999"))
                .andExpect(status().isNotFound());
    }


}
