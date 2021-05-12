package ua.com.alevel.db_all_circle.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.db_all_circle.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service("entityManagerUserDao")
@Transactional
public class EntityManagerUserDao implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
//        entityManager.getTransaction().begin();
        entityManager.merge(user);
//        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(find(id));
    }

    @Override
    public User find(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> find() {
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return (List<User>) query.getResultList();
    }
}
