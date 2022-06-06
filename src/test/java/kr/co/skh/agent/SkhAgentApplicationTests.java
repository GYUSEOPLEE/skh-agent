package kr.co.skh.agent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
class SkhAgentApplicationTests {
    @Test
    void contextLoads() {
        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
        System.out.println(localDateTime);
    }
}
