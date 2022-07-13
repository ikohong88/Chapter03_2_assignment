package com.sparta.springcore.controller;

import com.sparta.springcore.dto.PostMypostRequestDto;
import com.sparta.springcore.dto.PostPasswordDto;
import com.sparta.springcore.dto.PostRequestDto;
import com.sparta.springcore.model.Post;
import com.sparta.springcore.model.UserRoleEnum;
import com.sparta.springcore.repository.PostRepository;
import com.sparta.springcore.security.UserDetailsImpl;
import com.sparta.springcore.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController
public class PostRestController {
    private final PostService postService;
    private final PostRepository postRepository;

    // 게시글 작성 API (생성)
    //    - 제목, 작성자명, 비밀번호, 작성 내용을 입력하기
    // service까지 갈 필요없이 바로 생성가능
    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        Post post = new Post(requestDto, userId);
        return postRepository.save(post);
    }

    // 게시글 조회 API
    //    - 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기
    //    (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
    @GetMapping("/api/posts")
    public List<Post> getPost(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    // 로그인한 회원이 등록한 게시판 조회
    @GetMapping("/api/posts/user-posts")
    public List<Post> getPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 회원 테이블의 ID
        Long userId = userDetails.getUser().getId();

        return postService.getProducts(userId);
    }


    // 게시글 수정 API
    //    - API를 호출할 때 입력된 비밀번호를 비교하여 동일할 때만 글이 수정되게 하기
    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostMypostRequestDto requestDto) {
        return postService.update(id,requestDto);
    }

    // 게시글 삭제 API
    //    - API를 호출할 때 입력된 비밀번호를 비교하여 동일할 때만 글이 삭제되게 하기
    // service까지 갈 필요없이 JPA를 사용하여 바로 삭제 가능
    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }

    // 비밀번호 확인
    @PostMapping("/api/checkpwds/{id}")
    public int checkPassword(@PathVariable Long id, @RequestBody PostPasswordDto requestDto) {
        Optional<Post> post = postRepository.findById(id);
        if (post.get().getPassword().equals(requestDto.getPassword())) {
            return 1;
        } else {
            return 2;
        }
    }

    // (관리자용) 등록된 모든 상품 목록 조회
    @Secured(value = UserRoleEnum.Authority.ADMIN)
    @GetMapping("/api/admin/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }
}
