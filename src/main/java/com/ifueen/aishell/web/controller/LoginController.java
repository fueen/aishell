package com.ifueen.aishell.web.controller;

import com.ifueen.aishell.domain.Employee;
import com.ifueen.aishell.service.IEmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private IEmployeeService ies;

    //跳转到登录界面
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login1(){
        return "nlogin";
    }

    //响应登录的请求
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String username, String password, Model model){

        //拿到当前用户
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            subject.login(token);
            return "redirect:/main";
        } catch (UnknownAccountException e) {
            model.addAttribute("error","用户名或密码错误,建议自杀");
            model.addAttribute("username",username);
            e.printStackTrace();
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("error","用户名或密码错误,建议自杀");
            e.printStackTrace();
        } catch (AuthenticationException e) {
            model.addAttribute("error","遇到了一个神秘错误");
            e.printStackTrace();
        }
        return "nlogin";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "nlogin";
    }
    @RequestMapping("/register")
    public String register(Employee employee,String npassword,Model model){
        //如果两次密码不一致
        if (!npassword.equals(employee.getPassword())){
            model.addAttribute("pwderror","两次密码不一致");
        }else {
            ies.save(employee);
        }
        return  "nlogin";
    }

}
