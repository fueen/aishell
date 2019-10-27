package com.ifueen.aishell.query;

import com.github.wenhao.jpa.Specifications;
import com.ifueen.aishell.domain.Department;
import com.ifueen.aishell.domain.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeQuery extends BaseQuery{

    //用户名
    private String username;
    //邮箱
    private String email;
    //年龄
    private Integer age;
    //所属部门
    private Long departmentId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    //覆写创建Specifications对象的方法
    @Override
    public Specification createSpec() {
        Specification<Employee> specification = Specifications.<Employee>and().like(StringUtils.isNoneBlank(username), "username", "%" + username + "%")
                .like(StringUtils.isNoneBlank(email), "email", "%" + email + "%")
                .gt(age != null, "age", age)
                .eq(departmentId!=null,"department.id",departmentId).build();

        return specification;
    }
}
