package com.tomasfriends.bansikee_server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class DBConnectionTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @DisplayName("RDB Mysql 연결 테스트")
    @Test
    void ConnectionTest() {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("커넥션 : "  + connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
