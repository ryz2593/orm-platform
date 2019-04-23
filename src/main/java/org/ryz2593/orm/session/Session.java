package org.ryz2593.orm.session;

/**
 * @author ryz2593
 * @date 2019/4/23
 * @desc
 */
public interface Session {
    <T> int save(T entity);
}
