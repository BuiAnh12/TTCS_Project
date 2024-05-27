package com.model;
import java.util.Date;
import java.sql.Timestamp;
public class Invoice {
    private int invoiceId;
    private int customerId;
    private int staffId;
    private Timestamp purchaseDate;
    private Timestamp updateDate;
    private String customerName;
    private String staffName;
    private int totalAmount;

    public Invoice(int invoiceId, int customerId, int staffId, Timestamp purchaseDate, String customerName, String staffName, int totalAmount, Timestamp updateDate) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.staffId = staffId;
        this.purchaseDate = purchaseDate;
        this.customerName = customerName;
        this.staffName = staffName;
        this.totalAmount = totalAmount;
        this.updateDate = updateDate;
    }
    public Invoice(int invoiceId, int customerId, int staffId, Timestamp purchaseDate, Timestamp updateDate) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.staffId = staffId;
        this.purchaseDate = purchaseDate;
        this.updateDate = updateDate;
    }
    public Invoice( int customerId, int staffId, Timestamp purchaseDate, Timestamp updateDate) {
        this.customerId = customerId;
        this.staffId = staffId;
        this.purchaseDate = purchaseDate;
        this.updateDate = updateDate;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getStaffId() {
        return staffId;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getStaffName() {
        return staffName;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

}