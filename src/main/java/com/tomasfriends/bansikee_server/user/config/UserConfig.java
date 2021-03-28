package com.tomasfriends.bansikee_server.user.config;

import com.tomasfriends.bansikee_server.user.repository.FavoritesUserRepository;
import com.tomasfriends.bansikee_server.user.repository.PlantUserRepository;
import com.tomasfriends.bansikee_server.user.repository.UserMyPageRepository;
import com.tomasfriends.bansikee_server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    private final FavoritesUserRepository favoritesUserRepository;
    private final PlantUserRepository plantUserRepository;
    private final UserMyPageRepository userMyPageRepository;

    @Autowired
    public UserConfig(FavoritesUserRepository favoritesUserRepository,PlantUserRepository plantUserRepository, UserMyPageRepository userMyPageRepository){
        this.favoritesUserRepository = favoritesUserRepository;
        this.plantUserRepository = plantUserRepository;
        this.userMyPageRepository = userMyPageRepository;
    }
    @Bean
    public UserService userService(){
        return new UserService(favoritesUserRepository, userMyPageRepository);
    }
}
