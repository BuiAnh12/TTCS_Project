
package com.model;

import java.math.BigDecimal;

public class CartElement {
    private int productId;
    private String productName;
    private int quantity;
    private BigDecimal sellPrice;
    private BigDecimal totalPrice;
    private int index;

    public CartElement(int productId, String productName, int quantity, BigDecimal sellPrice, BigDecimal totalPrice, int index) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.sellPrice = sellPrice;
        this.totalPrice = totalPrice;
        this.index = index;
    }
    
    
    
    
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    
}
