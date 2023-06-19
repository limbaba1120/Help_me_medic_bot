package com.capstonedesign.backend.domain.post.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetailPostResponseDTO {

  private Long id;
  private String author;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
