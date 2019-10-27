package com.ifueen.aishell.query;

import com.github.wenhao.jpa.Specifications;
import com.ifueen.aishell.domain.Purchasebill;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PurchasebillQuery extends BaseQuery{

    //名称
    private String name;

    //开始时间
    private Date begindate;

    //结束时间
    private Date enddate;

    public Date getBegindate() {
        return begindate;
    }
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    public Date getEnddate() {
        return enddate;
    }
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    //状态
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //覆写创建Specifications对象的方法
    @Override
    public Specification createSpec() {
        //如果结束时间存在,就加1
        if (enddate!=null){
            enddate = DateUtils.addDays(enddate,1);
        }

        Specification<Purchasebill> specification = Specifications.<Purchasebill>and().
                ge(begindate!=null,"vdate",begindate)
                .lt(enddate!=null,"vdate",enddate)
                .eq(status!=null,"status",status)
                .build();

        return specification;
    }
}
