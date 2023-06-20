package by.sheva.service.user;

import by.sheva.domain.User;
import by.sheva.repository.user.UserRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance;

    private final UserRepositoryImpl repository = UserRepositoryImpl.getInstance();

    public UserServiceImpl() {
    }

    public static UserServiceImpl getInstance(){
        if(instance == null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public User findUserById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<User> findOneUser(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public List<User> getAllSubscribersByUser(User user) {
        return repository.getSubscribersByUser(user);
    }

    @Override
    public List<User> getAllSubscriptionsByUser(User user) {
        return repository.getSubscriptionsByUser(user);
    }

    @Override
    public int getSubscribersCountByUser(User user) {
        return repository.getSubscribersCount(user);
    }

    @Override
    public int getSubscriptionsCountByUser(User user) {
        return repository.getSubscriptionsCount(user);
    }

    @Override
    public boolean subscribeOnUser(User user, User subscriber) {
        return repository.subscribeOnUser(user, subscriber);
    }

    @Override
    public boolean unsubscribeOnUser(User user, User subscriber) {
        return repository.unsubscribeOnUser(user, subscriber);
    }

    @Override
    public boolean isSubscribed(String username, String subscriberUsername) {
        return repository.isSubscribed(username, subscriberUsername);
    }

    @Override
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public List<User> findAllUsersWithParams(int limit, int offset) {
        return repository.findAll(limit, offset);
    }

    @Override
    public User creatUser(User user) {
        return repository.creatObject(user);
    }

    @Override
    public User updateUser(User user) {
        return repository.updateObject(user);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return repository.deleteObject(id);
    }
}
