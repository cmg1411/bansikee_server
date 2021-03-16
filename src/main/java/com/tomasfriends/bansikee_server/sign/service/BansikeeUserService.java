package com.tomasfriends.bansikee_server.sign.service;

import com.tomasfriends.bansikee_server.sign.domain.LoginMethod;
import com.tomasfriends.bansikee_server.jwt.JwtProvider;
import com.tomasfriends.bansikee_server.sign.service.dto.NickNameRequestDto;
import com.tomasfriends.bansikee_server.sign.service.dto.SignInRequestDto;
import com.tomasfriends.bansikee_server.sign.service.dto.SignInResponseDto;
import com.tomasfriends.bansikee_server.sign.service.dto.BasicLoginUserRequest;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import com.tomasfriends.bansikee_server.sign.service.exceptions.*;
import com.tomasfriends.bansikee_server.sign.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        BansikeeUser user = userRepository.findByEmail(signInRequestDto.getEmail()).orElseThrow(NotRegisteredEmailException::new);
        isNormalUser(user.getLoginMethod());
        isValidPassword(signInRequestDto.getPassword(), user.getPassword());
        return new SignInResponseDto(jwtProvider.getJWT(user, user.getRoles()), user.getName(), user.getEmail());
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

    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public Boolean getIsOnboardedService(int id) {
        Optional<BansikeeUser> user = userRepository.findById(id);
        BansikeeUser bansikeeUser = user.orElseThrow(NotRegisteredUserIdException::new);
        return bansikeeUser.isOnBoarding();
    }

    public Boolean nickNameCheck(NickNameRequestDto nickNameDto) {
        Optional<BansikeeUser> userByNickName = userRepository.findByName(nickNameDto.getNickName());
        return userByNickName.isPresent();
    }
}
