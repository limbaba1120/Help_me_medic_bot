 package com.capstonedesign.backend.domain.login.service;

import com.capstonedesign.backend.domain.user.User;
import com.capstonedesign.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

  private final UserRepository userRepository;

  public User login(String username, String password) {

    User findUser = userRepository.findByLoginId(username);

    if (findUser == null) {
      throw new NoSuchElementException("사용자가 존재하지 않습니다.");
    } else if (!isCorrectPassword(password, findUser)) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    return findUser;
  }

  private boolean isCorrectPassword(String password, User findUser) {
    return findUser.getPassword().equals(password);
  }
}
