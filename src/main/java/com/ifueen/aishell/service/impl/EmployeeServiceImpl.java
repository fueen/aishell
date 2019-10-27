package com.ifueen.aishell.service.impl;

import com.ifueen.aishell.commons.MD5Util;
import com.ifueen.aishell.domain.Employee;
import com.ifueen.aishell.repository.EmployeeRepository;
import com.ifueen.aishell.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Employee的实现类
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee,Long> implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    /**
     * 添加员工的时候进行密码加密
     * @param employee
     */

    @Override
    public void save(Employee employee) {
        //如果ID为空，则表示添加员工操作
        if (employee.getId()==null)
            employee.setPassword(MD5Util.createMd5Str(employee.getPassword()));
        super.save(employee);
    }

    @Override
    public Employee findByUserName(String username) {
        Employee employee = employeeRepository.findByUsername(username);
        return employee;
    }

    @Override
    public List<Employee> findByDept() {
        return employeeRepository.findByDept("采购部");
    }
}
