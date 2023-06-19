package com.capstonedesign.backend.domain.post.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListPostResponse<T> {

  private int count;
  private T postList;
}
