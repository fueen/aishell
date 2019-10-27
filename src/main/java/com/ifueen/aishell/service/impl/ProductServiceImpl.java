package com.ifueen.aishell.service.impl;

import com.ifueen.aishell.domain.Product;
import com.ifueen.aishell.repository.ProductRepository;
import com.ifueen.aishell.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Product的实现类
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product,Long> implements IProductService {

    @Autowired
    private ProductRepository productRepository;


}
