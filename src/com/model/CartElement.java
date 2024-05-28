
package com.model;

import java.math.BigDecimal;

public class CartElement {
    private int productId;
    private String productName;
    private int quantity;
    private BigDecimal sellPrice;
    private BigDecimal totalPrice;
    private int index;
    private int InvoiceItemId = -1;
    private int importId = -1;
    private BigDecimal unitPrice = new BigDecimal(-1);
    private int previousQuantity;

    public CartElement(int productId, String productName, int quantity, BigDecimal sellPrice, BigDecimal totalPrice, int index, int InvoiceItemId, int importId, BigDecimal unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.sellPrice = sellPrice;
        this.totalPrice = totalPrice;
        this.index = index;
        this.InvoiceItemId = InvoiceItemId;
        this.importId = importId;
        this.unitPrice = unitPrice;
        this.previousQuantity = quantity;
    }
    
    public CartElement(int productId, String productName, int quantity, BigDecimal sellPrice, BigDecimal totalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.sellPrice = sellPrice;
        this.totalPrice = totalPrice;
        this.previousQuantity = quantity;
        this.importId = -1;
    }

    public CartElement(int productId, String productName, int quantity, BigDecimal sellPrice, BigDecimal totalPrice, int index) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.sellPrice = sellPrice;
        this.totalPrice = totalPrice;
        this.index = index;
        this.previousQuantity = quantity;
        this.importId = -1;
    }

    

    public int getImportId() {
        return importId;
    }

    public void setImportId(int importId) {
        this.importId = importId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    
    
    public int getInvoiceItemId() {
        return InvoiceItemId;
    }

    public void setInvoiceItemId(int InvoiceItemId) {
        this.InvoiceItemId = InvoiceItemId;
    }

    public int getPreviousQuantity() {
        return previousQuantity;
    }

    public void setPreviousQuantity(int previousQuantity) {
        this.previousQuantity = previousQuantity;
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
