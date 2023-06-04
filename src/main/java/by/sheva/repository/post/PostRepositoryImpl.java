package by.sheva.repository.post;

import by.sheva.configuration.PostgresDBConnection;
import by.sheva.domain.Post;
import by.sheva.domain.User;
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

import static by.sheva.repository.post.PostTableColumns.CREATED_AT;
import static by.sheva.repository.post.PostTableColumns.DESCRIPTION;
import static by.sheva.repository.post.PostTableColumns.IMAGE;
import static by.sheva.repository.post.PostTableColumns.POST_ID;
import static by.sheva.repository.post.PostTableColumns.USER_ID;

public class PostRepositoryImpl implements PostRepository {

    private static PostRepositoryImpl instance;

    private final PostgresDBConnection connection = PostgresDBConnection.getInstance();

    public PostRepositoryImpl() {
    }

    public static PostRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new PostRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Post findById(Integer id) {

        final String query = "select * from post_app.posts where post_id = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return postMapper(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Optional<Post> findOne(Integer id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<Post> findAll() {

        final String query = "select * from post_app.posts";

        List<Post> posts = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                posts.add(postMapper(rs));
            }

            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> findAll(int limit, int offset) {

        final String query = "select * from post_app.posts limit " + limit
                + " offset " + offset;

        List<Post> posts = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                posts.add(postMapper(rs));
            }

            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Post creatObject(Post object) {

        final String query = "insert into post_app.posts (user_id, image, created_at, description) " +
                "values (?, ?, ?, ?)";

        PreparedStatement statement;
        Connection connection;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);

            statement.setInt(1, object.getUser().getUserId());
            statement.setString(2, object.getImage());
            statement.setDate(3, (Date) object.getCreatedAt());
            statement.setString(4, object.getDescription());

            statement.executeUpdate();

            ResultSet rs = connection.createStatement().executeQuery("select currval('post_app.posts_post_id_seq.') as last_id");
            int lastInsertPostId = rs.getInt("last_id");

            return findById(lastInsertPostId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Post updateObject(Post object) {

        final String query = "update post_app.posts set user_id = ?, image = ?, created_at = ?, description = ? " +
                "where post_id = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {

            statement.setInt(1, object.getUser().getUserId());
            statement.setString(2, object.getImage());
            statement.setDate(3, (Date) object.getCreatedAt());
            statement.setString(4, object.getDescription());
            statement.setInt(5, object.getPostId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findById(object.getPostId());
    }

    @Override
    public Integer deleteObject(Integer id) {

        final String query = "delete from post_app.posts where user_id = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setInt(1, id);

            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;
    }

    private Post postMapper(ResultSet resultSet) throws SQLException {

        User user = UserRepositoryImpl.getInstance().findById(resultSet.getInt(USER_ID));
        return Post.builder()
                .setPostId(resultSet.getInt(POST_ID))
                .setUser(user)
                .setImage(resultSet.getString(IMAGE))
                .setCreatedAt(resultSet.getDate(CREATED_AT))
                .setDescription(resultSet.getString(DESCRIPTION))
                .build();

    }

    private Connection getConnection() {
        return connection.getDBConnection();
    }
}
