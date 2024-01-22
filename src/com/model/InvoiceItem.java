package com.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public class InvoiceItem {
    private int invoiceItemId;
    private int invoiceId;
    private int productId;
    private int importId;
    private BigDecimal unitPrice;
    private int quantity;
    private BigDecimal totalPrice;
    private BigDecimal profit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public InvoiceItem(int invoiceItemId, int invoiceId, int productId, int importId, BigDecimal unitPrice, int quantity, BigDecimal totalPrice, BigDecimal profit) {
        this.invoiceItemId = invoiceItemId;
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.importId = importId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.profit = profit;
        //this.createdAt = createdAt;
        //this.updatedAt = updatedAt;
    }
    public InvoiceItem( int invoiceId, int productId, int importId, BigDecimal unitPrice, int quantity, BigDecimal totalPrice, BigDecimal profit) {
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.importId = importId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.profit = profit;
    }
    public InvoiceItem( int invoiceId, int productId, int importId, BigDecimal unitPrice, int quantity, BigDecimal totalPrice, BigDecimal profit,int invoiceItemId) {
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.importId = importId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.profit = profit;
        this.invoiceItemId = invoiceItemId;
    }
    public InvoiceItem(int productId, int importId, BigDecimal unitPrice, int quantity, BigDecimal totalPrice, BigDecimal profit) {
        this.productId = productId;
        this.importId = importId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.profit = profit;
    }
    

    public int getInvoiceItemId() {
        return invoiceItemId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getProductId() {
        return productId;
    }

    public int getImportId() {
        return importId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setInvoiceItemId(int invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setImportId(int importId) {
        this.importId = importId;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
  
}