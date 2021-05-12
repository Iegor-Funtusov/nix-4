package ua.com.alevel.db_all_circle.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alevel.db_all_circle.entity.User;

import java.util.List;

@Service("springDataUserDao")
public class SpringDataUserDao implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User find(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> find() {
        return userRepository.findAll();
    }
}
