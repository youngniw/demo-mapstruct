package com.example.demomapstruct.mapper;

import com.example.demomapstruct.dto.UserDataDto;
import com.example.demomapstruct.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserDataMapper extends EntityMapper<UserDataDto, User> {
    User toEntity(UserDataDto userDataDto);

    @Mapping(target = "isCreatedToday", expression = "java(user.getCreatedDate().isAfter(java.time.LocalDateTime.of(java.time.LocalDate.now(), java.time.LocalTime.of(0, 0, 0))))")
    UserDataDto toDto(User user);

    @Named("partialUpdate")
    @Mapping(target = "uid", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget User entity, UserDataDto userDataDto);

    default User fromIdx(Long idx) {
        if (idx == null) {
            return null;
        }
        return User.builder()
                .uid(idx)
                .build();
    }
}
