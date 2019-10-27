package com.ifueen.aishell.service;

import com.ifueen.aishell.domain.Menu;

import java.util.List;
import java.util.Set;

/**
 * Menu类的接口
 */
public interface IMenuService extends IBaseService<Menu,Long>{
    List<Menu> findParentMenus();
    List<Menu> findByLoginUser(Long userId);
}
