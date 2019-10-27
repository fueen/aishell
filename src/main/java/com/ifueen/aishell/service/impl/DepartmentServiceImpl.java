package com.ifueen.aishell.service.impl;

import com.ifueen.aishell.domain.Department;
import com.ifueen.aishell.repository.DepartmentRepository;
import com.ifueen.aishell.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Department的实现类
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department,Long> implements IDepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department findByName(String name) {

        return departmentRepository.findByName(name);
    }
}
