package by.academy.exeption.service;

import by.academy.domain.User;
import by.academy.repository.UserRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor


public class UserServiceImpl implements UserService {

    private final UserRepositoryInterface userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

}
