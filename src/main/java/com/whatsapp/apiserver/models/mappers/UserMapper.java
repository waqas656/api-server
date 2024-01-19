package com.whatsapp.apiserver.models.mappers;

import com.whatsapp.apiserver.models.dto.UserDto;
import com.whatsapp.apiserver.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto toUserDto(User user);

}
