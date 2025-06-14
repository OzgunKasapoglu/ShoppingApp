package shoppingapp;
/**
 *
 * @author ozgunkasapoglu
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ShoppingAppUI extends javax.swing.JFrame {

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private User currentUser = null;
    private DefaultListModel<String> productListModel = new DefaultListModel<>();
    private ArrayList<Order> completedOrders = new ArrayList<>();


    /** Creates new form ShoppingAppUI */
    public ShoppingAppUI() {
        initializeTheme();
        initComponents();
        users = Database.loadUsers();
        products = Database.loadProducts();
        completedOrders = Database.loadOrders();
        if (products.isEmpty()) {
            initializeDefaultProducts();
        }
    }

    private void initializeDefaultProducts() {
        products.add(new Product("Laptop", "Black", "Electronics", 10, 2.5, "A powerful laptop"));
        products.add(new Product("Smartphone", "White", "Electronics", 20, 0.3, "Latest smartphone"));
        products.add(new Product("Headphones", "Red", "Audio", 15, 0.2, "Noise cancelling"));
        products.add(new Product("Gaming Mouse", "Black", "Computer Accessories", 25, 0.1, "High precision gaming mouse"));
        products.add(new Product("Mechanical Keyboard", "Silver", "Computer Accessories", 12, 0.8, "Mechanical keyboard with RGB lighting"));
        products.add(new Product("Fitness Tracker", "Blue", "Wearables", 30, 0.05, "Tracks steps, heart rate and sleep"));
        products.add(new Product("Coffee Maker", "Silver", "Kitchen Appliances", 8, 3.0, "Programmable coffee maker"));
        products.add(new Product("Bluetooth Speaker", "Black", "Audio", 18, 0.5, "Portable waterproof speaker"));
        products.add(new Product("Smartwatch", "Gold", "Wearables", 15, 0.08, "Smart watch with health monitoring"));
        products.add(new Product("External SSD", "Black", "Storage", 40, 0.15, "1TB external solid state drive"));
        products.add(new Product("Air Fryer", "Black", "Kitchen Appliances", 15, 5.2, "Oil-free cooking with adjustable temperature"));
        products.add(new Product("Robot Vacuum", "White", "Home Appliances", 8, 3.2, "Smart vacuum with mapping capability"));
        products.add(new Product("Blender", "Red", "Kitchen Appliances", 20, 2.0, "High-power blender for smoothies and soups"));
        products.add(new Product("Denim Jacket", "Blue", "Clothing", 25, 0.7, "Classic denim jacket with metal buttons"));
        products.add(new Product("Running Shoes", "Black/Red", "Footwear", 18, 0.4, "Lightweight running shoes with cushioned sole"));
        products.add(new Product("Winter Coat", "Navy", "Clothing", 12, 1.8, "Water-resistant padded winter coat"));
        products.add(new Product("Yoga Mat", "Purple", "Fitness", 30, 0.9, "Non-slip exercise mat for yoga and pilates"));
        products.add(new Product("Camping Tent", "Green", "Outdoor", 10, 3.5, "4-person waterproof tent for camping"));
        products.add(new Product("Dumbbells Set", "Black", "Fitness", 15, 10.0, "Pair of adjustable dumbbells with case"));
        products.add(new Product("Bluetooth Earbuds", "White", "Audio", 40, 0.05, "Wireless earbuds with noise cancellation"));
        products.add(new Product("Gaming Console", "Black", "Electronics", 7, 3.0, "Next-gen gaming console with controller"));
        products.add(new Product("Portable Projector", "Gray", "Electronics", 9, 1.2, "Mini HD projector for home theater"));
        products.add(new Product("Wireless Charger", "Black", "Accessories", 35, 0.2, "Fast wireless charging pad for smartphones"));
        products.add(new Product("Power Bank", "Silver", "Accessories", 45, 0.3, "20000mAh portable battery charger"));
        products.add(new Product("USB-C Hub", "Gray", "Computer Accessories", 22, 0.15, "7-in-1 USB-C adapter with HDMI and card readers"));
        products.add(new Product("Ergonomic Chair", "Black", "Furniture", 10, 12.0, "Adjustable office chair with lumbar support"));
        products.add(new Product("Smart Notebook", "Blue", "Stationery", 25, 0.4, "Reusable notebook with cloud synchronization"));
        products.add(new Product("Desktop Monitor", "Black", "Electronics", 14, 5.5, "27-inch 4K display with adjustable stand"));
    }
    private void setupWindowListeners() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                saveAllData();
            }
        });
    }

    private void saveAllData() {
        Database.saveUsers(users);
        Database.saveProducts(products);
        Database.saveOrders(completedOrders);
        System.out.println("All data saved successfully");
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {
        setTitle("Shopping App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Welcome", createWelcomePanel());
        tabbedPane.addTab("Login", createLoginPanel());
        tabbedPane.addTab("Register", createRegisterPanel());
        tabbedPane.addTab("Products", createProductsPanel());
        tabbedPane.addTab("Cart", createCartPanel());
        tabbedPane.addTab("Orders", createOrdersPanel());
        tabbedPane.addTab("Favorites", createFavoritesPanel());
        tabbedPane.addTab("Account", createAccountPanel());

        // ChangeListener to auto-refresh panels when switching tabs
        tabbedPane.addChangeListener(e -> {
            JTabbedPane pane = (JTabbedPane) e.getSource();
            int selectedIndex = pane.getSelectedIndex();

            // Appropriate refresh logic based on the selected tab
            switch (selectedIndex) {
                case 3: // Products tab
                    JTable productTable = (JTable) ((JScrollPane) ((JPanel) pane.getComponentAt(3)).getComponent(0)).getViewport().getView();
                    DefaultTableModel model = (DefaultTableModel) productTable.getModel();
                    model.setRowCount(0);

                    for (Product p : products) {
                        model.addRow(new Object[]{
                                p.getProductName(),
                                p.getProductDescription(),
                                p.getProductCategory(),
                                p.getProductColor(),
                                p.getProductWeight() + " kg",
                                p.getProductStockInformation()
                        });
                    }
                    break;

                case 4: // Cart tab
                    JList<String> cartList = (JList<String>) ((JScrollPane) ((JPanel) pane.getComponentAt(4)).getComponent(0)).getViewport().getView();
                    DefaultListModel<String> cartListModel = (DefaultListModel<String>) cartList.getModel();
                    cartListModel.clear();

                    if (currentUser != null) {
                        java.util.Map<Product, Integer> productCounts = new java.util.HashMap<>();
                        for (Product product : currentUser.getOrderedProducts()) {
                            productCounts.put(product, productCounts.getOrDefault(product, 0) + 1);
                        }

                        for (java.util.Map.Entry<Product, Integer> entry : productCounts.entrySet()) {
                            Product product = entry.getKey();
                            int quantity = entry.getValue();

                            cartListModel.addElement(quantity + "x " + product.getProductName() +
                                    " - " + product.getProductDescription() +
                                    " - Category: " + product.getProductCategory() +
                                    " - Color: " + product.getProductColor() +
                                    " - Weight: " + product.getProductWeight() + " kg" +
                                    " - Stock: " + product.getProductStockInformation());
                        }
                    }
                    break;

                case 5: // Orders tab
                    JList<String> ordersList = (JList<String>) ((JScrollPane) ((JPanel) pane.getComponentAt(5)).getComponent(0)).getViewport().getView();
                    DefaultListModel<String> ordersListModel = (DefaultListModel<String>) ordersList.getModel();
                    ordersListModel.clear();

                    if (currentUser != null) {
                        for (Order order : completedOrders) {
                            if (order.getOrderingUser().equals(currentUser)) {
                                Product product = order.getOrderedProduct();
                                CreditCard card = order.getCreditCard();
                                ordersListModel.addElement(product.getProductName() +
                                        " - " + product.getProductDescription() +
                                        " - Category: " + product.getProductCategory() +
                                        " - Color: " + product.getProductColor() +
                                        " - Weight: " + product.getProductWeight() + " kg" +
                                        " - Paid with card ending in " +
                                        card.getCardNumber().substring(card.getCardNumber().length() - 4));
                            }
                        }
                    }
                    break;

                case 6: // Favorites tab
                    JList<String> favoritesList = (JList<String>) ((JScrollPane) ((JPanel) pane.getComponentAt(6)).getComponent(0)).getViewport().getView();
                    DefaultListModel<String> favoritesListModel = (DefaultListModel<String>) favoritesList.getModel();
                    favoritesListModel.clear();

                    if (currentUser != null) {
                        for (Product product : currentUser.getFavoriteProducts()) {
                            favoritesListModel.addElement(product.getProductName() +
                                    " - " + product.getProductDescription() +
                                    " - Category: " + product.getProductCategory() +
                                    " - Color: " + product.getProductColor() +
                                    " - Weight: " + product.getProductWeight() + " kg" +
                                    " - Stock: " + product.getProductStockInformation());
                        }
                    }
                    break;

                case 7: // Account tab
                    if (currentUser != null) {
                        JPanel accountPanel = (JPanel) pane.getComponentAt(7);
                        JPanel userInfoPanel = (JPanel) accountPanel.getComponent(0);

                        ((JLabel) userInfoPanel.getComponent(1)).setText(currentUser.getUsername());
                        ((JLabel) userInfoPanel.getComponent(3)).setText(currentUser.getName() + " " + currentUser.getSurname());
                        ((JLabel) userInfoPanel.getComponent(5)).setText(currentUser.getDateOfBirth());
                        ((JLabel) userInfoPanel.getComponent(7)).setText(currentUser.getEmailAddress());
                        ((JLabel) userInfoPanel.getComponent(9)).setText(currentUser.getHomeAddress());
                        ((JLabel) userInfoPanel.getComponent(11)).setText(currentUser.getWorkAddress());

                        JPanel centerPanel = (JPanel) accountPanel.getComponent(1);
                        JPanel creditCardDisplayPanel = (JPanel) centerPanel.getComponent(0);
                        JScrollPane cardScrollPane = (JScrollPane) creditCardDisplayPanel.getComponent(0);
                        JList<String> cardList = (JList<String>) cardScrollPane.getViewport().getView();
                        DefaultListModel<String> cardListModel = (DefaultListModel<String>) cardList.getModel();

                        cardListModel.clear();
                        for (CreditCard card : currentUser.getCreditCards()) {
                            cardListModel.addElement("Card Number: " + card.getFormattedCardNumber() +
                                    " | Exp: " + card.getExpirationDate() +
                                    " | CVV: " + card.getSecurityCode() +
                                    " | Cardholder: " + card.getCardholderName());
                        }
                    }
                    break;
            }
        });

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        setupWindowListeners();
    }
    private void initializeTheme() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            UIManager.put("control", new Color(35, 35, 35));
            UIManager.put("info", new Color(35, 35, 35));
            UIManager.put("nimbusBase", new Color(25, 25, 25));
            UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
            UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
            UIManager.put("nimbusFocus", new Color(72, 125, 182));
            UIManager.put("nimbusGreen", new Color(0, 138, 0));
            UIManager.put("nimbusInfoBlue", new Color(47, 92, 180));
            UIManager.put("nimbusLightBackground", new Color(45, 45, 45));
            UIManager.put("nimbusOrange", new Color(191, 98, 4));
            UIManager.put("nimbusRed", new Color(169, 46, 34));
            UIManager.put("nimbusSelectedText", Color.WHITE);
            UIManager.put("nimbusSelectionBackground", new Color(72, 125, 182));
            UIManager.put("text", Color.WHITE);

            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 8);
            UIManager.put("Button.margin", new Insets(8, 14, 8, 14)); // Better padding
            UIManager.put("Button.background", new Color(64, 120, 192));
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 14));
            UIManager.put("Button.textShiftOffset", 0);
            UIManager.put("Button.focusPainted", false);

            UIManager.put("TextField.background", new Color(45, 45, 45));
            UIManager.put("TextField.foreground", Color.WHITE);
            UIManager.put("TextField.margin", new Insets(6, 8, 6, 8));
            UIManager.put("TextField.caretForeground", Color.WHITE);
            UIManager.put("PasswordField.background", new Color(45, 45, 45));
            UIManager.put("PasswordField.foreground", Color.WHITE);
            UIManager.put("PasswordField.margin", new Insets(6, 8, 6, 8));

            UIManager.put("Table.background", new Color(35, 35, 35));
            UIManager.put("Table.foreground", Color.WHITE);
            UIManager.put("Table.gridColor", new Color(60, 60, 60));
            UIManager.put("Table.selectionBackground", new Color(72, 125, 182, 180));
            UIManager.put("Table.showGrid", true);
            UIManager.put("Table.intercellSpacing", new Dimension(1, 1));

            UIManager.put("List.background", new Color(35, 35, 35));
            UIManager.put("List.foreground", Color.WHITE);
            UIManager.put("List.selectionBackground", new Color(72, 125, 182, 180));
            UIManager.put("List.selectionForeground", Color.WHITE);

            UIManager.put("TabbedPane.background", new Color(30, 30, 30));
            UIManager.put("TabbedPane.foreground", Color.WHITE);
            UIManager.put("TabbedPane.selectedForeground", Color.WHITE);
            UIManager.put("TabbedPane.selectedBackground", new Color(55, 55, 55));
            UIManager.put("TabbedPane.contentBorderInsets", new Insets(4, 4, 4, 4));
            UIManager.put("TabbedPane.tabAreaInsets", new Insets(4, 4, 0, 4));
            UIManager.put("TabbedPane.font", new Font("SansSerif", Font.BOLD, 14));

            Font defaultFont = new Font("SansSerif", Font.PLAIN, 14);
            UIManager.put("Label.font", defaultFont);
            UIManager.put("TextField.font", defaultFont);
            UIManager.put("Table.font", defaultFont);
            UIManager.put("List.font", defaultFont);
            UIManager.put("ComboBox.font", defaultFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(35, 40, 50),
                        0, h, new Color(25, 30, 40));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
                g2d.dispose();
            }
        };
        panel.setLayout(new BorderLayout(0, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));

        JLabel welcomeLabel = new JLabel("WELCOME TO SHOPPING APP", JLabel.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        welcomeLabel.setForeground(Color.WHITE);
        headerPanel.add(welcomeLabel, BorderLayout.CENTER);

        JLabel subtitleLabel = new JLabel("Your one-stop solution for online shopping", JLabel.CENTER);
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(180, 180, 180));
        headerPanel.add(subtitleLabel, BorderLayout.SOUTH);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);

        JPanel buttonPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(30, 35, 45));
                g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
                g2d.dispose();
            }
        };
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        buttonPanel.setOpaque(false);

        JButton loginButton = createStylishButton("Login to Your Account", new Color(41, 65, 97));
        loginButton.addActionListener(e -> {
            JTabbedPane tabbedPane = (JTabbedPane) SwingUtilities.getAncestorOfClass(
                    JTabbedPane.class, panel);
            if (tabbedPane != null) {
                tabbedPane.setSelectedIndex(1);
            }
        });

        JButton registerButton = createStylishButton("Create a New Account", new Color(45, 75, 55));
        registerButton.addActionListener(e -> {
            JTabbedPane tabbedPane = (JTabbedPane) SwingUtilities.getAncestorOfClass(
                    JTabbedPane.class, panel);
            if (tabbedPane != null) {
                tabbedPane.setSelectedIndex(2);
            }
        });

        JLabel infoLabel = new JLabel("<html><center>Sign in to access your orders, favorites and to explore more!</center></html>", JLabel.CENTER);
        infoLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoLabel.setForeground(new Color(180, 180, 180));

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(infoLabel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        centerWrapper.add(buttonPanel, gbc);

        JLabel footerLabel = new JLabel("© 2025 Shopping App - All Rights Reserved", JLabel.CENTER);
        footerLabel.setForeground(new Color(120, 120, 120));
        footerLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(centerWrapper, BorderLayout.CENTER);
        panel.add(footerLabel, BorderLayout.SOUTH);

        return panel;
    }
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));

        loginPanel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField(15);
        loginPanel.add(usernameField);

        loginPanel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField(15);
        loginPanel.add(passwordField);

        loginPanel.add(new JLabel(""));
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    currentUser = user;
                    JOptionPane.showMessageDialog(this, "Login successful! Welcome " + user.getName());

                    JTabbedPane tabbedPane = (JTabbedPane) SwingUtilities.getAncestorOfClass(
                            JTabbedPane.class, panel);
                    if (tabbedPane != null) {
                        tabbedPane.setSelectedIndex(3);
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        });
        loginPanel.add(loginButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(loginPanel, gbc);

        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel registerPanel = new JPanel(new GridLayout(9, 2, 5, 5));
        registerPanel.setBorder(BorderFactory.createTitledBorder("Register"));

        registerPanel.add(new JLabel("Username:"));
        JTextField regUsernameField = new JTextField(15);
        registerPanel.add(regUsernameField);

        registerPanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField(15);
        registerPanel.add(nameField);

        registerPanel.add(new JLabel("Surname:"));
        JTextField surnameField = new JTextField(15);
        registerPanel.add(surnameField);

        registerPanel.add(new JLabel("Date of Birth:"));
        JTextField dobField = new JTextField(15);
        registerPanel.add(dobField);

        registerPanel.add(new JLabel("Password:"));
        JPasswordField regPasswordField = new JPasswordField(15);
        registerPanel.add(regPasswordField);

        registerPanel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField(15);
        registerPanel.add(emailField);

        registerPanel.add(new JLabel("Home Address:"));
        JTextField homeAddressField = new JTextField(15);
        registerPanel.add(homeAddressField);

        registerPanel.add(new JLabel("Work Address:"));
        JTextField workAddressField = new JTextField(15);
        registerPanel.add(workAddressField);

        registerPanel.add(new JLabel(""));
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            User newUser = new User(
                    regUsernameField.getText(),
                    nameField.getText(),
                    surnameField.getText(),
                    dobField.getText(),
                    new String(regPasswordField.getPassword()),
                    emailField.getText(),
                    homeAddressField.getText(),
                    workAddressField.getText()
            );
            users.add(newUser);
            JOptionPane.showMessageDialog(this, "Registration successful! You can now login.");
            saveAllData();

            JTabbedPane tabbedPane = (JTabbedPane) SwingUtilities.getAncestorOfClass(
                    JTabbedPane.class, panel);
            if (tabbedPane != null) {
                tabbedPane.setSelectedIndex(1);
            }
        });
        registerPanel.add(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(registerPanel, gbc);

        return panel;
    }

    private JPanel createProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"Name", "Description", "Category", "Color", "Weight", "Stock"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Product p : products) {
            tableModel.addRow(new Object[]{
                    p.getProductName(),
                    p.getProductDescription(),
                    p.getProductCategory(),
                    p.getProductColor(),
                    p.getProductWeight() + " kg",
                    p.getProductStockInformation()
            });
        }

        JTable productTable = new JTable(tableModel);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(productTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) productTable.getModel();
            model.setRowCount(0);

            for (Product p : products) {
                model.addRow(new Object[]{
                        p.getProductName(),
                        p.getProductDescription(),
                        p.getProductCategory(),
                        p.getProductColor(),
                        p.getProductWeight() + " kg",
                        p.getProductStockInformation()
                });
            }
        });
        buttonsPanel.add(refreshButton);

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(this, "Please login first");
                return;
            }

            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0 && selectedRow < products.size()) {
                Product selectedProduct = products.get(selectedRow);

                String quantityStr = JOptionPane.showInputDialog(
                        this,
                        "How many " + selectedProduct.getProductName() + "(s) do you want to add to cart?",
                        "Add to Cart",
                        JOptionPane.QUESTION_MESSAGE
                );

                if (quantityStr == null) {
                    return;
                }

                try {
                    int quantity = Integer.parseInt(quantityStr);
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(this, "Please enter a positive number");
                        return;
                    }

                    if (quantity > selectedProduct.getProductStockInformation()) {
                        JOptionPane.showMessageDialog(this,
                                "Not enough stock. Available: " + selectedProduct.getProductStockInformation());
                        return;
                    }

                    for (int i = 0; i < quantity; i++) {
                        currentUser.order(selectedProduct);
                    }

                    JOptionPane.showMessageDialog(this,
                            quantity + " " + selectedProduct.getProductName() + "(s) added to cart");
                    saveAllData();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid number");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a product");
            }
        });
        buttonsPanel.add(addToCartButton);

        JButton addToFavoritesButton = new JButton("Add to Favorites");
        addToFavoritesButton.addActionListener(e -> {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(this, "Please login first");
                return;
            }

            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0 && selectedRow < products.size()) {
                Product selectedProduct = products.get(selectedRow);

                boolean alreadyInFavorites = currentUser.getFavoriteProducts().contains(selectedProduct);

                currentUser.favorite(selectedProduct);

                if (alreadyInFavorites) {
                    JOptionPane.showMessageDialog(this, "Product is already in favorites: " + selectedProduct.getProductName());
                } else {
                    JOptionPane.showMessageDialog(this, "Product added to favorites: " + selectedProduct.getProductName());
                }
                saveAllData();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a product");
            }
        });
        buttonsPanel.add(addToFavoritesButton);

        panel.add(buttonsPanel, BorderLayout.SOUTH);

        return panel;
    }
    
    private JPanel createCartPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        DefaultListModel<String> cartListModel = new DefaultListModel<>();
        JList<String> cartList = new JList<>(cartListModel);
        JScrollPane scrollPane = new JScrollPane(cartList);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonsPanel = new JPanel();

        JButton refreshButton = new JButton("Refresh Cart");
        refreshButton.addActionListener(e -> {
            cartListModel.clear();
            if (currentUser != null) {
                java.util.Map<Product, Integer> productCounts = new java.util.HashMap<>();

                for (Product product : currentUser.getOrderedProducts()) {
                    productCounts.put(product, productCounts.getOrDefault(product, 0) + 1);
                }

                for (java.util.Map.Entry<Product, Integer> entry : productCounts.entrySet()) {
                    Product product = entry.getKey();
                    int quantity = entry.getValue();

                    cartListModel.addElement(quantity + "x " + product.getProductName() +
                            " - " + product.getProductDescription() +
                            " - Category: " + product.getProductCategory() +
                            " - Color: " + product.getProductColor() +
                            " - Weight: " + product.getProductWeight() + " kg" +
                            " - Stock: " + product.getProductStockInformation());
                }
            }
        });
        buttonsPanel.add(refreshButton);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(this, "Please login first");
                return;
            }

            if (currentUser.getOrderedProducts().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Your cart is empty");
                return;
            }

            if (currentUser.getCreditCards().isEmpty()) {
                JOptionPane.showMessageDialog(this, "You can't order without adding a credit card. Please add a card.");
                return;
            }

            java.util.Map<Product, Integer> productCounts = new java.util.HashMap<>();

            for (Product product : currentUser.getOrderedProducts()) {
                productCounts.put(product, productCounts.getOrDefault(product, 0) + 1);
            }

            boolean insufficientStock = false;
            for (java.util.Map.Entry<Product, Integer> entry : productCounts.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();

                if (quantity > product.getProductStockInformation()) {
                    JOptionPane.showMessageDialog(this,
                            "Insufficient stock for " + product.getProductName() +
                                    ". Available: " + product.getProductStockInformation() +
                                    ", Requested: " + quantity);
                    insufficientStock = true;
                    break;
                }
            }

            if (insufficientStock) {
                return;
            }

            CreditCard selectedCard;
            if (currentUser.getCreditCards().size() == 1) {
                selectedCard = currentUser.getCreditCards().get(0);
            } else {
                String[] cardOptions = new String[currentUser.getCreditCards().size()];
                for (int i = 0; i < currentUser.getCreditCards().size(); i++) {
                    CreditCard card = currentUser.getCreditCards().get(i);
                    cardOptions[i] = "Card ending in " +
                            card.getCardNumber().substring(card.getCardNumber().length() - 4) +
                            " (Exp: " + card.getExpirationDate() + ")";
                }

                int selectedOption = JOptionPane.showOptionDialog(
                        this,
                        "Select a credit card for payment:",
                        "Payment Method",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        cardOptions,
                        cardOptions[0]
                );

                if (selectedOption == JOptionPane.CLOSED_OPTION) {
                    return;
                }

                selectedCard = currentUser.getCreditCards().get(selectedOption);
            }

            ArrayList<Product> productsToProcess = new ArrayList<>(currentUser.getOrderedProducts());

            currentUser.getOrderedProducts().clear();
            cartListModel.clear();

            for (Product product : productsToProcess) {
                product.setProductStockInformation(product.getProductStockInformation() - 1);

                Order order = new Order(currentUser, product, selectedCard);
                completedOrders.add(order);
            }

            saveAllData();
            productListModel.clear();
            for (Product product : products) {
                productListModel.addElement(product.getProductName() + " - " + product.getProductDescription() +
                        " - " + product.getProductCategory() + " - Stock: " + product.getProductStockInformation());
            }

            JOptionPane.showMessageDialog(this, "Order placed successfully with card ending in " +
                    selectedCard.getCardNumber().substring(selectedCard.getCardNumber().length() - 4));
            saveAllData();
        });
        buttonsPanel.add(checkoutButton);


        JButton removeItemButton = new JButton("Remove Selected Item");
        removeItemButton.addActionListener(e -> {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(panel, "Please login first");
                return;
            }

            int selectedIndex = cartList.getSelectedIndex();
            if (selectedIndex < 0 || cartList.getModel().getSize() == 0) {
                JOptionPane.showMessageDialog(panel, "Please select an item to remove");
                return;
            }

            java.util.Map<Product, Integer> productCounts = new java.util.HashMap<>();
            for (Product product : currentUser.getOrderedProducts()) {
                productCounts.put(product, productCounts.getOrDefault(product, 0) + 1);
            }

            Product selectedProduct = null;
            int index = 0;
            for (java.util.Map.Entry<Product, Integer> entry : productCounts.entrySet()) {
                if (index == selectedIndex) {
                    selectedProduct = entry.getKey();
                    break;
                }
                index++;
            }

            if (selectedProduct != null) {
                int currentQuantity = productCounts.get(selectedProduct);

                String quantityStr = JOptionPane.showInputDialog(
                        panel,
                        "How many " + selectedProduct.getProductName() + "(s) do you want to remove? (1-" + currentQuantity + ")",
                        "Remove Items",
                        JOptionPane.QUESTION_MESSAGE
                );

                try {
                    int quantityToRemove = Integer.parseInt(quantityStr);
                    if (quantityToRemove <= 0) {
                        JOptionPane.showMessageDialog(panel, "Quantity must be greater than zero");
                        return;
                    }
                    if (quantityToRemove > currentQuantity) {
                        JOptionPane.showMessageDialog(panel, "Cannot remove more than you have in cart (" + currentQuantity + ")");
                        return;
                    }

                    int removed = 0;
                    for (int i = currentUser.getOrderedProducts().size() - 1; i >= 0 && removed < quantityToRemove; i--) {
                        if (currentUser.getOrderedProducts().get(i).equals(selectedProduct)) {
                            currentUser.getOrderedProducts().remove(i);
                            removed++;
                        }
                    }

                    refreshButton.doClick();
                    JOptionPane.showMessageDialog(panel, quantityToRemove + " " +
                            selectedProduct.getProductName() + "(s) removed from cart");
                    saveAllData();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Please enter a valid number");
                } catch (NullPointerException ex) {
                }
            }
        });
        buttonsPanel.add(removeItemButton);

        JButton clearCartButton = new JButton("Clear Cart");
        clearCartButton.addActionListener(e -> {
            if (currentUser == null || currentUser.getOrderedProducts().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Cart is already empty");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(panel,
                    "Are you sure you want to clear your entire cart?",
                    "Confirm Clear Cart", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                currentUser.getOrderedProducts().clear();
                cartListModel.clear();
                JOptionPane.showMessageDialog(panel, "Cart cleared successfully");
                saveAllData();
            }
        });
        buttonsPanel.add(clearCartButton);

        panel.add(buttonsPanel, BorderLayout.SOUTH);
        
        return panel;
    }

    private JPanel createFavoritesPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        DefaultListModel<String> favoritesListModel = new DefaultListModel<>();
        JList<String> favoritesList = new JList<>(favoritesListModel);
        JScrollPane scrollPane = new JScrollPane(favoritesList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();

        JButton refreshButton = new JButton("Refresh Favorites");
        refreshButton.addActionListener(e -> {
            favoritesListModel.clear();
            if (currentUser != null) {
                for (Product product : currentUser.getFavoriteProducts()) {
                    favoritesListModel.addElement(product.getProductName() +
                            " - " + product.getProductDescription() +
                            " - Category: " + product.getProductCategory() +
                            " - Color: " + product.getProductColor() +
                            " - Weight: " + product.getProductWeight() + " kg" +
                            " - Stock: " + product.getProductStockInformation());
                }
            }
        });
        buttonsPanel.add(refreshButton);

        JButton removeButton = new JButton("Remove from Favorites");
        removeButton.addActionListener(e -> {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(panel, "Please login first");
                return;
            }

            int selectedIndex = favoritesList.getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < currentUser.getFavoriteProducts().size()) {
                currentUser.getFavoriteProducts().remove(selectedIndex);
                favoritesListModel.remove(selectedIndex);
                JOptionPane.showMessageDialog(panel, "Item removed from favorites");
                saveAllData();
            } else {
                JOptionPane.showMessageDialog(panel, "Please select an item to remove");
            }
        });
        buttonsPanel.add(removeButton);

        panel.add(buttonsPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createAccountPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel userInfoPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        userInfoPanel.setBorder(BorderFactory.createTitledBorder("User Information"));

        JLabel usernameLabel = new JLabel("Username: ");
        JLabel usernameValue = new JLabel();
        userInfoPanel.add(usernameLabel);
        userInfoPanel.add(usernameValue);

        JLabel nameLabel = new JLabel("Name: ");
        JLabel nameValue = new JLabel();
        userInfoPanel.add(nameLabel);
        userInfoPanel.add(nameValue);

        JLabel dbLabel = new JLabel("Date of Birth: ");
        JLabel dbValue = new JLabel();
        userInfoPanel.add(dbLabel);
        userInfoPanel.add(dbValue);

        JLabel emailLabel = new JLabel("Email: ");
        JLabel emailValue = new JLabel();
        userInfoPanel.add(emailLabel);
        userInfoPanel.add(emailValue);

        JLabel homeAddressLabel = new JLabel("Home Address: ");
        JLabel homeAddressValue = new JLabel();
        userInfoPanel.add(homeAddressLabel);
        userInfoPanel.add(homeAddressValue);

        JLabel workAddressLabel = new JLabel("Work Address: ");
        JLabel workAddressValue = new JLabel();
        userInfoPanel.add(workAddressLabel);
        userInfoPanel.add(workAddressValue);


        JPanel creditCardDisplayPanel = new JPanel(new BorderLayout());
        creditCardDisplayPanel.setBorder(BorderFactory.createTitledBorder("Your Credit Cards"));

        DefaultListModel<String> cardListModel = new DefaultListModel<>();
        JList<String> cardList = new JList<>(cardListModel);
        JScrollPane cardScrollPane = new JScrollPane(cardList);
        creditCardDisplayPanel.add(cardScrollPane, BorderLayout.CENTER);

        JPanel creditCardActionPanel = new JPanel(new FlowLayout());
        creditCardActionPanel.setBorder(BorderFactory.createTitledBorder("Credit Card Actions"));

        JButton addCardButton = new JButton("Add Random Credit Card");
        addCardButton.addActionListener(e -> {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(panel, "Please login first");
                return;
            }

            CreditCard card = new CreditCard(currentUser);
            currentUser.getCreditCards().add(card);

            cardListModel.addElement("Card Number: " + card.getFormattedCardNumber() +
                    " | Exp: " + card.getExpirationDate() +
                    " | CVV: " + card.getSecurityCode() +
                    " | Cardholder: " + card.getCardholderName());

            JOptionPane.showMessageDialog(panel,
                    "Credit card added successfully!\nCard number: " + card.getFormattedCardNumber() +
                            "\nExpiry: " + card.getExpirationDate() +
                            "\nCVV: " + card.getSecurityCode() +
                            "\nCardholder: " + card.getCardholderName());
            saveAllData();
        });
        creditCardActionPanel.add(addCardButton);

        JButton addManualCardButton = new JButton("Enter Card Manually");
        addManualCardButton.addActionListener(e -> {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(panel, "Please login first");
                return;
            }

            JPanel cardForm = new JPanel(new GridLayout(0, 2, 5, 5));

            JTextField cardNumberField = new JTextField(16);
            cardForm.add(new JLabel("Card Number:"));
            cardForm.add(cardNumberField);

            JTextField expiryField = new JTextField("MM/YY");
            cardForm.add(new JLabel("Expiry Date:"));
            cardForm.add(expiryField);

            JTextField cvvField = new JTextField(3);
            cardForm.add(new JLabel("CVV:"));
            cardForm.add(cvvField);

            JTextField nameField = new JTextField(currentUser.getName() + " " + currentUser.getSurname());
            cardForm.add(new JLabel("Cardholder Name:"));
            cardForm.add(nameField);

            JLabel noteLabel = new JLabel("Click Cancel to go back without adding a card");
            noteLabel.setFont(new Font(noteLabel.getFont().getName(), Font.ITALIC, noteLabel.getFont().getSize()));
            JPanel notePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            notePanel.add(noteLabel);

            JPanel mainFormPanel = new JPanel(new BorderLayout());
            mainFormPanel.add(cardForm, BorderLayout.CENTER);
            mainFormPanel.add(notePanel, BorderLayout.SOUTH);

            int result = JOptionPane.showConfirmDialog(panel, mainFormPanel,
                    "Enter Card Details", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String cardNumber = cardNumberField.getText().trim();
                String expiry = expiryField.getText().trim();
                String cvv = cvvField.getText().trim();
                String name = nameField.getText().trim();

                if (cardNumber.isEmpty() || expiry.isEmpty() || cvv.isEmpty() || name.isEmpty() || expiry.equals("MM/YY")) {
                    JOptionPane.showMessageDialog(panel, "All fields are required. Please fill them out.",
                            "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                CreditCard card = new CreditCard(currentUser);
                card.setCardNumber(cardNumber);
                card.setExpirationDate(expiry);
                card.setSecurityCode(cvv);
                card.setCardholderName(name);

                currentUser.getCreditCards().add(card);

                cardListModel.addElement("Card: " + card.getFormattedCardNumber() +
                        " | Exp: " + card.getExpirationDate() +
                        " | CVV: " + card.getSecurityCode());

                JOptionPane.showMessageDialog(panel, "Credit card added successfully!");
                saveAllData();
            }
        });
        creditCardActionPanel.add(addManualCardButton);


        JButton deleteCardButton = new JButton("Delete Credit Card");
        deleteCardButton.addActionListener(e -> {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(panel, "Please login first");
                return;
            }

            int selectedIndex = cardList.getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < currentUser.getCreditCards().size()) {
                int confirm = JOptionPane.showConfirmDialog(panel,
                        "Are you sure you want to delete this credit card?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    currentUser.getCreditCards().remove(selectedIndex);
                    cardListModel.remove(selectedIndex);
                    JOptionPane.showMessageDialog(panel, "Credit card removed successfully!");
                    saveAllData();
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a credit card to delete");
            }
        });
        creditCardActionPanel.add(deleteCardButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            if (currentUser != null) {
                usernameValue.setText(currentUser.getUsername());
                nameValue.setText(currentUser.getName() + " " + currentUser.getSurname());
                dbValue.setText(currentUser.getDateOfBirth());
                emailValue.setText(currentUser.getEmailAddress());
                homeAddressValue.setText(currentUser.getHomeAddress());
                workAddressValue.setText(currentUser.getWorkAddress());

                cardListModel.clear();
                for (CreditCard card : currentUser.getCreditCards()) {
                    cardListModel.addElement("Card Number: " + card.getFormattedCardNumber() +
                            " | Exp: " + card.getExpirationDate() +
                            " | CVV: " + card.getSecurityCode() +
                            " | Cardholder: " + card.getCardholderName());
                }
            } else {
                usernameValue.setText("Not logged in");
                usernameValue.setText("Not logged in");
                nameValue.setText("");
                dbValue.setText("");
                emailValue.setText("");
                homeAddressValue.setText("");
                workAddressValue.setText("");
                cardListModel.clear();
            }
        });

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(creditCardDisplayPanel, BorderLayout.CENTER);
        centerPanel.add(creditCardActionPanel, BorderLayout.SOUTH);

        panel.add(userInfoPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(refreshButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createOrdersPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        DefaultListModel<String> ordersListModel = new DefaultListModel<>();
        JList<String> ordersList = new JList<>(ordersListModel);
        JScrollPane scrollPane = new JScrollPane(ordersList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();

        JButton refreshButton = new JButton("Refresh Orders");
        refreshButton.addActionListener(e -> {
            ordersListModel.clear();
            if (currentUser != null) {
                for (Order order : completedOrders) {
                    if (order.getOrderingUser().equals(currentUser)) {
                        Product product = order.getOrderedProduct();
                        CreditCard card = order.getCreditCard();
                        ordersListModel.addElement(product.getProductName() +
                                " - " + product.getProductDescription() +
                                " - Category: " + product.getProductCategory() +
                                " - Color: " + product.getProductColor() +
                                " - Weight: " + product.getProductWeight() + " kg" +
                                " - Paid with card ending in " +
                                card.getCardNumber().substring(card.getCardNumber().length() - 4));
                    }
                }
            }
        });
        buttonsPanel.add(refreshButton);

        JButton cancelOrderButton = new JButton("Cancel Selected Order");
        cancelOrderButton.addActionListener(e -> {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(panel, "Please login first");
                return;
            }

            int selectedIndex = ordersList.getSelectedIndex();

            ArrayList<Order> userOrders = new ArrayList<>();
            for (Order order : completedOrders) {
                if (order.getOrderingUser().equals(currentUser)) {
                    userOrders.add(order);
                }
            }

            if (selectedIndex >= 0 && selectedIndex < userOrders.size()) {
                int confirm = JOptionPane.showConfirmDialog(panel,
                        "Are you sure you want to cancel this order?",
                        "Confirm Cancellation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    Order orderToCancel = userOrders.get(selectedIndex);

                    Product product = orderToCancel.getOrderedProduct();
                    product.setProductStockInformation(product.getProductStockInformation() + 1);

                    completedOrders.remove(orderToCancel);

                    ordersListModel.remove(selectedIndex);
                    JOptionPane.showMessageDialog(panel, "Order cancelled successfully");
                    saveAllData();
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Please select an order to cancel");
            }
        });
        buttonsPanel.add(cancelOrderButton);

        panel.add(buttonsPanel, BorderLayout.SOUTH);

        return panel;
    }
    private JButton createStylishButton(String text, Color baseColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();

                GradientPaint gp = new GradientPaint(
                        0, 0, baseColor,
                        0, h, baseColor.darker());

                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, w-1, h-1, 10, 10);

                g2d.setColor(new Color(255, 255, 255, 70));
                g2d.drawRoundRect(0, 0, w-1, h-1, 10, 10);

                ButtonModel model = getModel();
                if (model.isPressed()) {
                    g2d.setColor(new Color(0, 0, 0, 50));
                    g2d.fillRoundRect(0, 0, w-1, h-1, 10, 10);
                } else if (model.isRollover()) {
                    g2d.setColor(new Color(255, 255, 255, 50));
                    g2d.fillRoundRect(0, 0, w-1, h-1, 10, 10);
                }

                g2d.dispose();

                super.paintComponent(g);
            }
        };

        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(300, 50));

        return button;
    }

    /**
     * @param args the command line arguments
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShoppingAppUI().setVisible(true);
            }
        });
    }
}