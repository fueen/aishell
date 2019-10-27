package com.ifueen.aishell.web.controller;

import com.ifueen.aishell.commons.PageUtil;
import com.ifueen.aishell.commons.ResultJson;
import com.ifueen.aishell.domain.SystemDictionaryType;
import com.ifueen.aishell.query.SystemDictionaryTypeQuery;
import com.ifueen.aishell.service.ISystemDictionaryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/systemDictionaryType")
public class SystemDictionaryTypeController extends BaseController{

    @Autowired
    private ISystemDictionaryTypeService ies;


    @RequestMapping("/index")
    public String index(){
        return "systemDictionaryType/index";
    }

    /**
     * 获取所有
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<SystemDictionaryType> list(){
        return ies.findAll();
    }

    /**
     * 分页
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public PageUtil page(SystemDictionaryTypeQuery query){
        Page<SystemDictionaryType> systemDictionaryTypes = ies.queryPage(query);
        PageUtil pageUtil = new PageUtil(systemDictionaryTypes);
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
    @ModelAttribute("editSystemDictionaryType")
    public SystemDictionaryType beforeUpdate(Long id,String cmd){
        if (id!=null && "update".equals(cmd)){
            System.out.println("进来咯");
            //先从数据库中根据ID查询出相关的对象,这样有些不修改的值也会有默认值
            SystemDictionaryType systemDictionaryType = ies.findOne(id);
            /**
             * 设置关联的Department类为空,这样它不会去数据库这种进行查询
             * 不会和JPA产生关系,从而没有被持久化,这样就能避免n to n的问题
             */

            return systemDictionaryType;
        }
        return null;
    }


    /**
     * 添加
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultJson save(SystemDictionaryType systemDictionaryType){
        return saveorupdate(systemDictionaryType);
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultJson update(@ModelAttribute("editSystemDictionaryType") SystemDictionaryType systemDictionaryType){
        System.out.println("进来了");
        return saveorupdate(systemDictionaryType);
    }

    /**
     * 添加和修改
     * @return
     */
    public ResultJson saveorupdate(SystemDictionaryType systemDictionaryType){
        try {
            ies.save(systemDictionaryType);
            return new ResultJson();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(false,e.getMessage());
        }

    }

    @RequestMapping("findById")
    @ResponseBody
    public SystemDictionaryType findById(Long id){
        SystemDictionaryType systemDictionaryType = ies.findOne(id);
        return systemDictionaryType;
    }

}
