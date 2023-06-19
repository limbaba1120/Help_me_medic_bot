package com.capstonedesign.backend.domain.post;

import com.capstonedesign.backend.domain.comment.Comment;
import com.capstonedesign.backend.domain.user.User;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="post_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @Lob
  private String content;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
  private List<Comment> comments = new ArrayList<>();


  //==연관관계 편의 메소드==//
  public void setUser(User user) {
    this.user = user;
    user.getPosts().add(this);
  }

  //==생성 메소드==//
  public static Post createPost(User user, String content) {
    Post post = new Post();

    post.setUser(user);
    post.setContent(content);
    post.setCreatedAt(LocalDateTime.now());
    post.setUpdatedAt(LocalDateTime.now());

    return post;
  }
}
