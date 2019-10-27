package com.ifueen.aishell.service.impl;

import com.ifueen.aishell.domain.Menu;
import com.ifueen.aishell.repository.MenuRepository;
import com.ifueen.aishell.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Menu的实现类
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu,Long> implements IMenuService {

    @Autowired
    private MenuRepository menuRepository;


    @Override
    public List<Menu> findParentMenus() {
        List<Menu> parentMenus = menuRepository.findParentMenus();
        return parentMenus;
    }


    @Override
    public List<Menu> findByLoginUser(Long userId) {
        //准备好父菜单的容器
        ArrayList<Menu> parentMenus  = new ArrayList<>();
        //从数据库中查询到子菜单
        List<Menu> childrenMenus  = menuRepository.findByLoginUser(userId);
        //将子菜单进行遍历
        childrenMenus.forEach(childrenmenus -> {
            //拿到相对应的父菜单
            Menu parent = childrenmenus.getParent();
            //判断父菜单中是否存在
            if (parentMenus.contains(parent)){
                //indexOf:返回字符串中某个值第一次出现的索引位置
                //如果存在就拿到第一个索引位置的值然后放进去
                int i = parentMenus.indexOf(parent);
                Menu menu = parentMenus.get(i);
                menu.getChildren().add(childrenmenus);
            }else {
                //不存在就单独将父菜单放进去
                parentMenus.add(parent);
                parent.getChildren().add(childrenmenus);
            }
        });


        return parentMenus;
    }
}
