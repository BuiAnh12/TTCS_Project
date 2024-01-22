package com.model;
import java.sql.Date;
import java.math.BigDecimal;
public class Import{
    private int importId;
    private int productId;
    private Date manufacturingDate;
    private Date expiryDate;
    private Date importDate;
    private int importQuantity;
    private int availableQuantity;
    private BigDecimal unitPrice;
    private BigDecimal sellPrice;
    private String productName;
    private String category;
    
    

    public Import(){};
    public Import(int importId,int productId, Date manufacturingDate, Date expiryDate, Date importDate, int importQuantity, int availableQuantity, BigDecimal unitPrice, BigDecimal sellPrice,String productName,String category ) {
        this.importId = importId;
        this.productId = productId;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
        this.importDate = importDate;
        this.importQuantity = importQuantity;
        this.availableQuantity = availableQuantity;
        this.unitPrice = unitPrice;
        this.sellPrice = sellPrice;
        this.productName=productName;
        this.category=category;
    }
    
    public Import(int importId,int productId, Date manufacturingDate, Date expiryDate, Date importDate, int importQuantity, int availableQuantity, BigDecimal unitPrice, BigDecimal sellPrice) {
        this.importId = importId;
        this.productId = productId;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
        this.importDate = importDate;
        this.importQuantity = importQuantity;
        this.availableQuantity = availableQuantity;
        this.unitPrice = unitPrice;
        this.sellPrice = sellPrice;
    }
    
    public Import(int productId, Date manufacturingDate, Date expiryDate, Date importDate,int importQuantity, int availableQuantity, BigDecimal unitPrice, BigDecimal sellPrice) {
        this.productId = productId;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
        this.importDate = importDate;
         this.importQuantity = importQuantity;
        this.availableQuantity = availableQuantity;
        this.unitPrice = unitPrice;
        this.sellPrice = sellPrice;
        
    }
    public Import( Date manufacturingDate, Date expiryDate, Date importDate,int importQuantity, int availableQuantity, BigDecimal unitPrice, BigDecimal sellPrice,int importId,int productId) {
        this.importId = importId;
        this.productId = productId;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
        this.importDate = importDate;
         this.importQuantity = importQuantity;
        this.availableQuantity = availableQuantity;
        this.unitPrice = unitPrice;
        this.sellPrice = sellPrice;
        
    }
    

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    

    public int getImportId() {
        return importId;
    }

    public int getProductId() {
        return productId;
    }

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public Date getImportDate() {
        return importDate;
    }

    public int getImportQuantity() {
        return importQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setImportId(int importId) {
        this.importId = importId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public void setImportQuantity(int importQuantity) {
        this.importQuantity = importQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }
    

}