package com.capstonedesign.backend.domain.post.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequestDTO {

  private Long userId;
  private String content;
}
