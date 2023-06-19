package com.capstonedesign.backend.domain.user.service.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JoinUserRequestDTO {

  private String loginId;

  private String password;

  private String name;

  private String nickname;

  private String country;
}
