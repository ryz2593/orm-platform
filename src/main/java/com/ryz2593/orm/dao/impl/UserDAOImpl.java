package com.ryz2593.orm.dao.impl;

import com.ryz2593.orm.dao.UserDAO;
import com.ryz2593.orm.domain.User;
import org.ryz2593.orm.session.Session;
import org.ryz2593.orm.session.impl.SessionImpl;

/**
 * @author ryz2593
 * @date 2019/4/23
 * @desc
 */
public class UserDAOImpl implements UserDAO {
    //jdbc 操作

    private Session session = new SessionImpl();

    @Override
    public int addUser(User user) {
        return session.save(user);
    }

}
