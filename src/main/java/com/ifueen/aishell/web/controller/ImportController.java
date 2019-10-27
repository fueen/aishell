package com.ifueen.aishell.web.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.ifueen.aishell.commons.EmployeeExcelVerifyHandler;
import com.ifueen.aishell.domain.Department;
import com.ifueen.aishell.domain.Employee;
import com.ifueen.aishell.service.IDepartmentService;
import com.ifueen.aishell.service.IEmployeeService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private IEmployeeService ies;
    @Autowired
    private IDepartmentService ids;
    @Autowired
    private EmployeeExcelVerifyHandler employeeExcelVerifyHandler;

    @RequestMapping("/index")
    public String index(){
        return "import/index";
    }

    /*@RequestMapping("/employeeXlsx")
    public String employeeXlsx(MultipartFile file, HttpServletResponse response) throws Exception{

        //通过EasyPOI获取文件
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        //开启验证支持
        params.setNeedVerfiy(true);
        //设置一个验证处理器
        params.setVerifyHandler(employeeExcelVerifyHandler);
        //获取excel中的数据,封装成一个结果对象
        ExcelImportResult<Employee> result = ExcelImportUtil.importExcelMore(
                file.getInputStream(),
                Employee.class, params);
        //获取到正确的数据,把他们保存到数据库
        List<Employee> list = result.getList();
        list.forEach(employee -> {
            employee.setPassword("123");
            Department dept = ids.findByName(employee.getDepartment().getName());
            employee.setDepartment(dept);
            ies.save(employee);
        });
        //如果文件有错误,将数据返回到前台并给出提示信息,让前台下载一个错误的excel
        if (result.isVerfiyFail()){
            //4.2拿到错误的文件薄
            Workbook failWorkbook = result.getFailWorkbook();

            //把这个文件导出
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //mime类型
            response.setHeader("Content-disposition", "attachment;filename=error.xlsx"); //告诉浏览下载的是一个附件，名字叫做error.xlsx
            response.setHeader("Pragma", "No-cache");//设置不要缓存
            OutputStream ouputStream = response.getOutputStream();
            failWorkbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        }

        return "import/index";
    }*/


    @RequestMapping("/employeeXlsx")
    public String employeeXlsx(MultipartFile empFile, HttpServletResponse response) throws Exception {
        //通过EasyPOI获取文件
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        //开启验证支持
        params.setNeedVerfiy(true);
        //设置一个验证处理器
        params.setVerifyHandler(employeeExcelVerifyHandler);

        //获取excel中的数据,封装成一个结果对象
        ExcelImportResult<Employee> result = ExcelImportUtil.importExcelMore(
                empFile.getInputStream(),
                Employee.class, params);
        //获取到正确的数据,把他们保存到数据库
        List<Employee> list = result.getList();
        list.forEach(e->{
            e.setPassword("123");
            Department dept = ids.findByName(e.getDepartment().getName());
            e.setDepartment(dept);
            ies.save(e);
        });
        //如果文件有错误,将数据返回到前台并给出提示信息,让前台下载一个错误的excel
        if(result.isVerfiyFail()){
            //4.2拿到错误的文件薄
            Workbook failWorkbook = result.getFailWorkbook();

            //把这个文件导出
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //mime类型
            response.setHeader("Content-disposition", "attachment;filename=error.xlsx"); //告诉浏览下载的是一个附件，名字叫做error.xlsx
            response.setHeader("Pragma", "No-cache");//设置不要缓存
            OutputStream ouputStream = response.getOutputStream();
            failWorkbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        }

        return "import/index";
    }



}
