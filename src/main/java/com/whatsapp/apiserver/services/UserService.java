package com.whatsapp.apiserver.services;

import com.whatsapp.apiserver.models.dto.UserDto;
import com.whatsapp.apiserver.models.entities.User;
import com.whatsapp.apiserver.models.mappers.UserMapper;
import com.whatsapp.apiserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto saveUser(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .build();

        User savedUser = userRepository.save(user);

        //converting User entity object to UserDto
        return UserMapper.USER_MAPPER.toUserDto(savedUser);
    }

}
