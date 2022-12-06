package com.example.demomapstruct.mapper;

import com.example.demomapstruct.dto.UserDto;
import com.example.demomapstruct.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "loginId", ignore = true)
    @Mapping(target = "loginPw", ignore = true)
    User toEntity(UserDto userDto);

    @Mapping(target = "isCreatedToday", expression = "java(user.getCreatedDate().toLocalDate().isEqual(java.time.LocalDate.now()))")
    UserDto toDto(User user);

    @Named("partialUpdate")
    @Mapping(target = "uid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
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
