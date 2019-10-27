package com.ifueen.aishell.service.impl;

import com.ifueen.aishell.domain.SystemDictionarydetail;
import com.ifueen.aishell.repository.SystemDictionarydetailRepository;
import com.ifueen.aishell.service.ISystemDictionarydetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * SystemDictionarydetail的实现类
 */
@Service
public class SystemDictionarydetailServiceImpl extends BaseServiceImpl<SystemDictionarydetail,Long> implements ISystemDictionarydetailService {

    @Autowired
    private SystemDictionarydetailRepository systemDictionarydetailRepository;


}
