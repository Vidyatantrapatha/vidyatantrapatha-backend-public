package me.electronicsboy.vidyatantrapatha.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import me.electronicsboy.vidyatantrapatha.data.PrivilegeLevel;
import me.electronicsboy.vidyatantrapatha.dtos.rest.LoginUserDto;
import me.electronicsboy.vidyatantrapatha.dtos.rest.RegisterUserDto;
import me.electronicsboy.vidyatantrapatha.models.User;
import me.electronicsboy.vidyatantrapatha.repositories.UserRepository;

@Service
public class UserAuthenticationService {
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public UserAuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user = new User()
                .setFullname(input.getFullname())
                .setEmail(input.getEmail())
                .setUsername(input.getUsername())
                .setPrivilageLevel(PrivilegeLevel.GUEST)
                .setEnabled(false)
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setLocation(input.getLocation());

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        User user = userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    	authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        input.getPassword()
                )
        );

        return user;
    }
    
    public String getEncodedPassword(String plaintest) {
    	return passwordEncoder.encode(plaintest);
    }
}
