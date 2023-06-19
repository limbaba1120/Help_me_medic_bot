package com.capstonedesign.backend.domain.post.repository;

import com.capstonedesign.backend.domain.post.Post;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

  private final EntityManager em;

  @Override
  public void save(Post post) {
    em.persist(post);
  }

  @Override
  public Post findById(Long id) {

    return em.find(Post.class, id);
  }

  @Override
  public List<Post> findByUserId(Long userId) {

    return em.createQuery("select distinct p from Post p" +
            " join fetch p.user u where u.id = :userId", Post.class)
        .setParameter("userId", userId)
        .getResultList();
  }

  @Override
  public List<Post> findAll() {

    try {
      return em.createQuery("SELECT p FROM Post p", Post.class)
          .getResultList();
    } catch (NoResultException e){
      return null;
    }

  }


  @Override
  public void delete(Long id) {

    Post findPost = em.find(Post.class, id);

    em.remove(findPost);
  }
}
