package com.example.boottest;

import com.example.boottest.dto.UserRegisterDto;
import com.example.boottest.service.AuthService;
import com.example.boottest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoottestApplicationTests {
    @Autowired
    UserService userService;

    @MockBean
    private AuthService creditSystemService;

    @Test
    public void testUserService() {
        int userId = 6;
        UserRegisterDto dto = new UserRegisterDto();
        dto.setUsername("delores");
        dto.setPassword("8542a5ee537cc2c1a9d0548b136a42f6");
        UserRegisterDto result = userService.findById(userId);
        given(this.creditSystemService.login(anyString(), anyString())).willReturn(dto.getUsername());

        assertEquals(dto.getUsername(), result.getUsername());
        assertEquals(dto.getPassword(), result.getPassword());
    }
}
