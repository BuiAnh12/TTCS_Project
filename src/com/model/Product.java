package com.model;

public class Product {
    private int productId;
    private String productName;
    private String manufacturer;
    private String description;
    private String category;

    public Product(int productId, String productName, String manufacturer, String description, String category) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.description = description;
        this.category = category;
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