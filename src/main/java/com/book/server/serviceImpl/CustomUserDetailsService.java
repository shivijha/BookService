package com.book.server.serviceImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.book.server.entity.User;
import com.book.server.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the database using the email (username)
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        
        return user; // Return the User object, which implements UserDetails
    }
}
