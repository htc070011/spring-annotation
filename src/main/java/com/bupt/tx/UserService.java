package com.bupt.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Transactional
    public void insertUser() {
        userDao.insert();

        System.out.println("insert has been done");

        System.out.println(1 / 0);



    }

}
