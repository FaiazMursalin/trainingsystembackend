package com.example.trainingsystembackend.service;

import com.example.trainingsystembackend.model.User;
import com.example.trainingsystembackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("com.example.trainingsystembackend.repository")
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User role=userRepository.findByName(username);
        if(role==null) {
            throw new UsernameNotFoundException("No user with name: "+username);
        }
        //System.out.println(role);
        return role;

    }
}
