
package com.model;

import java.math.BigDecimal;

public class DetailCustomer {
    private String productName;
    private int quanity;
    private BigDecimal price ;
    private BigDecimal total;

    public DetailCustomer(String productName, int quanity, BigDecimal price, BigDecimal total) {
        this.productName = productName;
        this.quanity = quanity;
        this.price = price;
        this.total = total;
    }

    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    
}
