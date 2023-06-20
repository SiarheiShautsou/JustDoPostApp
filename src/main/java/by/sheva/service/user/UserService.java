package by.sheva.service.user;

import by.sheva.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User findUserById(Integer id);

    Optional<User> findOneUser(Integer id);

    Optional<User> findUserByUsername(String username);

    List<User> getAllSubscribersByUser(User user);

    List<User> getAllSubscriptionsByUser(User user);

    int getSubscribersCountByUser(User user);

    int getSubscriptionsCountByUser(User user);

    boolean subscribeOnUser(User user, User subscriber);

    boolean unsubscribeOnUser(User user, User subscriber);

    boolean isSubscribed(String username, String subscriberUsername);

    List<User> findAllUsers();

    List<User> findAllUsersWithParams(int limit, int offset);

    User creatUser(User user);

    User updateUser(User user);

    Integer deleteUser(Integer id);

}
