package com.ifueen.aishell.service;

import com.ifueen.aishell.commons.MD5Util;
import com.ifueen.aishell.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceTest {

    @Autowired
    private IEmployeeService employeeService;

    /**
     * 保存
     */
    @Test
    public void testsave(){
        Employee employee = new Employee();
        employee.setUsername("库布里克");
        employeeService.save(employee);
    }

    /**
     * 查询所有
     *
     */
    @Test
    public void testfindAll(){
        List<Employee> list = employeeService.findAll();
        list.forEach(employee -> System.out.println(employee));
    }

    /**
     * 删除
     *
     */
    @Test
    public void delete(){
    }

    /**
     * 更新
     */
    @Test
    public void testUpdate(){
        Employee employee = new Employee();
        employee.setId(301L);
        employee.setUsername("不急");
        employeeService.save(employee);
    }

    /**
     * 将数据库中的所有密码进行加密
     */
    @Test
    public void testUpdatePwd(){
        List<Employee> list = employeeService.findAll();
        list.forEach(employee -> {
            String username = employee.getUsername();
            //设置密码为加密后的密码
            employee.setPassword(MD5Util.createMd5Str(username));
            employeeService.save(employee);
        });
    }

}
