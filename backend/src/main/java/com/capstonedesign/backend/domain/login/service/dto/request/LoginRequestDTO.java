package com.capstonedesign.backend.domain.login.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

  private String loginId;
  private String password;
}
