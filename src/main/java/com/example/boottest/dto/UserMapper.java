package com.example.boottest.dto;

import com.example.boottest.dao.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserRegisterDto entityToDto(User entity);

    User dtoToEntity(UserRegisterDto dto);
}
