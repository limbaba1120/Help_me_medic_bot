package com.capstonedesign.backend.controller;

import com.capstonedesign.backend.domain.user.User;
import com.capstonedesign.backend.domain.user.service.UserService;
import com.capstonedesign.backend.domain.user.service.dto.request.JoinUserRequestDTO;
import com.capstonedesign.backend.domain.user.service.dto.request.UpdateUserRequestDTO;
import com.capstonedesign.backend.domain.user.service.dto.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://xn--2z1bo3hjrs.xn--yq5b.xn--3e0b707e/"})
@Api(tags = "회원 관련 API")
public class UserController {

  private final UserService userService;

  /**
   * 회원 가입
   */
  @PostMapping("/users/join")
  @ApiOperation(value = "회원가입 API", notes = "회원 정보를 요청값으로 받아 회원가입 진행")
  public ResponseEntity<Object> joinUser(@RequestBody JoinUserRequestDTO request) {

    duplicateCheck(request.getLoginId());

    User user = User.createUser(request.getLoginId(), request.getPassword(), request.getName(), request.getNickname(), request.getCountry());
    Long userId = userService.join(user);

    return ResponseEntity
        .created(URI.create("/users/" + userId))
        .build();
  }

  /**
   * 회원 조회
   */
  @GetMapping("/users")
  @ApiOperation(value = "전체 회원 목록 조회 API", notes = "회원가입된 모든 회원 목록 조회")
  public ListUserResponse<List<ListUserDetailResponseDTO>> listUser() {

    List<User> userList = userService.findAll();
    List<ListUserDetailResponseDTO> listUserData = userList.stream().map(user -> new ListUserDetailResponseDTO(user.getId(), user.getLoginId(), user.getName(), user.getNickname(), user.getCountry()))
        .collect(Collectors.toList());

    return new ListUserResponse<>(listUserData.size(), listUserData);
  }

  /**
   * 회원 상세 조회
   */
  @GetMapping("/users/{userId}")
  @ApiOperation(value = "회원 상세 조회 API", notes = "특정 회원 상세 정보 조회")
  public ListUserDetailResponseDTO listUserDetail(@PathVariable Long userId) {

    User findUser = userService.findById(userId);

    return new ListUserDetailResponseDTO(findUser.getId(), findUser.getLoginId(), findUser.getName(), findUser.getNickname(), findUser.getCountry());
  }

  /**
   * 회원 수정
   */
  @PatchMapping("/users/{userId}")
  @ApiOperation(value = "회원 수정 API", notes = "요청받은 회원 정보 수정값에 따라 회원 수정")
  public ResponseEntity<Long> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {

    userService.update(userId, updateUserRequestDTO);
    User findUser = userService.findById(userId);

    return ResponseEntity.noContent().build();
  }

  /**
   * 회원 삭제
   */
  @DeleteMapping("/users/{userId}")
  @ApiOperation(value = "회원 삭제 API", notes = "특정 회원의 가입 정보를 삭제")
  public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {

    userService.delete(userId);

    return ResponseEntity.noContent().build();
  }

  /**
   * 아이디 중복 조회
   */
  public void duplicateCheck(String loginId) {

    if (!Objects.isNull(userService.findByLoginId(loginId))) {

      throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
    }
  }
}
