package by.sheva.service.comment;

import by.sheva.domain.Comment;
import by.sheva.domain.Post;
import by.sheva.repository.comment.CommentRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService{

    private static CommentServiceImpl instance;

    private final CommentRepositoryImpl repository = CommentRepositoryImpl.getInstance();

    public CommentServiceImpl() {
    }

    public static CommentServiceImpl getInstance() {
        if (instance == null) {
            instance = new CommentServiceImpl();
        }
        return instance;
    }

    @Override
    public Comment findCommentById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Comment> findOneComment(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<Comment> findAllComments() {
        return repository.findAll();
    }

    @Override
    public List<Comment> findAllCommentsWithParams(int limit, int offset) {
        return repository.findAll(limit, offset);
    }

    @Override
    public List<Comment> findAllCommentsByPost(Post post, int limit, int offset) {
        return repository.findAllCommentsByPost(post, limit, offset);
    }

    @Override
    public boolean deleteAllCommentsForPost(Post post) {
        return repository.deleteAllCommentsByPost(post);
    }

    @Override
    public int getCommentCountByPost(Post post) {
        return repository.getCommentCountByPost(post);
    }

    @Override
    public Comment creatComment(Comment comment) {
        return repository.creatObject(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return repository.updateObject(comment);
    }

    @Override
    public Integer deleteComment(Integer id) {
        return repository.deleteObject(id);
    }
}
