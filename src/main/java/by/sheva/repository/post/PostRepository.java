package by.sheva.repository.post;

import by.sheva.domain.Post;
import by.sheva.domain.User;
import by.sheva.repository.CRUDRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CRUDRepository<Integer, Post> {
    @Override
    Post findById(Integer id);

    @Override
    Optional<Post> findOne(Integer id);

    @Override
    List<Post> findAll();

    @Override
    List<Post> findAll(int limit, int offset);

    List<Post> findMostPopularPosts();

    List<Post> findPostsByUser(User user, int limit, int offset);

    List<Post> findPostsLikedByUser(User user, int limit, int offset);

    boolean getLike(Post post, User user);

    boolean isLikedPost(Post post, User user);

    boolean deleteLike(Post post, User user);

    int getLikeCount(Post post);

    int getUserPostsCount(User user);

    @Override
    Post creatObject(Post object);

    @Override
    Post updateObject(Post object);

    @Override
    Integer deleteObject(Integer id);
}
