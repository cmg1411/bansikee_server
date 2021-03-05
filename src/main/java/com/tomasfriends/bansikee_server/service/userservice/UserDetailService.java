package com.tomasfriends.bansikee_server.service.userservice;

import com.tomasfriends.bansikee_server.domain.LoginMethod;
import com.tomasfriends.bansikee_server.domain.jwt.JwtProvider;
import com.tomasfriends.bansikee_server.dto.controllerdto.BasicLoginUserRequest;
import com.tomasfriends.bansikee_server.dto.servicedto.BansikeeUser;
import com.tomasfriends.bansikee_server.exceptions.AlreadySignedUpException;
import com.tomasfriends.bansikee_server.exceptions.InvalidPasswordException;
import com.tomasfriends.bansikee_server.exceptions.NotRegisteredEmailException;
import com.tomasfriends.bansikee_server.repository.loginrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  JwtProvider jwtProvider;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(NotRegisteredEmailException::new);
    }

    public void signIn(BasicLoginUserRequest user) {
        if(userRepository.findTopByEmail(user.getEmail()).isPresent()) {
            throw new AlreadySignedUpException();
        }

        userRepository.save(BansikeeUser.builder()
            .email(user.getEmail())
            .password(passwordEncoder.encode(user.getPassword()))
            .name(user.getName())
            .loginMethod(LoginMethod.NORMAL.name())
            .roles(Collections.singletonList("ROLE_USER"))
            .build());
    }

    public String validatePassword(String email, String password) {
        BansikeeUser user = userRepository.findByEmail(email).orElseThrow(NotRegisteredEmailException::new);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException();
        }
        return jwtProvider.getJWT(user, user.getRoles());
    }
}
