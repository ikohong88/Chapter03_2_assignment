package com.sparta.springcore.service;

import com.sparta.springcore.dto.PostMypostRequestDto;
import com.sparta.springcore.model.Post;
import com.sparta.springcore.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@Service  // 서비스임을 선언합니다.
public class PostService {
    private final PostRepository postRepository;

    // 생성자를 통해, Service 클래스를 만들 때 꼭 Repository를 넣어주도록
    // 스프링에게 알려줌 --> @RequiredArgsConstructor가 대신 역할을 해줌
//    public PostService(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }

    // 회원 ID 로 등록된 게시판 조회
    public List<Post> getProducts(Long userId) {
        return postRepository.findAllByUserId(userId);
    }

    // Controll에서 요청이 들어오고 Repo까지 전달이 필요한건 Service에서 코드 작성
    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다. - 게시물 수정시 사용
    public Long update(Long id, PostMypostRequestDto postMypostRequestDto) {
        // 오류가 발생했을때 어떻게 할지 확인 - 게시판이 삭제되어 없을경우 대비
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 게시물이 존재하지 않습니다.")
        );
        post.updateByPost(postMypostRequestDto);
        return id;
    }
}
