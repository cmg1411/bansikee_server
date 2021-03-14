package com.tomasfriends.bansikee_server.sign.service.userservice;

import com.tomasfriends.bansikee_server.sign.domain.LoginMethod;
import com.tomasfriends.bansikee_server.jwt.JwtProvider;
import com.tomasfriends.bansikee_server.sign.dto.controllerdto.BasicLoginUserRequest;
import com.tomasfriends.bansikee_server.sign.dto.servicedto.BansikeeUser;
import com.tomasfriends.bansikee_server.sign.exceptions.*;
import com.tomasfriends.bansikee_server.sign.repository.loginrepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

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

    public Boolean getIsOnboardedService(int id) {
        Optional<BansikeeUser> user = userRepository.findById(id);
        BansikeeUser bansikeeUser = user.orElseThrow(NotRegisteredUserIdException::new);
        return bansikeeUser.isOnBoarding();
    }

    public Boolean nickNameCheck(String nickName) {
        Optional<BansikeeUser> userByNickName = userRepository.findByName(nickName);
        return userByNickName.isPresent();
    }
}
