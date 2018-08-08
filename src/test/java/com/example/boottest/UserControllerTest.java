package com.example.boottest;

import com.example.boottest.controller.UserController;
import com.example.boottest.dto.UserRegisterDto;
import com.example.boottest.service.AuthService;
import com.example.boottest.service.UserService;
import com.example.boottest.util.JwtTokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    UserService userService;
    @MockBean
    JwtTokenUtil jwtTokenUtil;

    @Test
    public void testMvc() throws Exception {
        int userId = 6;
        UserRegisterDto dto = new UserRegisterDto();
        dto.setUsername("delores");
        dto.setPassword("8542a5ee537cc2c1a9d0548b136a42f6");

        given(this.userService.findById(anyInt())).willReturn(dto);
        UserRegisterDto result = userService.findById(userId);

        mvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isOk())
        .andReturn().getAsyncResult().equals(dto.toString());// http status is 401 instead of 200
    }
}
