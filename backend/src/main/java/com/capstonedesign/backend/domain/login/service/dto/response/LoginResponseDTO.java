package com.capstonedesign.backend.domain.login.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

  private Long id;

  private String loginId;

  private String password;

  private String name;

  private String nickname;

  private String country;
}
