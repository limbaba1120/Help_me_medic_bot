package com.capstonedesign.backend.controller;

import com.capstonedesign.backend.config.SessionConstConfig;
import com.capstonedesign.backend.domain.login.service.LoginService;
import com.capstonedesign.backend.domain.login.service.dto.request.LoginRequestDTO;
import com.capstonedesign.backend.domain.login.service.dto.response.DetailSessionResponseDTO;
import com.capstonedesign.backend.domain.login.service.dto.response.LoginResponseDTO;
import com.capstonedesign.backend.domain.login.service.dto.response.LogoutResponseDTO;
import com.capstonedesign.backend.domain.user.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.capstonedesign.backend.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://xn--2z1bo3hjrs.xn--yq5b.xn--3e0b707e/"})
@Slf4j
@Api(tags = "로그인 관련 API")
public class LoginController {

  private final LoginService loginService;

  @PostMapping("/login")
  @ApiOperation(value = "로그인 API", notes = "아이디 및 비밀번호를 요청 파라미터로 하여 로그인")
  public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletRequest request) {

    User loginUser = loginService.login(loginRequestDTO.getLoginId(), loginRequestDTO.getPassword());
    log.info("login: {}", loginUser);

    HttpSession session = request.getSession();
    session.setAttribute(SessionConstConfig.SESSION_KEY, loginUser);

    return new LoginResponseDTO(loginUser.getId(), loginUser.getLoginId(),
        loginUser.getPassword(), loginUser.getName(), loginUser.getNickname(), loginUser.getCountry());
  }

  @PostMapping("/logout")
  @ApiOperation(value = "로그아웃 API", notes = "사용자 로그아웃 (세션 만료)")
  public LogoutResponseDTO logout(HttpServletRequest request) {

    HttpSession session = request.getSession(false);
    User user = (User) session.getAttribute(SessionConstConfig.SESSION_KEY);

    if (session != null) {
      session.invalidate();
    }

    log.info(user.getNickname() + "이(가) 로그아웃되었습니다.");

    return new LogoutResponseDTO(true, "로그아웃");
  }

  @GetMapping("/session")
  @ApiOperation(value = "사용자 세션 체크 API", notes = "사용자가 세션 보유여부 확인 및 세션정보 상세 조회 ")
  public DetailSessionResponseDTO detailSession(HttpServletRequest request) {

    HttpSession session = request.getSession(false);

    if (!Objects.isNull(session) && !Objects.isNull(session.getAttribute(SessionConstConfig.SESSION_KEY))) {
      User loginUser = (User) session.getAttribute(SessionConstConfig.SESSION_KEY);
      return new DetailSessionResponseDTO(loginUser.getId(), loginUser.getLoginId(),
          loginUser.getName(), loginUser.getNickname(), loginUser.getCountry());
    } else {
      throw new NoSuchElementException("세션이 존재하지 않습니다.");
    }
  }
}
