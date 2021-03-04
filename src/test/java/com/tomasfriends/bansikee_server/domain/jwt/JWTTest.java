package com.tomasfriends.bansikee_server.domain.jwt;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JWTTest {

    @Value("${properties.jwt.secretKey}")
    private String key;

    @DisplayName("@value 테스트")
    @Test
    void getValue() {
        Assertions.assertThat(key).isEqualTo("tomasWithFriends");
    }
}