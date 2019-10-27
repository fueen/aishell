package com.ifueen.aishell.service.impl;

import com.ifueen.aishell.domain.Supplier;
import com.ifueen.aishell.repository.SupplierRepository;
import com.ifueen.aishell.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Supplier的实现类
 */
@Service
public class SupplierServiceImpl extends BaseServiceImpl<Supplier,Long> implements ISupplierService {

    @Autowired
    private SupplierRepository supplierRepository;


}
