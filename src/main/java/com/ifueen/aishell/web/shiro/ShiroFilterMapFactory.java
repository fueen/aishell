package com.ifueen.aishell.web.shiro;

import com.ifueen.aishell.domain.Permission;
import com.ifueen.aishell.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用来存放相应的权限
 * 修改了这个需要重启(热启动咩用)
 */
public class ShiroFilterMapFactory {

    @Autowired
    private IPermissionService iPermissionService;

    public Map<String,String> createMap(){

        HashMap<String, String> map = new LinkedHashMap<>();
        //anon代表需要放行的路径
        map.put("/login","anon");
        //静态资源放行
        map.put("/login","anon");
        map.put("*.js","anon");
        map.put("*.css","anon");
        map.put("/css/**","anon");
        map.put("/js/**","anon");
        map.put("/easyui/**","anon");
        map.put("/images/**","anon");
        //perms:权限拦截
        List<Permission> permissions = iPermissionService.findAll();
        permissions.forEach(permission -> {
            map.put(permission.getUrl(),"aishellPerms["+permission.getSn()+"]");
        });
        //authc:拦截，这个必须放在最后
        map.put("/**","authc");
        return map;
    }


}
