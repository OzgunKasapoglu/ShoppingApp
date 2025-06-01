/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoppingapp;

/**
 *
 * @author ozgunkasapoglu
 */
public class Product {
    private String productName;
    private String productColor;
    private String productCategory;
    private int productStockInformation;
    private double productWeight;
    private String productDescription;

    public Product() {
    }


    public Product(String productName, String productColor, String productCategory, int productStockInformation, double productWeight, String productDescription) {
        if (productStockInformation < 0 || productWeight < 0) {
            System.out.println("Stock and weight cannot be negative.");
            // Optionally, set default values or throw an exception
        } else {
            this.productName = productName;
            this.productColor = productColor;
            this.productCategory = productCategory;
            this.productStockInformation = productStockInformation;
            this.productWeight = productWeight;
            this.productDescription = productDescription;
        }
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getProductColor() {
        return productColor;
    }
    
    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }
    
    public String getProductCategory() {
        return productCategory;
    }
    
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductStockInformation() {
        return productStockInformation;
    }
    
    public void setProductStockInformation(int productStockInformation) {
        if (productStockInformation < 0) {
            System.out.println("Stock cannot be negative.");
        } else {
            this.productStockInformation = productStockInformation;
        }
    }
    
    public double getProductWeight() {
        return productWeight;
    }
    
    public void setProductWeight(double productWeight) {
        if (productWeight < 0) {
            System.out.println("Weight cannot be negative.");
        } else {
            this.productWeight = productWeight;
        }
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
   
    public void order(int amount) {
        if (amount <= 0) {
            System.out.println("You should order at least 1 product.");
        } else if ((this.productStockInformation - amount) < 0) {
            System.out.println("Stock cannot be negative. You can order maximum " 
                    + this.productStockInformation + " items.");
        } else {
            this.productStockInformation -= amount;
        }
    }
}


