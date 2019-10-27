package com.ifueen.aishell.repository;

import com.ifueen.aishell.domain.Permission;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;


public interface PermissionRepository extends BaseRepository<Permission,Long>{

    @Query("select distinct p.sn from Employee e join e.roles r join r.permissions p where e.id = ?1")
    Set<String> findsnByEmp(Long employeeId);
}
