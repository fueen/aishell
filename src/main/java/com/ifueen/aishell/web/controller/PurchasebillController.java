package com.ifueen.aishell.web.controller;

import com.ifueen.aishell.commons.PageUtil;
import com.ifueen.aishell.commons.ResultJson;
import com.ifueen.aishell.domain.Employee;
import com.ifueen.aishell.domain.Purchasebill;
import com.ifueen.aishell.domain.Purchasebillitem;
import com.ifueen.aishell.query.PurchasebillQuery;
import com.ifueen.aishell.service.IPurchasebillService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/purchasebill")
public class PurchasebillController extends BaseController{

    @Autowired
    private IPurchasebillService ies;


    @RequestMapping("/index")
    public String index(){
        return "purchasebill/index";
    }

    /**
     * 获取所有
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<Purchasebill> list(){
        return ies.findAll();
    }

    /**
     * 分页
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public PageUtil page(PurchasebillQuery query){
        Page<Purchasebill> purchasebills = ies.queryPage(query);
        PageUtil pageUtil = new PageUtil(purchasebills);
        return pageUtil;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultJson delete(Long id){
        try {
            ies.delete(id);
            System.out.println("成功");
            return new ResultJson();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(false,e.getMessage());
        }

    }


    /**
     * 解决数据丢失问题
     * 每次修改之前执行的处理
     */
    @ModelAttribute("editPurchasebill")
    public Purchasebill beforeUpdate(Long id,String cmd){
        if (id!=null && "update".equals(cmd)){
            //先从数据库中根据ID查询出相关的对象,这样有些不修改的值也会有默认值
            Purchasebill purchasebill = ies.findOne(id);
            /**
             * 设置关联的Department类为空,这样它不会去数据库这种进行查询
             * 不会和JPA产生关系,从而没有被持久化,这样就能避免n to n的问题
             */
            purchasebill.getItems().clear();
            purchasebill.setBuyer(null);
            purchasebill.setSupplier(null);
            return purchasebill;
        }
        return null;
    }


    /**
     * 添加
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultJson save(Purchasebill purchasebill){
        //拿到当前的登录用户
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        purchasebill.setInputUser(employee);
        return saveorupdate(purchasebill);
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultJson update(@ModelAttribute("editPurchasebill") Purchasebill purchasebill){
        System.out.println("进来了");
        return saveorupdate(purchasebill);
    }

    /**
     * 添加和修改
     * @return
     */
    public ResultJson saveorupdate(Purchasebill purchasebill){
        //准备总金额和总数量
        BigDecimal totalAmount = new BigDecimal(0);
        BigDecimal totalNum = new BigDecimal(0);
        try {
            List<Purchasebillitem> items = purchasebill.getItems();
            for (Purchasebillitem item : items) {
                item.setBill(purchasebill);
                BigDecimal multiply = item.getPrice().multiply(item.getNum());
                item.setAmount(multiply);
                totalAmount = totalAmount.add(multiply);
                totalNum = totalNum.add(item.getNum());
            }
            //把金额设置进去
            purchasebill.setTotalAmount(totalAmount);
            purchasebill.setTotalNum(totalNum);
            ies.save(purchasebill);
            return new ResultJson();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(false,e.getMessage());
        }

    }

    @RequestMapping("findById")
    @ResponseBody
    public Purchasebill findById(Long id){
        Purchasebill purchasebill = ies.findOne(id);
        return purchasebill;
    }

}
