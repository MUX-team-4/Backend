package com.th.mux.service;

import com.th.mux.dto.UserDto;
import com.th.mux.mapper.UserMapper;
import com.th.mux.model.User;
import com.th.mux.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto isUserExist(UserDto userDto) {
        Optional<User> user = userRepository.findAllByEmail(userDto.getEmail());
        return user.map(UserMapper::toDto).orElse(null);
    }
}
