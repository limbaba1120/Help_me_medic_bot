package com.capstonedesign.backend.domain.post.service;

import com.capstonedesign.backend.domain.post.Post;
import com.capstonedesign.backend.domain.post.repository.PostRepository;
import com.capstonedesign.backend.domain.post.service.dto.request.UpdatePostRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  @Transactional
  public Long save(Post item) {
    postRepository.save(item);

    return item.getId();
  }

  public Post findById(Long id) {
    return postRepository.findById(id);
  }

  public List<Post> findByUserId(Long userId) {
    return postRepository.findByUserId(userId);
  }

  public List<Post> findAll() {
    return postRepository.findAll();
  }

  @Transactional
  public void update(Long id, UpdatePostRequestDTO updatePostRequestDTO) {
    Post findPost = findById(id);

    if (findPost!= null) {
      findPost.setContent(updatePostRequestDTO.getContent());
      findPost.setUpdatedAt(LocalDateTime.now());
    }
  }

  @Transactional
  public void delete(Long id) {
    postRepository.delete(id);
  }


}
