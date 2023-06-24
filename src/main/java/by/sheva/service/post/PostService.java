package by.sheva.service.post;

import by.sheva.domain.Post;
import by.sheva.domain.User;

import java.util.List;
import java.util.Optional;

public interface PostService{


    Post findPostById(Integer id);

    Optional<Post> findOnePost(Integer id);

    List<Post> findAllPosts();

    List<Post> findAllPostsWithParams(int limit, int offset);

    List<Post> findMostPopularPosts();

    List<Post> findAllPostsByUser(User user, int limit, int offset);

    boolean getLikeForPost(Post post, User user);

    boolean isLikedPost(Post post, int userId);

    boolean deleteLikeForPost(Post post, User user);

    int getLikesCount(Post post);

    int getUserPostsCount(User user);

    List<Post> findPostsLikedByUser(User user, int limit, int offset);

    Post creatPost(Post post);

    Post updatePost(Post post);

    Integer deletePost(Integer id);

}
