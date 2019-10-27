package com.ifueen.aishell.service;

import com.ifueen.aishell.domain.Employee;

import java.util.List;

/**
 * Employee类的接口
 */
public interface IEmployeeService extends IBaseService<Employee,Long>{
    /**
     * 根据用户名查询，返回相应的对象
     * @param username
     * @return
     */
    Employee findByUserName(String username);

    //通过部门获取到员工
    List<Employee> findByDept();
}
