/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoppingapp;
import java.util.ArrayList;
/**
 *
 * @author ozgunkasapoglu
 */
public class User {
    private String username;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String password;
    private String emailAddress;
    private String homeAddress;
    private String workAddress;
    private ArrayList<Product> orderedProducts;
    private ArrayList<Product> favoriteProducts;
    private ArrayList<CreditCard> creditCards;
    
    public User(String username, String name, String surname, String dateOfBirth, String password, String emailAddress, String homeAddress, String workAddress) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.emailAddress = emailAddress;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.orderedProducts = new ArrayList <Product>();
        this.favoriteProducts = new ArrayList <Product>();
        this.creditCards = new ArrayList <CreditCard>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public ArrayList<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(ArrayList<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public ArrayList<Product> getFavoriteProducts() {
        return favoriteProducts;
    }

    public void setFavoriteProducts(ArrayList<Product> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }

    public ArrayList<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(ArrayList<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }


    public void order(Product product) {
        if (product != null) {
            orderedProducts.add(product);
            System.out.println("Sipariş verildi! Eklenen ürün:" + product.getProductName());
        }
    }
    
    public void favorite(Product product) {
        if(product != null & !favoriteProducts.contains(product)) {
            favoriteProducts.add(product);
            System.out.println("Ürün favorilere eklendi. Eklenen ürün: " + product.getProductName());
        } else if(product !=null){
            System.out.println("Ürün favorilerde zaten mevcut");
        }
    }
}