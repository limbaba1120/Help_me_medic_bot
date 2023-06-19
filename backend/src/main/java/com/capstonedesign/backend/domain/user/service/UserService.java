package com.capstonedesign.backend.domain.user.service;

import com.capstonedesign.backend.domain.user.User;
import com.capstonedesign.backend.domain.user.repository.UserRepository;
import com.capstonedesign.backend.domain.user.service.dto.request.UpdateUserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public Long join(User user) {
    userRepository.save(user);

    return user.getId();
  }


  public User findById(Long id) {

    return userRepository.findById(id);
  }

  public User findByLoginId(String loginId) {

      return userRepository.findByLoginId(loginId);
  }

  public User findByName(String name) {

    return userRepository.findByName(name);
  }

  public User findByNickname(String nickname) {

    return userRepository.findByNickname(nickname);
  }

  public List<User> findAll() {

    return userRepository.findAll();
  }

  @Transactional
  public void update(Long id, UpdateUserRequestDTO updateUserRequestDTO) {
    User findUser = userRepository.findById(id);

    if (findUser != null) {
      findUser.setName(updateUserRequestDTO.getName());
      findUser.setNickname(updateUserRequestDTO.getNickname());
      findUser.setCountry(updateUserRequestDTO.getCountry());
    }
  }

  @Transactional
  public void delete(Long id) {
    userRepository.delete(id);
  }

}
