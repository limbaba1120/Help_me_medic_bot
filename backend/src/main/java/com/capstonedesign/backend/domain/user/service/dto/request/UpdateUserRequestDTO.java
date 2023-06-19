package com.capstonedesign.backend.domain.user.service.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDTO {

  private String name;

  private String nickname;

  private String country;
}
