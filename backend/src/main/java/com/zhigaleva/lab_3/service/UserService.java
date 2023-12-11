package com.zhigaleva.lab_3.service;

import com.zhigaleva.lab_3.dto.Authorities;
import com.zhigaleva.lab_3.dto.UserCreateDto;
import com.zhigaleva.lab_3.entity.Authority;
import com.zhigaleva.lab_3.entity.User;
import com.zhigaleva.lab_3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserUsername(String username) throws BadRequestException {
        return userRepository.findByUsername(username)
                .orElseThrow(BadRequestException::new);
    }

    public void createUser(UserCreateDto dto) {
        Authority authority = Authority.builder()
                .authority(Authorities.USER.name())
                .username(dto.getUsername())
                .build();

        User user = User.builder()
                .username(dto.getUsername())
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .authority(authority)
                .enabled(true)
                .build();

        userRepository.save(user);
    }
}
