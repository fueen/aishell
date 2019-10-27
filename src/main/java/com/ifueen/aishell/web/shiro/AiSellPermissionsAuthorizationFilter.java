package com.ifueen.aishell.web.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AiSellPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {
    /**
     * 重写onAccessDenied,让shiro拦截支持Ajax的请求
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = this.getSubject(request, response);
        if (subject.getPrincipal() == null) {
            this.saveRequestAndRedirectToLogin(request, response);
        } else {
            //首先确定是否为Ajax请求,如果是的话就返回json数据
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            //拿到请求头中的数据
            String header = req.getHeader("X-Requested-With");
            //进行判断
            if ("XMLHttpRequest".equals(header)){
                //返回json数据
                resp.setContentType("application/json;charset=UTF-8");
                //通过响应流把json数据返回去
                resp.getWriter().print("{\"success\":false,\"msg\":\"没有权限\"}");
            }else {
                String unauthorizedUrl = this.getUnauthorizedUrl();
                if (StringUtils.hasText(unauthorizedUrl)) {
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
                } else {
                    WebUtils.toHttp(response).sendError(401);
                }
            }
        }

        return false;
    }
}
