package com.capstonedesign.backend.domain.map.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceSearchResponseByCustom<T> {

  private int count;
  private T results;
}
