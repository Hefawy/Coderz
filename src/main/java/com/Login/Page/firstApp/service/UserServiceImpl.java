package com.Login.Page.firstApp.service;


import com.Login.Page.firstApp.model.dto.UserDto;
import com.Login.Page.firstApp.model.entity.UserApp;
import com.Login.Page.firstApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        // For simplicity, you can directly use UserRepository methods here
        List<UserApp> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(userEntity -> new UserDto(userEntity.getEmail(), userEntity.getPassword()))
                .collect(Collectors.toList());
    }

    @Override
    public void saveUser(UserDto userDto) {
        // Map UserDto to UserEntity and save it
        UserApp userEntity = new UserApp();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userRepository.save(userEntity);
    }
}
