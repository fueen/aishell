package com.ifueen.aishell.service.impl;

import com.ifueen.aishell.domain.SystemDictionaryType;
import com.ifueen.aishell.repository.SystemDictionaryTypeRepository;
import com.ifueen.aishell.service.ISystemDictionaryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SystemDictionaryType的实现类
 */
@Service
public class SystemDictionaryTypeServiceImpl extends BaseServiceImpl<SystemDictionaryType,Long> implements ISystemDictionaryTypeService {

    @Autowired
    private SystemDictionaryTypeRepository systemDictionaryTypeRepository;


}
