package com.ifueen.aishell.repository;

import com.ifueen.aishell.domain.Menu;
import com.ifueen.aishell.domain.Menu;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;


public interface MenuRepository extends BaseRepository<Menu,Long>{

    /**
     * 获取到所有父菜单
     * @return
     */
    @Query("select o from Menu o where o.url is null ")
    List<Menu> findParentMenus();


    /**
     * 通过用户ID获取所有的子菜单
     */
    @Query("select distinct m from Employee e join e.roles r join r.permissions p join p.menu m where e.id = ?1")
    List<Menu> findByLoginUser(Long userId);
}
