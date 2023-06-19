package com.capstonedesign.backend.domain.user.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListUserResponse<T> {

  private int count;
  private T data;
}
