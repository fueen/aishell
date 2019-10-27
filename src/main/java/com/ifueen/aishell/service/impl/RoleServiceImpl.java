package com.ifueen.aishell.service.impl;

import com.ifueen.aishell.domain.Role;
import com.ifueen.aishell.repository.RoleRepository;
import com.ifueen.aishell.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Role的实现类
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role,Long> implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

}
