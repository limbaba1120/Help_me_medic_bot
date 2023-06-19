package com.capstonedesign.backend.domain.post.repository;

import com.capstonedesign.backend.domain.post.Post;

import java.util.List;

public interface PostRepository {

  void save(Post post);

  Post findById(Long id);

  List<Post> findByUserId(Long memberId);

  List<Post> findAll();

  void delete(Long id);
}
