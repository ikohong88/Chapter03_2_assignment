package com.sparta.springcore.model;

import com.sparta.springcore.dto.PostMypostRequestDto;
import com.sparta.springcore.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 생성
@Entity  // DB 테이블 역할
// 초기 데이터베이스 1개 생성필요 - 하지 않을시 에러 발생 : application.properties 에서 작성한 DB이름으로 생성필요
//@Table(name = "posts") -- 이걸 작성하면 posts와 post 테이블 2개가 생성됨
public class Post extends Timestamped {
    // 제목, 작성자명, 작성 날짜 (Timestamped), 비밀번호 - JWT?, 게시글 내용

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String post;

    // 게시물 생성시 이용
    public Post(PostRequestDto requestDto, Long userId) {
        this.userId = userId;
        this.title = requestDto.getTitle();
        this.name = requestDto.getTitle();
        this.password = requestDto.getPassword();
        this.post = requestDto.getPost();
    }

    // 게시판 수정시 이용
    public void updateByPost(PostMypostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.post = requestDto.getPost();
    }
}

