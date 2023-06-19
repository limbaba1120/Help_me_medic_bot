package com.capstonedesign.backend.domain.user;

import com.capstonedesign.backend.domain.comment.Comment;
import com.capstonedesign.backend.domain.post.Post;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(unique = true)
  private String loginId;

  private String password;

  private String name;

  private String nickname;

  private String country;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Post> posts = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Comment> comments = new ArrayList<>();

  //==생성 메소드==//
  public static User createUser(String loginId, String password, String name, String nickname, String country) {

    User user = new User();
    user.setLoginId(loginId);
    user.setPassword(password);
    user.setName(name);
    user.setNickname(nickname);
    user.setCountry(country);

    return user;
  }
}
