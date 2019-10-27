package com.ifueen.aishell.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchasebill")
public class Purchasebill extends BaseDomain{
    //时间
    private Date vdate;
    //总数
    private BigDecimal totalAmount;
    //总数量
    private BigDecimal totalNum;
    //创建时间
    private Date inputTime = new Date();
    //审核时间
    private Date auditorTime;
    //状态
    private Integer status = 0;

    //关联的供应商
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    //审核员
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditor_id")
    private Employee auditor;

    //采购员
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private Employee buyer;

    //录入人:就是当前登录用户
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inputUser_id")
    private Employee inputUser;

    //组合关系用List
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bill", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Purchasebillitem> items = new ArrayList<Purchasebillitem>();

    public List<Purchasebillitem> getItems() {
        return items;
    }

    public void setItems(List<Purchasebillitem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Purchasebill{" +
                "vdate=" + vdate +
                ", totalAmount=" + totalAmount +
                ", totalNum=" + totalNum +
                ", inputTime=" + inputTime +
                ", auditorTime=" + auditorTime +
                ", status=" + status +
                ", supplier=" + supplier +
                ", auditor=" + auditor +
                ", buyer=" + buyer +
                ", inputUser=" + inputUser +
                ", id=" + id +
                '}';
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getVdate() {
        return vdate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getAuditorTime() {
        return auditorTime;
    }

    public void setAuditorTime(Date auditorTime) {
        this.auditorTime = auditorTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Employee getAuditor() {
        return auditor;
    }

    public void setAuditor(Employee auditor) {
        this.auditor = auditor;
    }

    public Employee getBuyer() {
        return buyer;
    }

    public void setBuyer(Employee buyer) {
        this.buyer = buyer;
    }

    public Employee getInputUser() {
        return inputUser;
    }

    public void setInputUser(Employee inputUser) {
        this.inputUser = inputUser;
    }
}
