package shoppingapp;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * @author ozgunkasapoglu
 */
public class Order implements Serializable {
    private User orderingUser;
    private Product orderedProduct;
    private CreditCard creditCard;
    private int amount = 1;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if (amount > 0) {
            this.amount = amount;
        }
    }
    
    public void processOrder() {
        orderedProduct.order(amount);
        orderingUser.order(orderedProduct);
    }
    
}
