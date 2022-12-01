package com.example.demomapstruct.mapper;

import com.example.demomapstruct.dto.SaveUserDto;
import com.example.demomapstruct.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SignUpUserMapper extends EntityMapper<SaveUserDto, User> {
    @Mapping(target = "uid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    User toEntity(SaveUserDto saveUserDto);

    SaveUserDto toDto(User user);

    @Named("partialUpdate")
    @Mapping(target = "uid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget User entity, SaveUserDto dto);

    default User fromIdx(Long idx) {
        if (idx == null) {
            return null;
        }
        return User.builder()

                .uid(idx)
                .build();
    }
}
