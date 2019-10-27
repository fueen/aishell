package com.ifueen.aishell.query;

import com.github.wenhao.jpa.Specifications;
import com.ifueen.aishell.domain.Purchasebill;
import com.ifueen.aishell.domain.Purchasebillitem;
import com.ifueen.aishell.domain.vo.PurchaseBillItemVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PurchasebillitemQuery extends BaseQuery{



    //开始时间
    private Date beginDate;

    //结束时间
    private Date endDate;

    //状态
    private Integer status;

    //分组的值
    private Integer groupBy = 1;

    public Date getBeginDate() {
        return beginDate;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(Integer groupBy) {
        this.groupBy = groupBy;
    }



    //覆写创建Specifications对象的方法
    @Override
    public Specification createSpec() {
        //如果结束时间存在,就加1
        if (endDate!=null){
            endDate = DateUtils.addDays(endDate,1);
        }
        Specification<Purchasebillitem> specification = Specifications.<Purchasebillitem>and().
                ge(beginDate!=null,"bill.vdate",beginDate)
                .lt(endDate!=null,"bill.vdate",endDate)
                .eq(status!=null,"bill.status",status)
                .build();
        return specification;
    }


}
