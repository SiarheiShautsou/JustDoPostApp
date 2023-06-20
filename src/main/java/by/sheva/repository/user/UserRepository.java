package by.sheva.repository.user;

import by.sheva.domain.User;
import by.sheva.repository.CRUDRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CRUDRepository<Integer, User> {

    @Override
    User findById(Integer id) throws SQLException;

    @Override
    Optional<User> findOne(Integer id);

    Optional<User> findByUsername(String username) throws SQLException;

    List<User> getSubscribersByUser(User user);

    List<User> getSubscriptionsByUser(User user);

    int getSubscribersCount(User user);

    int getSubscriptionsCount(User user);

    boolean subscribeOnUser(User user, User subscriber);

    boolean unsubscribeOnUser(User user, User subscriber);

    boolean isSubscribed(String username, String subscriberUsername);

    @Override

    List<User> findAll();

    @Override
    List<User> findAll(int limit, int offset);

    @Override
    User creatObject(User object);

    @Override
    User updateObject(User object);

    @Override
    Integer deleteObject(Integer id);
}
