package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.ProfileDao;
import ua.com.alevel.data.Profile;
import ua.com.alevel.db.TestDB;

import java.util.List;

/**
 * @author Iehor Funtusov, created 23/12/2020 - 8:16 PM
 */
public class ProfileDaoImpl implements ProfileDao {

    @Override
    public void create(Profile data) {
        TestDB.getInstance().createProfile(data);
    }

    @Override
    public Profile read(int id) {
        return TestDB.getInstance().readProfile(id);
    }

    @Override
    public List<Profile> read() {
        return null;
    }

    @Override
    public void update(Profile data) {
        TestDB.getInstance().updateProfile(data);
    }

    @Override
    public void delete(int id) {
        TestDB.getInstance().deleteProfile(id);
    }
}
