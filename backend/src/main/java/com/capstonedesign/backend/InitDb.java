package com.capstonedesign.backend;

import com.capstonedesign.backend.domain.comment.Comment;
import com.capstonedesign.backend.domain.post.Post;
import com.capstonedesign.backend.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

  private final InitService initService;

  /**
   * 애플리케이션 실행 전에 미리 init() 실행
   */
  @PostConstruct
  public void init() {
    initService.dbInit();
  }

  @Component
  @Transactional
  @RequiredArgsConstructor
  @Slf4j
  static class InitService {

    private final EntityManager em;

    public void dbInit() {

      /*User user1 = createUser("admin", "admin", "admin", "Admin", "South Korea");
      User user2 = createUser("test1", "test1", "test1", "中村 あげは", "Japan");
      User user3 = createUser("test2", "test2", "test2", "조병찬", "South Korea");
      em.persist(user1);
      em.persist(user2);
      em.persist(user3);

      Post post1 = createPost(user1, "안녕하세요? 포럼 테스트용 게시물입니다. 포럼은 챗봇으로 해결되지 않는 문제를 집단지성으로 해결할 수 있는 공간입니다.");
      Post post2 = createPost(user2, "頭がとても痛いのですが、どこに行けばいいですか?");
      em.persist(post1);
      em.persist(post2);

      Comment comment1 = createComment(user2, post1, "こんにちは");
      Comment comment2 = createComment(user3, post1, "반갑습니다.");
      em.persist(comment1);
      em.persist(comment2);*/

//      em.flush();
    }

    private User createUser(String loginId, String password, String name, String nickname, String country) {

      return User.createUser(loginId, password, name, nickname, country);
    }

    private Post createPost(User user, String content) {

      return Post.createPost(user, content);
    }

    private Comment createComment(User user, Post post, String content) {

      return Comment.createComment(user, post, content);
    }

  }
}
