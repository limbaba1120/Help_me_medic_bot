package com.capstonedesign.backend.domain.comment;

import com.capstonedesign.backend.domain.user.User;
import com.capstonedesign.backend.domain.post.Post;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="comment_id")
  private Long id;

  @Lob
  private String content;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  private Post post;

  //==연관관계 편의 메소드==//
  public void setPost(Post post) {
    this.post = post;
    post.getComments().add(this);
  }

  public void setUser(User user) {
    this.user = user;
    user.getComments().add(this);
  }

  //==생성 메소드==//
  public static Comment createComment(User user, Post post, String content) {

    Comment comment = new Comment();

    comment.setUser(user);
    comment.setPost(post);
    comment.setContent(content);
    comment.setCreatedAt(LocalDateTime.now());
    comment.setUpdatedAt(LocalDateTime.now());

    return comment;
  }
}
