package com.github.vickyrai01.salesmanagement.service.auth;

import com.github.vickyrai01.salesmanagement.model.auth.User;
import com.github.vickyrai01.salesmanagement.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();

        user.getRoles().forEach(role -> authoritiesList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRolName().name()))));


        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.getIsEnabled(),
                user.getAccountNoExpired(),
                user.getCredentialsNoExpired(),
                user.getAccountNoLocked(),
                authoritiesList
        );
    }
}
