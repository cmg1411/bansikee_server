package com.tomasfriends.bansikee_server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

//    @Autowired
//    UserRepository userRepository;
//
//    @DisplayName("의존성 주입 테스트")
//    @Test
//    void di() throws SQLException {
//        //given
//        User user = new User();
//        user.setEmail("cmg1411@naver.com");
//
//        //when
//        User savedUser = userRepository.save(user);
//        Optional<User> foundedUser = userRepository.findById(user.getUserIdx());
//        User user1 = foundedUser.get();
//
//        //then
//        assertThat(savedUser).isNotNull();
//        assertThat(user1.getEmail()).isEqualTo(user.getEmail());
//    }
}