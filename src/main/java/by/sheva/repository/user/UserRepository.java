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
