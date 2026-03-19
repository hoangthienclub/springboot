package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc // tạo mock request đến controller
@TestPropertySource("/test.properties")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; // tạo mock request gọi tới api

    @MockitoBean
    private UserService userService; // dùng để mock Bean

    private UserCreationRequest request;
    private UserResponse userResponse;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(1990, 1, 1);
        request = UserCreationRequest.builder()
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .password("12345678")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("cf0600f538b3")
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        // GIVEN
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);

        when(userService.createUser(any())).thenReturn(userResponse);

        // WHEN, THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id").value(userResponse.getId()));
    }

    @Test
    void createUser_usernameInvalid_fail() throws Exception {
        // GIVEN
        request.setUsername("abc");
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);

        // WHEN, THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1004))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Username must be at least 4 characters"));
    }

    @Test
    void createUser_passwordInvalid_fail() throws Exception {
        // GIVEN
        request.setPassword("abc");
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);

        // WHEN, THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1005))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Password must be at least 8 characters"));
    }

    @Test
    void createUser_dobInvalid_fail() throws Exception {
        // GIVEN
        request.setDob(LocalDate.now());
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);

        // WHEN, THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1009))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Your age must be at least 18"));
    }
}
