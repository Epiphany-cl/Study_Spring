package dao.impl;

import dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public void show() {
        System.out.println("Hello world!");
    }
}
