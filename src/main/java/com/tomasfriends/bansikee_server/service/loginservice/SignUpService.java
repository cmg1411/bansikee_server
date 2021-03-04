package com.tomasfriends.bansikee_server.service.loginservice;

import com.tomasfriends.bansikee_server.domain.jwt.JwtProvider;
import com.tomasfriends.bansikee_server.dto.oauthprofile.GoogleProfile;
import com.tomasfriends.bansikee_server.dto.oauthprofile.KakaoProfile;
import com.tomasfriends.bansikee_server.dto.User;
import com.tomasfriends.bansikee_server.repository.loginrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Autowired
    public SignUpService(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public String signUpWithKakao(KakaoProfile profile) {
        String nickname = profile.getProperties().getNickname();
        String profileImage = profile.getProperties().getProfile_image();
        String email = profile.getKakao_account().getEmail();

        signUpIfNotUser(nickname, email, profileImage);

        return jwtProvider.getJWT(nickname, email, profileImage);
    }

    public String signUpWithGoogle(GoogleProfile profile) {
        String nickname = profile.getGiven_name();
        String profileImage = profile.getPicture();
        String email = profile.getEmail();

        signUpIfNotUser(nickname, email, profileImage);

        return jwtProvider.getJWT(nickname, email, profileImage);
    }

    private void signUpIfNotUser(String nickname, String email, String profileImage) {
        Optional<User> userByEmail = userRepository.findByEmail(email);

        if (userByEmail.isPresent()) return ;

        userRepository.save(User.builder()
            .name(nickname)
            .email(email)
            .profileImageURL(profileImage)
            .build());
    }
}