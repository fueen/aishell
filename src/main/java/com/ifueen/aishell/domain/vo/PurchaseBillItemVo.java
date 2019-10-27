package com.ifueen.aishell.domain.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ifueen.aishell.domain.Purchasebillitem;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class PurchaseBillItemVo {

    //id
    private Long id;
    //供应商
    private String supplier;
    //采购员
    private String buyer;
    //品牌
    private String product;
    //产品类型
    private String productType;
    //日期
    private Date vdate;
    //数量
    private BigDecimal num;
    //单价
    private BigDecimal price;
    //小计
    private BigDecimal amount;
    //状态
    private Integer status;
    //分组根据
    private String groupField;

    public PurchaseBillItemVo(Purchasebillitem item,Integer groupBy) {
        this.id = item.getId();
        this.supplier = item.getBill().getSupplier().getName();
        this.buyer = item.getBill().getBuyer().getUsername();
        this.product = item.getProduct().getName();
        this.productType = item.getProduct().getProductType().getName();
        this.vdate = item.getBill().getVdate();
        this.num = item.getNum();
        this.price = item.getPrice();
        this.amount = item.getAmount();
        this.status = item.getBill().getStatus();
        switch (groupBy){
            case 1:
                this.groupField = this.supplier;
                break;
            case 2:
                this.groupField = this.buyer;
                break;
            case 3:
                this.groupField = DateFormatUtils.format(this.vdate,"MM") + "月";
                break;
        }
    }

    @Override
    public String toString() {
        return "PurchaseBillItemVo{" +
                "id=" + id +
                ", supplier='" + supplier + '\'' +
                ", buyer='" + buyer + '\'' +
                ", product='" + product + '\'' +
                ", productType='" + productType + '\'' +
                ", vdate=" + vdate +
                ", num=" + num +
                ", price=" + price +
                ", amount=" + amount +
                ", status=" + status +
                ", groupField='" + groupField + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getVdate() {
        return vdate;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGroupField() {
        return groupField;
    }

    public void setGroupField(String groupField) {
        this.groupField = groupField;
    }
}
