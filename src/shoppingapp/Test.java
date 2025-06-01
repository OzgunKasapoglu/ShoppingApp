// src/shoppingapp/Test.java
package shoppingapp;

public class Test {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ShoppingAppUI ui = new ShoppingAppUI();
                ui.setVisible(true);
            }
        });

        // Let the UI initialize (not ideal but works for testing)
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Test User creation
        User user = new User("ozgun", "Ozgun", "Kasapoglu", "1990-01-01", "password", "ozgun@example.com", "Home Address", "Work Address");
        System.out.println("User created: " + user.getUsername());

        // Test Product creation
        Product product = new Product("Laptop", "Black", "Electronics", 10, 2.5, "A powerful laptop");
        System.out.println("Product created: " + product.getProductName() + ", Stock: " + product.getProductStockInformation());

        // Test ordering a product
        product.order(2);
        System.out.println("Product stock after ordering 2: " + product.getProductStockInformation());

        // Test User ordering a product
        user.order(product);

        // Test favoriting a product
        user.favorite(product);
        user.favorite(product); // Should print already in favorites

        // Test CreditCard creation and assignment
        CreditCard card = new CreditCard();
        user.getCreditCards().add(card);
        System.out.println("Credit card added. Total cards: " + user.getCreditCards().size());

        // Test Order creation and processing
        Order order = new Order(user, product, card);
        order.processOrder();
        System.out.println("Order processed for user: " + order.getOrderingUser().getUsername());
    }
}
