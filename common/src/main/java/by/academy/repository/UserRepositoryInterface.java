package by.academy.repository;

import by.academy.domain.User;

import java.util.Map;
import java.util.Optional;

public interface UserRepositoryInterface extends CRUDRepository<Long, User> {
Map<String,String> getUserStats();

Optional<User> findByLogin(String login);
}
