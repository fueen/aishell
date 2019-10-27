package com.ifueen.aishell.repository;

import com.ifueen.aishell.domain.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 继承JpaRepository接口，自动帮我们写增删改查，只需要将泛型定义好即可
 */

public interface EmployeeRepository extends BaseRepository<Employee,Long>{

        //通过用户名查询
        Employee findByUsername(String username);
        //模糊查询，通过用户名
        List<Employee> findByUsernameLike(String username);
        //通过用户名和邮件的模糊查询
        List<Employee> findByUsernameLikeAndEmailLike(String username,String email);

        //通过注解的方式查找用户
        @Query("select e from Employee e where e.username = ?1")
            Employee query(String username);

        //原生SQL的方法
        @Query(nativeQuery = true,value = "select * from employee")
            List<Employee> query02();

        //通过注解实现模糊查询
        @Query("select e from Employee e where e.username like ?1 and e.email like ?2")
            List<Employee> query03(String username,String email);

        //通过部门获取到相关的员工
        @Query("select e from Employee e where e.department.name=?1")
            List<Employee> findByDept(String DeptName);

}
