package com.ifueen.aishell.web.controller;

import com.ifueen.aishell.commons.PageUtil;
import com.ifueen.aishell.commons.ResultJson;
import com.ifueen.aishell.domain.Employee;
import com.ifueen.aishell.domain.Menu;
import com.ifueen.aishell.query.MenuQuery;
import com.ifueen.aishell.service.IMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{

    @Autowired
    private IMenuService ies;


    @RequestMapping("/index")
    public String index(){
        return "menu/index";
    }

    /**
     * 获取所有
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<Menu> list(){
        return ies.findAll();
    }

    /**
     * 分页
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public PageUtil page(MenuQuery query){
        Page<Menu> menus = ies.queryPage(query);
        PageUtil pageUtil = new PageUtil(menus);
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
    @ModelAttribute("editMenu")
    public Menu beforeUpdate(Long id,String cmd){
        if (id!=null && "update".equals(cmd)){
            System.out.println("进来咯");
            //先从数据库中根据ID查询出相关的对象,这样有些不修改的值也会有默认值
            Menu menu = ies.findOne(id);
            /**
             * 设置关联的Department类为空,这样它不会去数据库这种进行查询
             * 不会和JPA产生关系,从而没有被持久化,这样就能避免n to n的问题
             */

            return menu;
        }
        return null;
    }


    /**
     * 添加
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultJson save(Menu menu){
        return saveorupdate(menu);
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultJson update(@ModelAttribute("editMenu") Menu menu){
        return saveorupdate(menu);
    }

    /**
     * 添加和修改
     * @return
     */
    public ResultJson saveorupdate(Menu menu){
        try {
            ies.save(menu);
            return new ResultJson();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(false,e.getMessage());
        }

    }

    @RequestMapping("findById")
    @ResponseBody
    public Menu findById(Long id){
        Menu menu = ies.findOne(id);
        return menu;
    }

    @RequestMapping("/findParentMenus")
    @ResponseBody
    public List<Menu> findParentMenus(){
        List<Menu> menus = ies.findParentMenus();
        return menus;
    }


    @ResponseBody
    @RequestMapping("/findLoginUser")
    public List<Menu> findLoginUser(){
        Subject subject = SecurityUtils.getSubject();
        Employee principal = (Employee) subject.getPrincipal();
        List<Menu> menus = ies.findByLoginUser(principal.getId());
        return menus;
    }


}
