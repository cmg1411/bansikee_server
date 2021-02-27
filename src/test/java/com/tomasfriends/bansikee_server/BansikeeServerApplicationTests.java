package com.tomasfriends.bansikee_server;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BansikeeServerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void test() {
		Assertions.assertThat(1).isEqualTo(1);
	}
}
