package com.ifueen.aishell.commons;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.ifueen.aishell.domain.Employee;
import com.ifueen.aishell.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 上传时进行自定义验证
 */
@Component
public class EmployeeExcelVerifyHandler implements IExcelVerifyHandler<Employee> {

    @Autowired
    private IEmployeeService ies;

    /**
     * 覆写验证的方法
     * @param employee
     * @return
     */
    @Override
    public ExcelVerifyHandlerResult verifyHandler(Employee employee) {
        ExcelVerifyHandlerResult result = new ExcelVerifyHandlerResult(true);
        //通过用户名查询到数据
        Employee byUserName = ies.findByUserName(employee.getUsername());
        if (byUserName!=null){
            //证明数据库中已经存在这个数据了
            result.setSuccess(false);
            result.setMsg("用户名重复");
        }
        return result;
    }
}
