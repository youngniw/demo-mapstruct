package com.example.demomapstruct.mapper;

import com.example.demomapstruct.dto.UserDto;
import com.example.demomapstruct.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    @Named("partialUpdate")
    @Mapping(target = "uid", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget User entity, UserDto dto);

    default User fromIdx(Long idx) {
        if (idx == null) {
            return null;
        }
        return User.builder()
                .uid(idx)
                .build();
    }
}
