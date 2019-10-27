package com.ifueen.aishell.service;

import com.ifueen.aishell.domain.Purchasebillitem;
import com.ifueen.aishell.domain.vo.PurchaseBillItemVo;
import com.ifueen.aishell.query.PurchasebillitemQuery;

import java.util.List;

/**
 * Purchasebillitem类的接口
 */
public interface IPurchasebillitemService extends IBaseService<Purchasebillitem,Long>{

    //拿到数据
    List<PurchaseBillItemVo> purVos(PurchasebillitemQuery query);

}
