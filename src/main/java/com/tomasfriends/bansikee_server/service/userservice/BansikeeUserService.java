package com.tomasfriends.bansikee_server.service.userservice;

import com.tomasfriends.bansikee_server.domain.LoginMethod;
import com.tomasfriends.bansikee_server.domain.jwt.JwtProvider;
import com.tomasfriends.bansikee_server.dto.controllerdto.BasicLoginUserRequest;
import com.tomasfriends.bansikee_server.dto.servicedto.BansikeeUser;
import com.tomasfriends.bansikee_server.exceptions.AlreadySignedUpException;
import com.tomasfriends.bansikee_server.exceptions.HaveToSignInWithOauthException;
import com.tomasfriends.bansikee_server.exceptions.InvalidPasswordException;
import com.tomasfriends.bansikee_server.exceptions.NotRegisteredEmailException;
import com.tomasfriends.bansikee_server.repository.loginrepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class BansikeeUserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public void signUp(BasicLoginUserRequest user) {
        isAlreadyExistUser(user);

        userRepository.save(BansikeeUser.builder()
            .email(user.getEmail())
            .password(passwordEncoder.encode(user.getPassword()))
            .name(user.getName())
            .loginMethod(LoginMethod.NORMAL.name())
            .roles(Collections.singletonList("ROLE_USER"))
            .build());
    }

    private void isAlreadyExistUser(BasicLoginUserRequest user) {
        if(userRepository.findTopByEmail(user.getEmail()).isPresent()) {
            throw new AlreadySignedUpException();
        }
    }

    public String signIn(String email, String password) {
        BansikeeUser user = userRepository.findByEmail(email).orElseThrow(NotRegisteredEmailException::new);
        isNormalUser(user.getLoginMethod());
        isValidPassword(password, user.getPassword());
        return jwtProvider.getJWT(user, user.getRoles());
    }

    private void isNormalUser(String loginMethod) {
        if (!loginMethod.equals("NORMAL")) {
            throw new HaveToSignInWithOauthException();
        }
    }

    private void isValidPassword(String password, String passwordRe) {
        if (!passwordEncoder.matches(password, passwordRe)) {
            throw new InvalidPasswordException();
        }
    }

    public BansikeeUser findByEmailService(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(NotRegisteredEmailException::new);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
