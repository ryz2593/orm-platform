package com.ryz2593.orm;

import com.ryz2593.orm.dao.UserDAO;
import com.ryz2593.orm.dao.impl.UserDAOImpl;
import com.ryz2593.orm.domain.User;

import java.util.Date;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //创建用户对象
        User user = new User();
        user.setId(1001);
        user.setName("zs");
        user.setAge(30);
        user.setBirth(new Date());

        UserDAO dao = new UserDAOImpl();
        int row = dao.addUser(user);
        System.out.println(row > 0 ? "success" : "failure");
    }
}
