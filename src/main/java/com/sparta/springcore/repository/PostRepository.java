package com.sparta.springcore.repository;

import com.sparta.springcore.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// SQL을 대신 해주는 역할
// Post에 관한 Repository
// JpaRepository 기능 포함
// 식별대상 : Post, id의 형태가 Long - <Post, Long>
public interface PostRepository extends JpaRepository<Post, Long> {
    // 수정시간을 기준으로 최신순으로 정렬을 해달라는 뜻 (내림차순)
    List<Post> findAllByOrderByModifiedAtDesc();
    List<Post> findAllByUserId(Long userId);
}
