package by.sheva.service.comment;

import by.sheva.domain.Comment;
import by.sheva.domain.Post;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment findCommentById(Integer id);

    Optional<Comment> findOneComment(Integer id);

    List<Comment> findAllComments();

    List<Comment> findAllCommentsWithParams(int limit, int offset);

    List<Comment> findAllCommentsByPost(Post post);

    boolean deleteAllCommentsForPost(Post post);

    int getCommentCountByPost(Post post);

    Comment creatComment(Comment comment);

    Comment updateComment(Comment comment);

    Integer deleteComment(Integer id);
}
