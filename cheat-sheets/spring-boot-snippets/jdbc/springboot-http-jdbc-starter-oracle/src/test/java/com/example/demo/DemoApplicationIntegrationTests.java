
package com.example.demo;

import com.example.demo.repository.MyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DemoApplicationIntegrationTests
{
    @Autowired
    private MyRepository myRepository;

    @Test
    void testReadUsers() {
        String name = myRepository.readUsers();
        assertThat(name).isNotNull();
    }

    @Test
    void testUpdateUser() {
        myRepository.updateUser();
        String name = myRepository.readUsers();
        assertThat(name).isEqualTo("NewName");
    }
}
