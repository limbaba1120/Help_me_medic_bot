package com.capstonedesign.backend.controller;


import com.capstonedesign.backend.domain.map.dto.response.PlaceSearchResponseByGoogle;
import com.capstonedesign.backend.domain.user.User;
import com.capstonedesign.backend.domain.user.service.UserService;
import com.capstonedesign.backend.domain.post.Post;
import com.capstonedesign.backend.domain.post.service.PostService;
import com.capstonedesign.backend.domain.post.service.dto.request.CreatePostRequestDTO;
import com.capstonedesign.backend.domain.post.service.dto.request.UpdatePostRequestDTO;
import com.capstonedesign.backend.domain.post.service.dto.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://xn--2z1bo3hjrs.xn--yq5b.xn--3e0b707e/"})
@Api(tags = "게시글 관련 API")
public class PostController {

  private final PostService postService;
  private final UserService userService;

  /**
   * 게시글 작성
   */
  @PostMapping("/posts")
  @ApiOperation(value = "게시글 작성 API", notes = "제목 및 내용을 요청 파라미터로 하여 게시글 작성")
  public ResponseEntity<Long> registerPost(@RequestBody CreatePostRequestDTO createPostRequestDTO) {

    User findUser = userService.findById(createPostRequestDTO.getUserId());
    Post post = Post.createPost(findUser, createPostRequestDTO.getContent());
    Long postId = postService.save(post);

    return ResponseEntity
        .created(URI.create("/posts/" + postId))
        .build();

  }

  /**
   * 게시글 조회
   */
  @GetMapping("/posts")
  @ApiOperation(value = "게시글 목록 조회 API", notes = "전체 게시글 목록 조회")
  public ListPostResponse<List<DetailPostResponseDTO>> listPost() {

    if (Objects.isNull(postService.findAll())) {
      throw new NoResultException("게시글이 존재하지 않습니다.");
    }
    List<Post> posts = postService.findAll();
    List<DetailPostResponseDTO> collect = posts.stream().map(p ->
        new DetailPostResponseDTO(p.getId(), p.getUser().getNickname(), p.getContent(),
            p.getCreatedAt(), p.getUpdatedAt())).collect(Collectors.toList());

    List<DetailPostResponseDTO> sorted = collect.stream()
        .sorted(Comparator.comparing(DetailPostResponseDTO::getId).reversed())
        .collect(Collectors.toList());

    return new ListPostResponse<>(sorted.size(), sorted);
  }

  /**
   * 게시글 상세 조회
   */
  @GetMapping("/posts/{postId}")
  @ApiOperation(value = "특정 게시글 상세 조회 API", notes = "특정 게시물 상세 조회")
  public DetailPostResponseDTO listPostDetail(@PathVariable Long postId) {

    Post findPost = postService.findById(postId);

    return new DetailPostResponseDTO(findPost.getId(), findPost.getUser().getNickname(),
        findPost.getContent(), findPost.getCreatedAt(), findPost.getUpdatedAt());
  }

  /**
   * 게시글 수정
   */
  @PatchMapping("/posts/{postId}")
  @ApiOperation(value = "게시글 수정 API", notes = "요청받은 게시글 정보 수정값에 따라 작성")
  public ResponseEntity<Long> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequestDTO updatePostRequestDTO) {

    postService.update(postId, updatePostRequestDTO);
    Post findPost = postService.findById(postId);

    return ResponseEntity.noContent().build();
  }

  /**
   * 게시글 삭제
   */
  @DeleteMapping("/posts/{postId}")
  @ApiOperation(value = "게시글 삭제 API", notes = "특정 게시글 삭제")
  public ResponseEntity<Object> deletePost(@PathVariable Long postId) {

    postService.delete(postId);

    return ResponseEntity.noContent().build();
  }
}
