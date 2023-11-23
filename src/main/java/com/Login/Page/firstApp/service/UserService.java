package com.Login.Page.firstApp.service;


import com.Login.Page.firstApp.model.dto.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    List<UserDto> getAllUsers();
}
