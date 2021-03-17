package com.tomasfriends.bansikee_server.sign.service.oauth2;

import com.tomasfriends.bansikee_server.jwt.JwtProvider;
import com.tomasfriends.bansikee_server.sign.service.dto.SignInResponseDto;
import com.tomasfriends.bansikee_server.sign.service.dto.GoogleProfile;
import com.tomasfriends.bansikee_server.sign.service.dto.KakaoProfile;
import com.tomasfriends.bansikee_server.sign.service.dto.Profile;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import com.tomasfriends.bansikee_server.sign.service.exceptions.NotRegisteredEmailException;
import com.tomasfriends.bansikee_server.sign.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class OauthSignUpService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Autowired
    public OauthSignUpService(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public SignInResponseDto signUpWithKakao(KakaoProfile profile) {
        String nickname = profile.getProperties().getNickname();
        String profileImage = profile.getProperties().getProfile_image();
        String email = profile.getKakao_account().getEmail();

        signUpIfNotUser(nickname, email, profileImage, profile);
        BansikeeUser user = userRepository.findByEmail(email).orElseThrow(NotRegisteredEmailException::new);

        return new SignInResponseDto(jwtProvider.getJWT(user, user.getRoles()), user.getName(), user.getEmail());
    }

    public SignInResponseDto signUpWithGoogle(GoogleProfile profile) {
        String nickname = profile.getGiven_name();
        String profileImage = profile.getPicture();
        String email = profile.getEmail();

        signUpIfNotUser(nickname, email, profileImage, profile);
        BansikeeUser user = userRepository.findByEmail(email).orElseThrow(NotRegisteredEmailException::new);

        return new SignInResponseDto(jwtProvider.getJWT(user, user.getRoles()), user.getName(), user.getEmail());
    }

    private void signUpIfNotUser(String nickname, String email, String profileImage, Profile profile) {
        Optional<BansikeeUser> userByEmail = userRepository.findByEmail(email);

        if (userByEmail.isPresent()) return ;

        userRepository.save(BansikeeUser.builder()
            .name(nickname)
            .email(email)
            .loginMethod(profile.getLoginMethod().name())
            .profileImageURL(profileImage)
            .roles(Collections.singletonList("ROLE_USER"))
            .build());
    }
}