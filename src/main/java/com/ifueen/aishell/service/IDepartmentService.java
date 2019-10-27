package com.ifueen.aishell.service;

import com.ifueen.aishell.domain.Department;

/**
 * Department类的接口
 */
public interface IDepartmentService extends IBaseService<Department,Long>{

    Department findByName(String name);
}
