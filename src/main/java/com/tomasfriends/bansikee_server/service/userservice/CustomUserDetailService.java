package com.tomasfriends.bansikee_server.service.userservice;

import com.tomasfriends.bansikee_server.exceptions.JWTDecodeException;
import com.tomasfriends.bansikee_server.repository.loginrepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return userRepository.findById(Integer.parseInt(id)).orElseThrow(JWTDecodeException::new);
    }
}
