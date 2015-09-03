package com.example.matthew.qubapp.Model;

/**
 * Created by Matthew on 26/08/2015.
 */
public class Product {

    private int id;
    private String description;
    private String brand;
    private String category;
    private double RRP;
    private double price;
    private int saving;
    private String barcode;
    private String image;
    private String link;
    private String linkName;

    public Product(){
        super();
    }

    public Product(int id, String description, String brand, String category, double RRP, double price, int saving, String barcode, String image, String link, String linkName) {
        this.id = id;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.RRP = RRP;
        this.price = price;
        this.saving = saving;
        this.barcode = barcode;
        this.image = image;
        this.link = link;
        this.linkName = linkName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRRP() {
        return RRP;
    }

    public void setRRP(double RRP) {
        this.RRP = RRP;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSaving() {
        return saving;
    }

    public void setSaving(int saving) {
        this.saving = saving;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }
}
