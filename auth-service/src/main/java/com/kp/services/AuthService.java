package com.kp.services;


import com.kp.dto.AuthResponseDto;
import com.kp.dto.LoginRequestDto;
import com.kp.dto.RegisterDto;
import com.kp.dto.UserProfileDto;
import com.kp.entity.User;
import com.kp.enums.Roles;
import com.kp.exceptions.LoginFailedException;
import com.kp.exceptions.ResourceNotFoundException;
import com.kp.exceptions.UserAlreadyExistException;
import com.kp.repository.UserRepository;
import com.netflix.discovery.converters.Auto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtService jwtService;

    public AuthResponseDto registerUser(RegisterDto registerDto){

        String email = registerDto.getEmail();

        boolean isAlreadyUser = userRepository.existsByEmail(email);

        if(isAlreadyUser){
//            TODO: throw useralreadyexistexception
            throw new UserAlreadyExistException("user already exist with this email");

        }
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword()); // todo: encrypt the passoword


        user.setRoles(Roles.USER);

        User savedUser = userRepository.save(user);

        String token = jwtService.createToken(savedUser);
        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setToken(token);

        return authResponseDto;

    }

    public AuthResponseDto loginUser(LoginRequestDto loginRequestDto){

        User user = userRepository.findByEmailAndPassword(loginRequestDto.getEmail(),loginRequestDto.getPassword());

        if(user == null){
            throw new LoginFailedException("either email or password is incorrect...");
        }

        String token = jwtService.createToken(user);

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setToken(token);

        return authResponseDto;
    }


    public UserProfileDto getProfile(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with this id"));

        return modelMapper.map(user,UserProfileDto.class);

    }
}
