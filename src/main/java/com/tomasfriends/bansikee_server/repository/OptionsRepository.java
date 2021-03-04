package com.tomasfriends.bansikee_server.repository;


import com.tomasfriends.bansikee_server.domain.Options;

import java.util.List;

public interface OptionsRepository {
    List<Options> findAll();
}
