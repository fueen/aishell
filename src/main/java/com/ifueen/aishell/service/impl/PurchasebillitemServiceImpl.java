package com.ifueen.aishell.service.impl;

import com.ifueen.aishell.domain.Purchasebillitem;
import com.ifueen.aishell.domain.vo.PurchaseBillItemVo;
import com.ifueen.aishell.query.PurchasebillitemQuery;
import com.ifueen.aishell.repository.PurchasebillitemRepository;
import com.ifueen.aishell.service.IPurchasebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Purchasebillitem的实现类
 */
@Service
public class PurchasebillitemServiceImpl extends BaseServiceImpl<Purchasebillitem,Long> implements IPurchasebillitemService {

    @Autowired
    private PurchasebillitemRepository purchasebillitemRepository;


    @Override
    public List<PurchaseBillItemVo> purVos(PurchasebillitemQuery query) {
        ArrayList<PurchaseBillItemVo> vos = new ArrayList<>();
        List<Purchasebillitem> purchasebillitems = super.queryAll(query);
        for (Purchasebillitem purchasebillitem : purchasebillitems) {
            PurchaseBillItemVo vo = new PurchaseBillItemVo(purchasebillitem,query.getGroupBy());
            vos.add(vo);
        }

        return vos;
    }
}
