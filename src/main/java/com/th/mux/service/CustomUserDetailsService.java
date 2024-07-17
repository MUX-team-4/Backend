package com.th.mux.service;

import com.th.mux.model.User;
import com.th.mux.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("calling loadUserByUsername email = " + username);
        //log.info("loadUserByUsername users={}", userRepository.findAll().size());
        Optional<User> userOp = userRepository.findAllByEmail(username);
        if (userOp.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = userOp.get();
        log.info("loadUserByUsername role = " + user.getRole().toString());
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true, grantedAuthorities);
    }
}
