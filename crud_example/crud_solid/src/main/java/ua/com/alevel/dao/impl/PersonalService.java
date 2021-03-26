package ua.com.alevel.dao.impl;

import org.apache.log4j.Logger;
import ua.com.alevel.dao.UserService;
import ua.com.alevel.db.DBInMemory;
import ua.com.alevel.entity.Personal;
import ua.com.alevel.util.SecurityUtil;

import java.util.List;

public class PersonalService implements UserService<Personal> {

    private final Logger logger = Logger.getLogger(PersonalService.class);

    private final DBInMemory db = DBInMemory.getInstance();

    @Override
    public void create(Personal personal) {
        logger.info("Start user create");
        if (db.existByLogin(personal.getLogin())) {
            logger.error("user exist");
            throw new RuntimeException("user exist by email: " + personal.getLogin());
        }
        personal.setPassword(SecurityUtil.generateMD5(personal.getPassword()));
        db.createUser(personal);
        logger.info("Finish user create");
    }

    @Override
    public void update(Personal personal) {
        db.updateUser(personal);
    }

    @Override
    public void delete(String id) {
        db.deleteUser(id);
    }

    @Override
    public Personal findById(String id) {
        return db.findById(id);
    }

    @Override
    public Personal findByLogin(String login) {
        return db.findByLogin(login);
    }

    @Override
    public List<Personal> findAll() {
        return db.findAllPersonals();
    }
}
