package by.academy.exeption.service;

import by.academy.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    List<User> findAll(int limit, int offset);

    User findById(Long id);

    Optional<User> findOne(Long id);

    User create(User object);

    Long delete(Long id);

    User update(User object);

    List<User> search(int verifiedLimit, int verifiedOffset);
}
