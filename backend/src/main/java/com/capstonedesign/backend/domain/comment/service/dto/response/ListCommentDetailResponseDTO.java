package com.capstonedesign.backend.domain.comment.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListCommentDetailResponseDTO {

  private Long id;

  private Long postId;

  private String author;

  private String content;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
