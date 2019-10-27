package com.ifueen.aishell.service.impl;

import com.ifueen.aishell.query.BaseQuery;
import com.ifueen.aishell.repository.BaseRepository;
import com.ifueen.aishell.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

//事务
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public abstract class BaseServiceImpl<T,ID extends Serializable> implements IBaseService<T,ID> {

    @Autowired
    private BaseRepository<T,ID> baseRepository;

    //从上下文中获取对象
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(T t) {
        baseRepository.save(t);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        baseRepository.delete(id);
    }

    @Override
    @Transactional
    public T findOne(ID id) {
        T t = baseRepository.findOne(id);
        return t;
    }

    @Override
    public List<T> findAll() {
        List<T> list = baseRepository.findAll();
        return list;
    }

    @Override
    public List<T> queryAll(BaseQuery baseQuery) {
        //查询的规则
        Specification spec = baseQuery.createSpec();
        List list = baseRepository.findAll(spec);
        return list;
    }

    @Override
    public Page<T> queryPage(BaseQuery baseQuery) {

        //先拿到分页对象
        PageRequest pageRequest = new PageRequest(baseQuery.getCurrentPage(), baseQuery.getPageSize(), baseQuery.createSort());
        //拿到规则对象
        Specification spec = baseQuery.createSpec();
        Page page = baseRepository.findAll(spec, pageRequest);
        return page;
    }

    @Override
    public List queryJpql(String jpql, Object... params) {
        //获取到query对象
        Query query = entityManager.createQuery(jpql);
        //设置条件值
        for (int i = 0; i <params.length; i++) {
            query.setParameter(i+1,params[i]);
        }
        //执行query语句
        List list = query.getResultList();
        return list;
    }
}
