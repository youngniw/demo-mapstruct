package com.example.demomapstruct.controller;

import com.example.demomapstruct.dto.PostDto;
import com.example.demomapstruct.dto.SavePostDto;
import com.example.demomapstruct.dto.UpdatePostDto;
import com.example.demomapstruct.entity.Post;
import com.example.demomapstruct.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    // pid 에 해당하는 게시글 조회
    @GetMapping("/{pid}")
    public ResponseEntity<PostDto> getPost(@PathVariable("pid") Long pid) {
        Optional<PostDto> post = postService.getPost(pid);

        return post
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));

    }

    // 게시글 목록 조회 (페이지 사이즈 설정)
    @GetMapping("/list")
    public ResponseEntity<List<PostDto>> getPostList(@RequestParam("page") int page) {
        // 페이지 번호는 0부터 시작
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("createdDate").descending());

        List<PostDto> postList = postService.getPostList(pageRequest);

        return ResponseEntity.ok(postList);
    }

    // 게시글 추가
    @PostMapping
    public ResponseEntity<Object> savePost(@RequestBody SavePostDto savePostDto) {
        Post post = postService.savePost(savePostDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{pid}")
                .buildAndExpand(post.getPid())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    // 게시글 수정
    @PutMapping("/{pid}")
    public ResponseEntity<PostDto> getPost(@PathVariable("pid") Long pid, @RequestBody UpdatePostDto updatePostDto) {
        postService.updatePost(pid, updatePostDto);

        return ResponseEntity.ok(null);
    }

    // 게시글 삭제
    @DeleteMapping("/{pid}")
    public ResponseEntity<Object> deletePost(@PathVariable("pid") Long pid) {
        postService.deletePost(pid);

        return ResponseEntity.ok(null);
    }
}
