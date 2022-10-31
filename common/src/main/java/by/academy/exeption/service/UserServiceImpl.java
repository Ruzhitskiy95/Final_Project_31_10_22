package by.academy.exeption.service;

import by.academy.domain.User;
import by.academy.repository.UserRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor


public class UserServiceImpl implements UserService{

////    @Autowired
////    @Qualifier("userRepository")
//    @Inject
//    @Named("userRepository")

    private final UserRepositoryInterface userRepository;
//    public UserServiceImpl(@Qualifier("userRepository") UserRepositoryInterface userRepository){
//        this.userRepository = userRepository;
//    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        return userRepository.findAll(limit,offset);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User create(User object) {
        return userRepository.create(object);
    }

    @Override
    public Long delete(Long id) {
        return userRepository.delete(id);
    }

    @Override
    public User update(User object) {
        return userRepository.update(object);
    }

    @Override
    public List<User> search(int verifiedLimit, int verifiedOffset) {
        return userRepository.findAll(verifiedLimit, verifiedOffset);
    }
}
