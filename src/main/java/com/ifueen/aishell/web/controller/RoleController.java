package com.ifueen.aishell.web.controller;

import com.ifueen.aishell.commons.PageUtil;
import com.ifueen.aishell.commons.ResultJson;
import com.ifueen.aishell.domain.Role;
import com.ifueen.aishell.query.RoleQuery;
import com.ifueen.aishell.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{

    @Autowired
    private IRoleService ies;


    @RequestMapping("/index")
    public String index(){
        return "role/index";
    }

    /**
     * 获取所有
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<Role> list(){
        return ies.findAll();
    }

    /**
     * 分页
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public PageUtil page(RoleQuery query){
        Page<Role> roles = ies.queryPage(query);
        PageUtil pageUtil = new PageUtil(roles);
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
    @ModelAttribute("editRole")
    public Role beforeUpdate(Long id,String cmd){
        if (id!=null && "update".equals(cmd)){
            //先从数据库中根据ID查询出相关的对象,这样有些不修改的值也会有默认值
            Role role = ies.findOne(id);
            /**
             * 设置关联的Department类为空,这样它不会去数据库这种进行查询
             * 不会和JPA产生关系,从而没有被持久化,这样就能避免n to n的问题
             */
            role.getPermissions().clear();
            return role;
        }
        return null;
    }


    /**
     * 添加
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultJson save(Role role){
        return saveorupdate(role);
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultJson update(@ModelAttribute("editRole") Role role){
        System.out.println("进来了");
        return saveorupdate(role);
    }

    /**
     * 添加和修改
     * @return
     */
    public ResultJson saveorupdate(Role role){
        try {
            ies.save(role);
            return new ResultJson();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(false,e.getMessage());
        }

    }

    @RequestMapping("findById")
    @ResponseBody
    public Role findById(Long id){
        Role role = ies.findOne(id);
        return role;
    }

}
