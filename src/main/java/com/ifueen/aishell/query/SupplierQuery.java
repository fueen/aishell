package com.ifueen.aishell.query;

import com.github.wenhao.jpa.Specifications;
import com.ifueen.aishell.domain.Supplier;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class SupplierQuery extends BaseQuery{

    //名称
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //覆写创建Specifications对象的方法
    @Override
    public Specification createSpec() {
        Specification<Supplier> specification = Specifications.<Supplier>and().like(StringUtils.isNoneBlank(name), "name", "%" + name + "%")
                .build();

        return specification;
    }
}
