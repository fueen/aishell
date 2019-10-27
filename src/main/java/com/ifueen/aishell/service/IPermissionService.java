package com.ifueen.aishell.service;

import com.ifueen.aishell.domain.Permission;

import java.util.Set;

/**
 * Permission类的接口
 */
public interface IPermissionService extends IBaseService<Permission,Long>{
    Set<String> findsnByEmp(Long employeeId);
}
