//package ua.com.alevel.db_all_circle.dao;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Isolation;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//import ua.com.alevel.db_all_circle.entity.User;
//
//import java.util.List;
//
//@Service("hibernateUserDao")
//public class HibernateUserDao implements UserDao {
//
//    private final SessionFactory sessionFactory;
//
//    public HibernateUserDao(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
//    public void create(User user) {
//        Session session = sessionFactory.getCurrentSession();
//        session.save(user);
//    }
//
//    @Override
//    @Transactional
//    public void update(User user) {
//        Session session = sessionFactory.getCurrentSession();
//        session.update(user);
//    }
//
//    @Override
//    @Transactional
//    public void delete(Integer id) {
//        Session session = sessionFactory.getCurrentSession();
//        session.delete(find(id));
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public User find(Integer id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.find(User.class, id);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<User> find() {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select u from User u", User.class).getResultList();
//    }
//}
