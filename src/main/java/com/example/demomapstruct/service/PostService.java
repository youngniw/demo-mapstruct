package com.example.demomapstruct.service;

import com.example.demomapstruct.dto.PostDto;
import com.example.demomapstruct.dto.SavePostDto;
import com.example.demomapstruct.dto.UpdatePostDto;
import com.example.demomapstruct.entity.Post;
import com.example.demomapstruct.mapper.PostMapper;
import com.example.demomapstruct.mapper.UserMapper;
import com.example.demomapstruct.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public Optional<PostDto> getPost(final Long pid) {
        return postRepository.findById(pid)
                .map(postMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<PostDto> getPostList(final Pageable pageable) {
        Page<Post> postList = postRepository.findAll(pageable);

        return postMapper.toDto(postList.getContent());
    }

    @Transactional
    public Post savePost(SavePostDto postDto) {
        Post post = postMapper.toEntity(postDto, userMapper.fromIdx(postDto.getUid()));

        return postRepository.save(post);
    }

    @Transactional
    public void updatePost(Long pid, UpdatePostDto postDto) {
        Post post = postRepository.findById(pid)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글 입니다."));

        postMapper.partialUpdate(post, postDto);    // 제목 및 내용 변경

        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long pid) {
        postRepository.deleteById(pid);
    }
}
