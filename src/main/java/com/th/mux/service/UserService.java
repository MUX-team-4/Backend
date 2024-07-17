package com.th.mux.service;

import com.th.mux.dto.UserDto;
import com.th.mux.mapper.UserMapper;
import com.th.mux.model.User;
import com.th.mux.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional(isolation = Isolation.SERIALIZABLE)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto isUserExist(UserDto userDto) {
        Optional<User> user = userRepository.findAllByEmail(userDto.getEmail());
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        // create password
        String password = "run4recht-" + user.get().getId() + "-" + user.get().getDepartment().getId();
        // encode password
        String encodedPassword = passwordEncoder.encode(password);
        log.info("isUserExist password={}, encodedPassword={}", password, encodedPassword);
        // save encoded password to database
        user.get().setPassword(encodedPassword);
        userRepository.save(user.get());
        return user.map(user1 -> {
            UserDto userDto1 = UserMapper.toDto(user1);
            // send raw password to client for basic authentication "user:password"
            userDto1.setPassword(password);
            return userDto1;
        }).orElse(null);
    }
}
