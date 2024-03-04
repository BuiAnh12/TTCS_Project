package com.model;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private String productName;
    private String manufacturer;
    private String description;
    private String category;
    private int availability;
    private BigDecimal sellPrice;

    public Product(int productId, String productName, String manufacturer, String description, String category, int availability, BigDecimal sellPrice) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.description = description;
        this.category = category;
        this.availability = availability;
        this.sellPrice = sellPrice;
    }
    
    
    
    public Product(int productId, String productName, String manufacturer, String description, String category, int availability) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.description = description;
        this.category = category;
        this.availability = availability;
    }
    
    

    public Product(int productId, String productName, String manufacturer, String description, String category, BigDecimal sellPrice) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.description = description;
        this.category = category;
        this.sellPrice = sellPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    
    
    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
    
    

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
}