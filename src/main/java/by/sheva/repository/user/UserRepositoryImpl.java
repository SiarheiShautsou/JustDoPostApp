package by.sheva.repository.user;

import by.sheva.configuration.PostgresDBConnection;
import by.sheva.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import static by.sheva.repository.user.UserTableColumns.EMAIL;
import static by.sheva.repository.user.UserTableColumns.ID;
import static by.sheva.repository.user.UserTableColumns.NAME;
import static by.sheva.repository.user.UserTableColumns.PASSWORD;
import static by.sheva.repository.user.UserTableColumns.PHOTO;
import static by.sheva.repository.user.UserTableColumns.USER_NAME;

public class UserRepositoryImpl implements UserRepository {

    private static UserRepositoryImpl instance;

    private final PostgresDBConnection connection = PostgresDBConnection.getInstance();

    public UserRepositoryImpl() {
    }

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }


    @Override
    public User findById(Integer id) {

        final String query = "select * from post_app.users where user_id = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return userMapper(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Optional<User> findOne(Integer id) {
        return Optional.of(findById(id));
    }

    @Override
    public Optional<User> findByUsername(String username) {

        final String query = "select * from post_app.users where username = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {

            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(userMapper(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getSubscribersByUser(User user) {

        final String query = "select u.name, u.username, u.email, u.photo from post_app.subscribers s " +
                " join users u on s.child_id = u.user_id where s.parent_id = ?";

        List<User> result = new ArrayList<>();

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {

            statement.setInt(1, user.getUserId());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                result.add(followerMapper(rs));
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getSubscriptionsByUser(User user) {

        final String query = "select u.name, u.username, u.email, u.photo from post_app.subscribers s " +
                " join users u on s.parent_id = u.user_id where s.child_id = ?";

        List<User> result = new ArrayList<>();

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {

            statement.setInt(1, user.getUserId());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                result.add(followerMapper(rs));
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getSubscribersCount(User user) {

        final String query = "select count (*) from post_app.subscribers where parent_id = ?";

        int count = 0;
        try(PreparedStatement statement = getConnection().prepareStatement(query)){
            statement.setInt(1, user.getUserId());
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                count = rs.getInt(1);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return count;
    }

    @Override
    public int getSubscriptionsCount(User user) {
        final String query = "select count (*) from post_app.subscribers where child_id = ?";

        int count = 0;
        try(PreparedStatement statement = getConnection().prepareStatement(query)){
            statement.setInt(1, user.getUserId());
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                count = rs.getInt(1);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    public boolean subscribeOnUser(User user, User subscriber) {

        final String query = "insert into post_app.subscribers (parent_id, child_id) values (?, ?)";

        boolean result = false;
        try(PreparedStatement statement = getConnection().prepareStatement(query)){

            statement.setInt(1, user.getUserId());
            statement.setInt(2, subscriber.getUserId());

            int rows = statement.executeUpdate();
            if(rows != 0){
                result = true;
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public boolean unsubscribeOnUser(User user, User subscriber) {

        final String query = "delete from post_app.subscribers where parent_id = ? and child_id = ?";

        boolean result = false;
        try(PreparedStatement statement = getConnection().prepareStatement(query)){

            statement.setInt(1, user.getUserId());
            statement.setInt(2, subscriber.getUserId());

            int rows = statement.executeUpdate();
            if(rows == 0){
                result = true;
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public boolean isSubscribed(User user, User subscriber) {

        final String query = "select count (*) from post_app.subscribers where parent_id = ? and child_id = ?";

        boolean result = false;
        try(PreparedStatement statement = getConnection().prepareStatement(query)){

            statement.setInt(1, user.getUserId());
            statement.setInt(2, subscriber.getUserId());

            int rows = statement.executeUpdate();
            if(rows == 0){
                result = true;
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return result;
    }


    @Override
    public List<User> findAll() {

        final String query = "select * from post_app.users";

        List<User> result = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()) {

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                result.add(userMapper(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;

    }

    @Override
    public List<User> findAll(int limit, int offset) {

        final String query = "select * from post_app.users limit " + limit + " offset " + offset;

        List<User> result = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()) {

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                result.add(userMapper(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    @Override
    public User creatObject(User object) {

        final String query = "insert into post_app.users(username, password, name, photo, email) values (?, ?, ?, ?, ?)";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, object.getUserName());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getName());
            statement.setString(4, object.getPhoto());
            statement.setString(5, object.getEmail());

            statement.executeUpdate();

            ResultSet resultSet = connection.createStatement().executeQuery("SELECT currval('post_app.users_user_id_seq') as last_id");
            resultSet.next();
            int userLastInsertId = resultSet.getInt("last_id");

            return findById(userLastInsertId);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User updateObject(User object) {

        final String query = "update post_app.users set username = ? , password = ?, name = ?, photo = ?, email = ? " +
                " where user_id = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {

            statement.setString(1, object.getUserName());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getName());
            statement.setString(4, object.getPhoto());
            statement.setString(5, object.getEmail());
            statement.setInt(6, object.getUserId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return findById(object.getUserId());
    }

    @Override
    public Integer deleteObject(Integer id) {

        final String query = "delete from post_app.users where user_id = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();

            return id;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Connection getConnection() {
        return connection.getDBConnection();
    }

    private User userMapper(ResultSet rs) throws SQLException {

        return User.builder()
                .setUserId(rs.getInt(ID))
                .setUserName(rs.getString(USER_NAME))
                .setPassword(rs.getString(PASSWORD))
                .setName(rs.getString(NAME))
                .setEmail(rs.getString(EMAIL))
                .setPhoto(rs.getString(PHOTO))
                .build();
    }

    private User followerMapper(ResultSet rs) throws SQLException {

        return User.builder()
                .setUserName(rs.getString(USER_NAME))
                .setName(rs.getString(NAME))
                .setEmail(rs.getString(EMAIL))
                .setPhoto(rs.getString(PHOTO))
                .build();
    }

}
