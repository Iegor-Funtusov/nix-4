package ua.com.alevel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        userRepository.save(user);
    }

    @Override
    public void update(Integer id, UserDto dto) {
        User user = find(id);
        BeanUtils.copyProperties(dto, user);
        userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        find(id);
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Integer id) {
        return find(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    private User find(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new RestException("user not found by id");
        }
        return user;
    }
}
