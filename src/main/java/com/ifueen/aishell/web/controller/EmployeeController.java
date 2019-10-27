package com.ifueen.aishell.web.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.ifueen.aishell.commons.ResultJson;
import com.ifueen.aishell.domain.Employee;
import com.ifueen.aishell.query.EmployeeQuery;
import com.ifueen.aishell.service.IEmployeeService;
import com.ifueen.aishell.commons.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController{

    @Autowired
    private IEmployeeService ies;


    @RequestMapping("/index")
    public String index(){
        return "employee/index";
    }

    /**
     * 获取所有
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<Employee> list(){
        return ies.findAll();
    }

    /**
     * 分页
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public PageUtil page(EmployeeQuery query){
        Page<Employee> employees = ies.queryPage(query);
        PageUtil pageUtil = new PageUtil(employees);
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
    @ModelAttribute("editEmployee")
    public Employee beforeUpdate(Long id,String cmd){
        if (id!=null && "update".equals(cmd)){
            //先从数据库中根据ID查询出相关的对象,这样有些不修改的值也会有默认值
            Employee employee = ies.findOne(id);
            /**
             * 设置关联的Department类为空,这样它不会去数据库这种进行查询
             * 不会和JPA产生关系,从而没有被持久化,这样就能避免n to n的问题
             */
            employee.setDepartment(null);
            return employee;
        }
        return null;
    }


    /**
     * 添加
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public ResultJson save(Employee employee){
        return saveorupdate(employee);
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultJson update(@ModelAttribute("editEmployee") Employee employee){
        System.out.println("进来了");
        return saveorupdate(employee);
    }

    /**
     * 添加和修改
     * @return
     */
    public ResultJson saveorupdate(Employee employee){
        try {
            ies.save(employee);
            return new ResultJson();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(false,e.getMessage());
        }

    }


    @RequestMapping("findById")
    @ResponseBody
    public Employee findById(Long id){
        Employee employee = ies.findOne(id);
        return employee;
    }

    @RequestMapping("export")
    public String expotr(EmployeeQuery query, ModelMap map, HttpServletRequest req){
        //根据条件拿到所有的数据
        List<Employee> employees = ies.queryAll(query);
        //设置导出的属性
        ExportParams params = new ExportParams("员工数据", "员工表", ExcelType.XSSF);
        //拿到真实路径
        String realPath = req.getServletContext().getRealPath("");
        System.out.println(realPath);
        employees.forEach(employee -> {
            //设置图片的路径
            employee.setHeadImage(realPath+employee.getHeadImage());
        });
        map.put(NormalExcelConstants.DATA_LIST, employees); // 数据集合
        map.put(NormalExcelConstants.CLASS, Employee.class);//导出实体
        map.put(NormalExcelConstants.PARAMS, params);//参数
        map.put(NormalExcelConstants.FILE_NAME, "employee");//文件名称
        return NormalExcelConstants.EASYPOI_EXCEL_VIEW;
    }

    /**
     * 获取采购部员工
     * @return
     */
    @RequestMapping("/findByDept")
    @ResponseBody
    public List<Employee> findByDept(){
        return ies.findByDept();
    }

}
