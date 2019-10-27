package com.ifueen.aishell.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "producttype")
public class ProductType extends BaseDomain{
    //名称
    private String name;
    private String descs;

    @Override
    public String toString() {
        return "ProductType{" +
                "name='" + name + '\'' +
                ", descs='" + descs + '\'' +
                ", productType=" + productType +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ProductType productType;

}
