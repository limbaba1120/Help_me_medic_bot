package com.capstonedesign.backend.controller;

import com.capstonedesign.backend.domain.comment.Comment;
import com.capstonedesign.backend.domain.comment.service.CommentService;
import com.capstonedesign.backend.domain.comment.service.dto.request.CreateCommentRequestDTO;
import com.capstonedesign.backend.domain.comment.service.dto.request.UpdateCommentRequestDTO;
import com.capstonedesign.backend.domain.comment.service.dto.response.ListCommentDetailResponseDTO;
import com.capstonedesign.backend.domain.comment.service.dto.response.ListCommentResponse;
import com.capstonedesign.backend.domain.post.Post;
import com.capstonedesign.backend.domain.post.service.PostService;
import com.capstonedesign.backend.domain.user.User;
import com.capstonedesign.backend.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://xn--2z1bo3hjrs.xn--yq5b.xn--3e0b707e/"})
@Api(tags = "댓글 관련 API")
public class CommentController {

  private final UserService userService;
  private final PostService postService;
  private final CommentService commentService;

  @PostMapping("/posts/{postId}/comments")
  @ApiOperation(value = "댓글 등록 API", notes = "해당 게시물에 댓글 등록")
  public ResponseEntity<Long> createComment(@PathVariable Long postId, @RequestBody CreateCommentRequestDTO request) {

    Post post = postService.findById(postId);
    User user = userService.findById(request.getUserId());
    Comment comment = Comment.createComment(user, post, request.getContent());
    Long commentId = commentService.save(comment);

    return ResponseEntity
        .created(URI.create("/posts/" + postId + "/comments/" + commentId))
        .build();
  }

  @GetMapping("/posts/{postId}/comments")
  @ApiOperation(value = "전체 댓글 조회 API", notes = "해당 게시글의 전체 댓글 조회")
  public ListCommentResponse<List<ListCommentDetailResponseDTO>> listComment(@PathVariable Long postId) {

    Post post = postService.findById(postId);
    List<ListCommentDetailResponseDTO> listCommentData = post.getComments().stream()
        .map(comment -> new ListCommentDetailResponseDTO(comment.getId(), post.getId(), comment.getUser().getNickname(),
            comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt())).collect(Collectors.toList());

    return new ListCommentResponse<>(listCommentData.size(), listCommentData);
  }

  @GetMapping("/posts/{postId}/comments/{commentId}")
  @ApiOperation(value = "댓글 상세 조회 API", notes = "댓글 상세 정보 조회")
  public ListCommentDetailResponseDTO listDetailComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {

    Comment comment = commentService.findById(commentId);
    return new ListCommentDetailResponseDTO(comment.getId(), comment.getPost().getId(), comment.getUser().getNickname(),
        comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt());
  }

  @PatchMapping("/posts/{postId}/comments/{commentId}")
  @ApiOperation(value = "댓글 수정 API", notes = "댓글 본문을 수정")
  public ResponseEntity<Long> updateComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @RequestBody UpdateCommentRequestDTO request) {

    commentService.updateComment(commentId, request.getContent());

    return ResponseEntity.noContent().build();
  }
  
  @DeleteMapping("/posts/{postId}/comments/{commentId}")
  @ApiOperation(value = "댓글 삭제 API", notes = "댓글 삭제")
  public ResponseEntity<Object> deleteComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {

    commentService.delete(commentId);

    return ResponseEntity.noContent().build();
  }
}
