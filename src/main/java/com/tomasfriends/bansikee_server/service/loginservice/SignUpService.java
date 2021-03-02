package com.tomasfriends.bansikee_server.service.loginservice;

import com.tomasfriends.bansikee_server.domain.login.jwt.JWT;
import com.tomasfriends.bansikee_server.domain.login.profile.KakaoProfile;
import com.tomasfriends.bansikee_server.dto.User;
import com.tomasfriends.bansikee_server.repository.loginrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignUpService {

    private final UserRepository userRepository;

    @Autowired
    public SignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String signUp(KakaoProfile profile) {
        String nickname = profile.getProperties().getNickname();
        String profileImage = profile.getProperties().getProfile_image();
        String email = profile.getKakao_account().getEmail();

        signUpIfNotUser(nickname, email, profileImage);

        return JWT.getJWT(nickname, email, profileImage);
    }

    private void signUpIfNotUser(String nickname, String email, String profileImage) {
        Optional<User> userByEmail = userRepository.findByEmail(email);

        if (userByEmail.isEmpty()) {
            userRepository.save(User.builder()
                .name(nickname)
                .email(email)
                .profileImageURL(profileImage)
                .build());
        }
    }
}