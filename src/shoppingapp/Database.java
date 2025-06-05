package shoppingapp;

import java.io.*;
import java.util.ArrayList;

public class Database {
    private static final String USERS_FILE = "users.dat";
    private static final String PRODUCTS_FILE = "products.dat";
    private static final String ORDERS_FILE = "orders.dat";

    // Save data methods
    public static void saveUsers(ArrayList<User> users) {
        saveObject(users, USERS_FILE);
    }

    public static void saveProducts(ArrayList<Product> products) {
        saveObject(products, PRODUCTS_FILE);
    }

    public static void saveOrders(ArrayList<Order> orders) {
        saveObject(orders, ORDERS_FILE);
    }

    // Load data methods
    @SuppressWarnings("unchecked")
    public static ArrayList<User> loadUsers() {
        Object obj = loadObject(USERS_FILE);
        if (obj != null) {
            return (ArrayList<User>) obj;
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Product> loadProducts() {
        Object obj = loadObject(PRODUCTS_FILE);
        if (obj != null) {
            return (ArrayList<Product>) obj;
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Order> loadOrders() {
        Object obj = loadObject(ORDERS_FILE);
        if (obj != null) {
            return (ArrayList<Order>) obj;
        }
        return new ArrayList<>();
    }

    // Private helper methods
    private static void saveObject(Object obj, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(obj);
            System.out.println("Saved data to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving to " + filename + ": " + e.getMessage());
        }
    }

    private static Object loadObject(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            System.out.println("Loaded data from " + filename);
            return ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename + " (This is normal for first run)");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading from " + filename + ": " + e.getMessage());
        }
        return null;
    }
}