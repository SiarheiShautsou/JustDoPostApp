package by.sheva.repository.comment;

import by.sheva.configuration.PostgresDBConnection;
import by.sheva.domain.Comment;
import by.sheva.domain.Post;
import by.sheva.domain.User;
import by.sheva.repository.post.PostRepositoryImpl;
import by.sheva.repository.user.UserRepositoryImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.sheva.repository.comment.CommentTableColumns.COMMENT_ID;
import static by.sheva.repository.comment.CommentTableColumns.CREATED_AT;
import static by.sheva.repository.comment.CommentTableColumns.MESSAGE;
import static by.sheva.repository.comment.CommentTableColumns.POST_ID;
import static by.sheva.repository.comment.CommentTableColumns.USER_ID;

public class CommentRepositoryImpl implements CommentRepository {

    private static CommentRepositoryImpl instance;

    private final PostgresDBConnection connection = PostgresDBConnection.getInstance();

    public CommentRepositoryImpl() {
    }

    public static CommentRepositoryImpl getInstance() {

        if (instance == null) {
            instance = new CommentRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Comment findById(Integer id) {

        final String query = "select * from post_app.comments where comment_id = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return commentMapper(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Optional<Comment> findOne(Integer id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<Comment> findAll() {

        final String query = "select * from post_app.comments";

        List<Comment> comments = new ArrayList<>();

        try (Statement statement = getConnection().createStatement()) {

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                comments.add(commentMapper(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }

    @Override
    public List<Comment> findAll(int limit, int offset) {


        final String query = "select * from post_app.comments limit " + limit + " offset " + offset;

        List<Comment> comments = new ArrayList<>();

        try (Statement statement = getConnection().createStatement()) {

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                comments.add(commentMapper(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;

    }

    @Override
    public int getCommentCountByPost(Post post) {

        final String query = "select count(*) from post_app.comments where post_id = ?";

        int result = 0;

        try(PreparedStatement statement = getConnection().prepareStatement(query)){

            statement.setInt(1, post.getPostId());

            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                result = rs.getInt(1);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public boolean deleteAllCommentsByPost(Post post) {

        final String query = "delete from post_app.comments where post_id = ?";

        boolean result = false;

        try (PreparedStatement statement = getConnection().prepareStatement(query)){

            statement.setInt(1, post.getPostId());

            int row = statement.executeUpdate();
            if (row != 0){
                result = true;
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Comment> findAllCommentsByPost(Post post, int limit, int offset) {

        final String query = "select * from post_app.comments where post_id = ? limit ? offset ?";

        List<Comment> comments = new ArrayList<>();

        try(PreparedStatement statement = getConnection().prepareStatement(query)){

            statement.setInt(1, post.getPostId());
            statement.setInt(2, limit);
            statement.setInt(3, offset);


            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                comments.add(commentMapper(rs));
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return comments;
    }

    @Override
    public Comment creatObject(Comment object) {

        final String query = "insert into post_app.comments (message, post_id, user_id, created_at) values (?, ?, ?,?)";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, object.getMessage());
            statement.setInt(2, object.getPost().getPostId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Comment updateObject(Comment object) {

        final String query = "update post_app.comments set message = ?, post_id = ?, user_id = ?, created_at = ?" +
                " where comment_id = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {

            statement.setString(1, object.getMessage());
            statement.setInt(2, object.getPost().getPostId());
            statement.setInt(3, object.getUser().getUserId());
            statement.setDate(4, (Date) object.getCreatedAt());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return findById(object.getCommentId());
    }

    @Override
    public Integer deleteObject(Integer id) {

        final String query = "delete from post_app.comments where comment_id = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private Connection getConnection() {
        return connection.getDBConnection();
    }

    private Comment commentMapper(ResultSet rs) throws SQLException {

        Post post = PostRepositoryImpl.getInstance().findById(rs.getInt(POST_ID));
        User user = UserRepositoryImpl.getInstance().findById(rs.getInt(USER_ID));

        return Comment.builder()
                .setCommentId(rs.getInt(COMMENT_ID))
                .setMessage(rs.getString(MESSAGE))
                .setPost(post)
                .setUser(user)
                .setCreatedAt(rs.getDate(CREATED_AT))
                .build();
    }
}
