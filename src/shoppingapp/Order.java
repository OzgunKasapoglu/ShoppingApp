/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoppingapp;

/**
 *
 * @author ozgunkasapoglu
 */
public class Order {
    private User orderingUser;
    private Product orderedProduct;
    private CreditCard creditCard;
    private int amount;

    public Order() {
        this.orderingUser = null;
        this.orderedProduct = null;
        this.creditCard = null;
    }

    public Order(User orderingUser, Product orderedProduct, CreditCard creditCard) {
        this.orderingUser = orderingUser;
        this.orderedProduct = orderedProduct;
        this.creditCard = creditCard;
    }

    public User getOrderingUser() {
        return orderingUser;
    }

    public void setOrderingUser(User orderingUser) {
        this.orderingUser = orderingUser;
    }

    public Product getOrderedProduct() {
        return orderedProduct;
    }

    public void setOrderedProduct(Product orderedProduct) {
        this.orderedProduct = orderedProduct;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
    
    public void processOrder() {
        orderedProduct.order(amount);
        orderingUser.order(orderedProduct);
    }
    
}
