package com.ifueen.aishell.service.impl;

import com.ifueen.aishell.domain.Purchasebill;
import com.ifueen.aishell.repository.PurchasebillRepository;
import com.ifueen.aishell.service.IPurchasebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Purchasebill的实现类
 */
@Service
public class PurchasebillServiceImpl extends BaseServiceImpl<Purchasebill,Long> implements IPurchasebillService {

    @Autowired
    private PurchasebillRepository purchasebillRepository;


}
