package com.ifueen.aishell.web.controller;

import com.ifueen.aishell.domain.vo.PurchaseBillItemVo;
import com.ifueen.aishell.query.PurchasebillitemQuery;
import com.ifueen.aishell.service.IPurchasebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/purchasebillitem")
public class PurchasebillitemController extends BaseController{

    @Autowired
    private IPurchasebillitemService ies;


    @RequestMapping("/index")
    public String index(){
        return "purchasebillitem/index";
    }

    /**
     * 获取所有
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<PurchaseBillItemVo> list(PurchasebillitemQuery query){
        return ies.purVos(query);
    }



}
