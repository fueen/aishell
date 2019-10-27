package com.ifueen.aishell.service;

import com.ifueen.aishell.query.BaseQuery;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 所有Service的父接口
 * 基本CRUD功能
 * @param <T>
 * @param <ID>
 */
public interface IBaseService<T,ID extends Serializable> {

    //增加和修改
    void save(T t);
    //删除
    void delete(ID id);
    //通过ID查询单个
    T findOne(ID id);
    //查询所有相关类的数据
    List<T> findAll();

    //高级查询部分
    //通过条件查询相关信息
    List<T> queryAll(BaseQuery baseQuery);
    //通过条件查询然后进行分页
    Page<T> queryPage(BaseQuery baseQuery);
    //根据JPQL查询
    //第一个参数表示jpql语句，第二个参数表示语句中?的值
    List queryJpql(String jpql,Object... params);


}
