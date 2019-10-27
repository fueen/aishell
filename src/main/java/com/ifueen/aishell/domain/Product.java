package com.ifueen.aishell.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product extends BaseDomain{

    //产品名称
    private String name;
    //颜色
    private String color;
    //图片
    private String pic;
    //缩略图
    private String smallPic;
    //成本价
    private BigDecimal costPrice;
    //销售价格
    private BigDecimal salePrice;

    // 对应的二级产品类型
    @ManyToOne
    @JoinColumn(name = "types_id")
    private ProductType productType;

    // 数据字典明细：单位
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private SystemDictionarydetail systemDictionarydetail;

    //数据字典明细：品牌
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private SystemDictionarydetail systemDictionarydetail1;

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", pic='" + pic + '\'' +
                ", smallPic='" + smallPic + '\'' +
                ", costPrice=" + costPrice +
                ", salePrice=" + salePrice +
                ", productType=" + productType +
                ", systemDictionarydetail=" + systemDictionarydetail +
                ", systemDictionarydetail1=" + systemDictionarydetail1 +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public SystemDictionarydetail getSystemDictionarydetail() {
        return systemDictionarydetail;
    }

    public void setSystemDictionarydetail(SystemDictionarydetail systemDictionarydetail) {
        this.systemDictionarydetail = systemDictionarydetail;
    }

    public SystemDictionarydetail getSystemDictionarydetail1() {
        return systemDictionarydetail1;
    }

    public void setSystemDictionarydetail1(SystemDictionarydetail systemDictionarydetail1) {
        this.systemDictionarydetail1 = systemDictionarydetail1;
    }
}
