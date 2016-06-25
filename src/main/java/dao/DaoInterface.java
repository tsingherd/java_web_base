package dao;

import java.util.List;

/**
 * Created by TangJiong on 2015/12/20.
 * 提供一个dao类的规范，即要实现的最基本的增删改查操作
 * 参考ORM面向对象的操作，add, delete, update方法的参数都是实体对象
 * 每个方法具体的实现以及参数类型的确定根据具体dao类的需求灵活调整
 */
public interface DaoInterface<T> {

    int add(final T obj);

    void delete(final T obj);

    void update(final T obj);

    T findById(Object id);

    List<T> findAll();

    List<T> findByPage(int pageSize, int currentPage);

}
