package by.sheva.service.post;

import by.sheva.domain.Post;
import by.sheva.domain.User;
import by.sheva.repository.post.PostRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class PostServiceImpl implements PostService{

    private static PostServiceImpl instance;

    private final PostRepositoryImpl repository = PostRepositoryImpl.getInstance();

    public PostServiceImpl() {
    }

    public PostServiceImpl getInstance() {

        if (instance == null) {
            instance = new PostServiceImpl();
        }
        return instance;
    }

    @Override
    public Post findPostById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Post> findOnePost(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<Post> findAllPosts() {
        return repository.findAll();
    }

    @Override
    public List<Post> findAllPostsWithParams(int limit, int offset) {
        return repository.findAll(limit, offset);
    }

    @Override
    public List<Post> findMostPopularPosts() {
        return repository.findMostPopularPosts();
    }

    @Override
    public List<Post> findAllPostsByUser(User user) {
        return repository.findPostsByUser(user);
    }

    @Override
    public boolean getLikeForPost(Post post, User user) {
        return repository.getLike(post, user);
    }

    @Override
    public boolean isLikedPost(Post post, User user) {
        return repository.isLikedPost(post, user);
    }

    @Override
    public boolean deleteLikeForPost(Post post, User user) {
        return repository.deleteLike(post, user);
    }

    @Override
    public int getLikesCount(Post post) {
        return repository.getLikeCount(post);
    }

    @Override
    public int getUserPostsCount(User user) {
        return repository.getUserPostsCount(user);
    }

    @Override
    public Post creatPost(Post post) {
        return repository.creatObject(post);
    }

    @Override
    public Post updatePost(Post post) {
        return repository.updateObject(post);
    }

    @Override
    public Integer deletePost(Integer id) {
        return repository.deleteObject(id);
    }
}
