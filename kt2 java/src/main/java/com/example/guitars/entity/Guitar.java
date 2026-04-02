package com.example.guitars.entity;

public class Guitar {
    private Long id;
    private String brand;
    private String model;
    private int stringsCount;

    public Guitar(Long id, String brand, String model, int stringsCount) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.stringsCount = stringsCount;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getStringsCount() { return stringsCount; }
    public void setStringsCount(int stringsCount) { this.stringsCount = stringsCount; }
}