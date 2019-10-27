package com.ifueen.aishell.service.impl;

import com.ifueen.aishell.domain.ProductType;
import com.ifueen.aishell.repository.ProductTypeRepository;
import com.ifueen.aishell.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductType的实现类
 */
@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType,Long> implements IProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;


}
