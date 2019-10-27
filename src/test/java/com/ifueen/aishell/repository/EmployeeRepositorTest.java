package com.ifueen.aishell.repository;

import com.github.wenhao.jpa.Specifications;
import com.ifueen.aishell.domain.Employee;
import com.ifueen.aishell.query.EmployeeQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeRepositorTest {

    @Autowired
    private EmployeeRepository repository;

    /**
     * 查询所有
     */
    @Test
    public void findAll(){
        List<Employee> list = repository.findAll();
        list.forEach(employee -> System.out.println(employee));
    }

    /**
     * 通过ID查询
     */
    @Test
    public void findById(){
        Employee one = repository.findOne(1L);
        System.out.println(one);
    }

    /**
     * 保存
     */
    @Test
    public void save(){
        Employee employee = new Employee();
        employee.setUsername("韩梅梅");
        employee.setPassword("454545");
        employee.setAge(18);
        repository.save(employee);
    }

    /**
     * 通过ID删除
     */
    @Test
    public void delById(){
        repository.delete(274L);
    }

    /**
     * 更新
     */
    @Test
    public void update(){
        Employee employee = repository.findOne(274L);
        employee.setUsername("山泥若");
        repository.save(employee);
    }

    /**
     * 排序
     */
    @Test
    public void sort(){
        Sort sort = new Sort(Sort.Direction.DESC,"age");
        List<Employee> employees = repository.findAll(sort);
        employees.forEach(employee -> System.out.println(employee));
    }

    /**
     * 分页
     */
    @Test
    public void page(){
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<Employee> page = repository.findAll(pageRequest);
        page.forEach(employee -> System.out.println(employee));
    }

    /**
     * 分页加上排序
     * 就在分页对象里面加入排序对象即可
     */
    @Test
    public void pagesort(){
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        PageRequest pageRequest = new PageRequest(0, 10,sort);
        Page<Employee> page = repository.findAll(pageRequest);
        page.forEach(employee -> System.out.println(employee));
    }

    /**
     * 利用jpaSpecificationExecutor完成模糊查询
     */
    @Test
    public void jpaSpecificationExecutor(){
        List<Employee> username = repository.findAll(new Specification<Employee>() {
            /**
             *
             * @param root  根，可以看作是字段
             * @param criteriaQuery     连接的,and or等..
             * @param criteriaBuilder   sql中的条件或者组合等..
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path username = root.get("username");
                Predicate predicate = criteriaBuilder.like(username, "%1%");
                return predicate;
            }
        });
        username.forEach(employee -> System.out.println(employee));
    }

    /**
     * 多个条件查询
     * 通过username、email、和年龄来进行查询
     */
    @Test
    public void jpaSpecificationExecutor02(){
        List<Employee> employees = repository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path username = root.get("username");
                Predicate p1 = criteriaBuilder.like(username, "%1%");
                Path email = root.get("email");
                Predicate p2 = criteriaBuilder.like(email, "%2%");
                Path age = root.get("age");
                Predicate p3 = criteriaBuilder.gt(age, 18);
                Predicate p4 = criteriaBuilder.and(p1, p2, p3);

                return p4;
            }
        });
        employees.forEach(employee -> System.out.println(employee));
    }

    /**
     * 高级查询和分页
     */
    @Test
    public void jpaSpecificationExecutor03(){

        PageRequest pageRequest = new PageRequest(0, 10);

        Page<Employee> page = repository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path username = root.get("username");
                Predicate predicate = criteriaBuilder.like(username, "%1%");
                return predicate;
            }
        }, pageRequest);
        page.forEach(employee -> System.out.println(employee));
    }

    /**
     * 高级查询+分页+排序
     * 其实就是在分页的对象里面加上一个排序即可
     */
    @Test
    public void jpaSpecificationExecutor04(){

        Sort sort = new Sort(Sort.Direction.DESC, "age");
        PageRequest pageRequest = new PageRequest(0, 10,sort);

        Page<Employee> page = repository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path username = root.get("username");
                Predicate predicate = criteriaBuilder.like(username, "%1%");
                return predicate;
            }
        }, pageRequest);
        page.forEach(employee -> System.out.println(employee));

    }

    /**
     * 使用jpa-spec进行模糊查询
     */
    @Test
    public void jpaSpec01(){
        Specification<Employee> specification = Specifications.<Employee>and().like("username", "%1%").build();
        List<Employee> employees = repository.findAll(specification);
        employees.forEach(employee -> System.out.println(employee));
    }

    /**
     * 多个条件查询
     */
    @Test
    public void jpaSpec02(){
        Specification<Employee> specification = Specifications.<Employee>and().like("username", "%1%")
                .like("email", "%2%")
                .gt("age", 18).build();
        List<Employee> employees = repository.findAll(specification);
        employees.forEach(employee -> System.out.println(employee));
    }

    /**
     * 高级查询和分页
     * 就在刚刚的基础上面加上一个分页对象即可
     */
    @Test
    public void jpaSpec03(){
        Specification<Employee> specification = Specifications.<Employee>and().like("username", "%1%")
                .like("email", "%2%")
                .gt("age", 18).build();
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<Employee> page = repository.findAll(specification, pageRequest);
        page.forEach(employee -> System.out.println(employee));
    }

    /**
     * 测试query
     */
    @Test
    public void jpaSpec04(){
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setOrderName("age");
        employeeQuery.setOrderType(true);
        Sort sort = employeeQuery.createSort();
        PageRequest pageRequest = new PageRequest(employeeQuery.getCurrentPage(), employeeQuery.getPageSize(), sort);
        Specification spec = employeeQuery.createSpec();
        Page<Employee> page = repository.findAll(spec, pageRequest);
        page.forEach(employee -> System.out.println(employee));
    }


}
