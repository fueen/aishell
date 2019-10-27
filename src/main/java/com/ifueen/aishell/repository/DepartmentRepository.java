package com.ifueen.aishell.repository;

import com.ifueen.aishell.domain.Department;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 继承JpaRepository接口，自动帮我们写增删改查，只需要将泛型定义好即可
 */

public interface DepartmentRepository extends BaseRepository<Department,Long>{
    //通过名称拿到部门
    Department findByName(String name);
}
