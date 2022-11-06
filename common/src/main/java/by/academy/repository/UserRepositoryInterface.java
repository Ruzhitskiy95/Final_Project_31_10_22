package by.academy.repository;

import by.academy.domain.User;

import java.util.Optional;

public interface UserRepositoryInterface extends CRUDRepository<Long, User> {

Optional<User> findByLogin(String login);
}
