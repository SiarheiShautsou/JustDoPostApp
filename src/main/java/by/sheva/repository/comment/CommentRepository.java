package by.sheva.repository.comment;

import by.sheva.domain.Comment;
import by.sheva.domain.Post;
import by.sheva.repository.CRUDRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CRUDRepository<Integer, Comment> {
    @Override
    Comment findById(Integer id);

    @Override
    Optional<Comment> findOne(Integer id);
    @Override
    List<Comment> findAll();

    @Override
    List<Comment> findAll(int limit, int offset);

    List<Comment> findAllCommentsByPost(Post post);

    boolean deleteAllCommentsByPost(Post post);

    int getCommentCountByPost(Post post);

    @Override
    Comment creatObject(Comment object);

    @Override
    Comment updateObject(Comment object);

    @Override
    Integer deleteObject(Integer id);
}
