package com.tomasfriends.bansikee_server.repository;

import com.tomasfriends.bansikee_server.domain.Options;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOptionsRepository extends JpaRepository<Options, Integer>, OptionsRepository {

    @Override
    List<Options> findAll();

}
