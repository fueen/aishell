package com.ifueen.aishell.web.shiro;

import com.ifueen.aishell.domain.Employee;
import com.ifueen.aishell.service.IEmployeeService;
import com.ifueen.aishell.service.IPermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义一个Realm
 */
public class JpaRealm extends AuthorizingRealm {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPermissionService iPermissionService;

    //授权功能
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //从数据库中获取到角色并且方法授权对象中
        Set<String> roles = getRoles();
        authorizationInfo.setRoles(roles);

        //从数据库中获取到权限并且方法授权对象中
        Subject subject = SecurityUtils.getSubject();
        Employee principal = (Employee) subject.getPrincipal();
        Set<String> perms = iPermissionService.findsnByEmp(principal.getId());
        authorizationInfo.setStringPermissions(perms);
        return authorizationInfo;
    }

    /**
     * 假设这里是角色管理
     * @return
     */
    public Set<String> getRoles(){
        Set<String> set = new HashSet<String>();
        set.add("admin");
        set.add("HR");
        return set;
    }

    /**
     * 假设这里是权限管理
     * @return
     */
    public Set<String> getPerms(){
        Set<String> set = new HashSet<String>();
        set.add("employee:*");
        return set;
    }


    //身份认证功能
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //拿到令牌
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //通过令牌拿到用户名
        String username = token.getUsername();
        //通过用户名查找到密码
        Employee employee = employeeService.findByUserName(username);
        //如果对象不存在(表示没有通过用户名查找到用户，用户不存在的情况)
        if (employee==null){
            return null;
        }
        /**
         * 返回的SimpleAuthenticationInfo对象
         * 第一个参数表示主体：随便填写
         * 第二个参数表示密码：填写数据库中的密码
         * 第三个参数：盐值
         * 第四个参数表示这个Realm的名称：自定义名字
         */
        //对于加密后的密码，这里不需要进行加密，shiro会自动判断密码是否正确，这里只需要写正常功能即可
        //拿到盐值对象
        ByteSource salt = ByteSource.Util.bytes("ifueen");
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(employee, employee.getPassword(), salt,"myRealm");

        return authenticationInfo;
    }

}
