package com.capstonedesign.backend.domain.comment.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListCommentResponse<T> {

  private int count;
  private T data;
}
