package com.ifueen.aishell.service.impl;

import com.ifueen.aishell.domain.Permission;
import com.ifueen.aishell.repository.PermissionRepository;
import com.ifueen.aishell.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Permission的实现类
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission,Long> implements IPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Set<String> findsnByEmp(Long employeeId) {
        Set<String> strings = permissionRepository.findsnByEmp(employeeId);
        return strings;
    }
}
