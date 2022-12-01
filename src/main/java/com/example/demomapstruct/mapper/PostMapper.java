package com.example.demomapstruct.mapper;

import com.example.demomapstruct.dto.PostDto;
import com.example.demomapstruct.dto.SavePostDto;
import com.example.demomapstruct.dto.UpdatePostDto;
import com.example.demomapstruct.entity.Post;
import com.example.demomapstruct.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper extends EntityMapper<PostDto, Post> {

    @Mapping(target = "updatedDate", ignore = true)
    Post toEntity(PostDto postDto);
    @Mapping(source = "userEntity", target = "writer")
    Post toEntity(SavePostDto postDto, User userEntity);


    @Mapping(target = "writer.createdDate", ignore = true)
    @Mapping(target = "writer.updatedDate", ignore = true)
    PostDto toDto(Post post);

    @Named("partialUpdate")
    @Mapping(target = "pid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Post entity, PostDto dto);

    @Named("partialUpdate")
    @Mapping(target = "pid", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Post entity, UpdatePostDto dto);

    default Post fromIdx(Long idx) {
        if (idx == null) {
            return null;
        }
        return Post.builder()
                .pid(idx)
                .build();
    }
}
