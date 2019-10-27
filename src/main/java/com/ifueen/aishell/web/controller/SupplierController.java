package com.ifueen.aishell.web.controller;

import com.ifueen.aishell.commons.PageUtil;
import com.ifueen.aishell.commons.ResultJson;
import com.ifueen.aishell.domain.Supplier;
import com.ifueen.aishell.query.SupplierQuery;
import com.ifueen.aishell.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController extends BaseController{

    @Autowired
    private ISupplierService ies;


    @RequestMapping("/index")
    public String index(){
        return "supplier/index";
    }

    /**
     * 获取所有
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<Supplier> list(){
        return ies.findAll();
    }

    /**
     * 分页
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public PageUtil page(SupplierQuery query){
        Page<Supplier> suppliers = ies.queryPage(query);
        PageUtil pageUtil = new PageUtil(suppliers);
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
    @ModelAttribute("editSupplier")
    public Supplier beforeUpdate(Long id,String cmd){
        if (id!=null && "update".equals(cmd)){
            System.out.println("进来咯");
            //先从数据库中根据ID查询出相关的对象,这样有些不修改的值也会有默认值
            Supplier supplier = ies.findOne(id);
            /**
             * 设置关联的Department类为空,这样它不会去数据库这种进行查询
             * 不会和JPA产生关系,从而没有被持久化,这样就能避免n to n的问题
             */

            return supplier;
        }
        return null;
    }


    /**
     * 添加
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultJson save(Supplier supplier){
        return saveorupdate(supplier);
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultJson update(@ModelAttribute("editSupplier") Supplier supplier){
        System.out.println("进来了");
        return saveorupdate(supplier);
    }

    /**
     * 添加和修改
     * @return
     */
    public ResultJson saveorupdate(Supplier supplier){
        try {
            ies.save(supplier);
            return new ResultJson();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(false,e.getMessage());
        }

    }

    @RequestMapping("findById")
    @ResponseBody
    public Supplier findById(Long id){
        Supplier supplier = ies.findOne(id);
        return supplier;
    }

}
