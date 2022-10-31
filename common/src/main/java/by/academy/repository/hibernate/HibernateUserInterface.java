package by.academy.repository.hibernate;

import by.academy.domain.User;
import by.academy.domain.entity.HibernateUser;
import by.academy.repository.CRUDRepository;

import java.util.Map;
import java.util.Optional;

public interface HibernateUserInterface extends CRUDRepository <Long, HibernateUser>{
    Map<String,String> getUserStats();

    Optional<User> findByLogin(String login);
}
