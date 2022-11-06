package com.example.demo.Service;

import com.example.demo.DTO.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService extends UserDetailsService {

    UserDto saveUser(UserDto userDto);

    UserDto getUser(Long id);

    UserDto updateUser(UserDto userDto);

    void deleteUser(Long id);
}
