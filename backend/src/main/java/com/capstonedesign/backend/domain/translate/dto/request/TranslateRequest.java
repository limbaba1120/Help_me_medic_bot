package com.capstonedesign.backend.domain.translate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TranslateRequest {

  private String source;

  private String target;

  private String text;
}
