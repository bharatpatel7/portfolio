package ePortfolio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.PrintWriter;
import java.io.IOException;



/**
 * Represents the main GUI for the ePortfolio application
 * Aurthor: Bharat Garsondiya
 * Date: 2024-11-29
 * ID: 1303213
 */
//MainGUI class
public class MainGUI extends JFrame {

        private Portfolio portfolio;
        private CardLayout cardLayout;
        private JPanel mainPanel;
        private JTextField totalGainField;

    // Constructor
    public MainGUI(String fileName) {

        portfolio = new Portfolio(fileName); 
        initUI();

    }

    //Method to initialize the user interface
    private void initUI() {

        // Set the title, size, default close operation, and location of the frame
        setTitle("ePortfolio");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a main panel and card layout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add the panels to the main panel
        mainPanel.add(createMainPanel(), "Main");
        mainPanel.add(createBuyPanel(), "Buy");
        mainPanel.add(createSellPanel(), "Sell");
        mainPanel.add(createSearchPanel(), "Search");
        mainPanel.add(createUpdatePricesPanel(), "Update Prices");
        mainPanel.add(createViewGainPanel(), "View Gain");

        // Add the main panel to the frame
        add(mainPanel);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu commandsMenu = new JMenu("Commands");

        // Create the menu items
        JMenuItem buyMenuItem = new JMenuItem("Buy");
        JMenuItem sellMenuItem = new JMenuItem("Sell");
        JMenuItem updateMenuItem = new JMenuItem("Update Prices");
        JMenuItem getGainMenuItem = new JMenuItem("Get Gain");
        JMenuItem searchMenuItem = new JMenuItem("Search");
        JMenuItem quitMenuItem = new JMenuItem("Quit");
    
        // Add action listeners to the menu items
        /**  
         * @param e ActionEvent
         * @return void
         */
        buyMenuItem.addActionListener (new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                cardLayout.show(mainPanel, "Buy");

                }
        });

        /**  
         * @param e ActionEvent
         * @return void
         */
        // Sell menu item action listener
        sellMenuItem.addActionListener (new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                cardLayout.show(mainPanel, "Sell");

                }
        });

        // Update menu item action listener
        /**  
         * @param e ActionEvent
         * @return void
         */
        updateMenuItem.addActionListener (new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                cardLayout.show(mainPanel, "Update Prices");

                }
        });

        // Get gain menu item action listener
        /**  
         * @param e ActionEvent
         * @return void
         */
        getGainMenuItem.addActionListener (new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                cardLayout.show(mainPanel, "View Gain");
                JTextArea individualGainArea = new JTextArea(10, 30);
                updateTotalGain(individualGainArea);

                }
        });

        // Search menu item action listener
        /**  
         * @param e ActionEvent
         * @return void
         */
        searchMenuItem.addActionListener (new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                cardLayout.show(mainPanel, "Search");

                }
        });

        // Quit menu item action listener
        /**  
         * @param e ActionEvent
         * @return void
         */
        quitMenuItem.addActionListener (new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                portfolio.saveInvestments(portfolio.getFileName());
                System.exit(0);
                
                }
        });

        // Add the menu items to the commands menu
        commandsMenu.add(buyMenuItem);
        commandsMenu.add(sellMenuItem);
        commandsMenu.add(updateMenuItem);
        commandsMenu.add(getGainMenuItem);
        commandsMenu.add(searchMenuItem);
        commandsMenu.add(quitMenuItem);

        // Add the commands menu to the menu bar
        menuBar.add(commandsMenu);
        setJMenuBar(menuBar);

        // Add a window listener to save the investments when the window is closed
        /**  
         * @param windowEvent WindowEvent
         * @return void
         */
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                portfolio.saveInvestments(portfolio.getFileName());

            }
        });
}

        //Method to create the main panel
        /**
         * @return JPanel
         */
        private JPanel createMainPanel() {

                // Create a panel with a border layout
                JPanel panel = new JPanel(new BorderLayout());
                JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'><h1>Welcome to ePortfolio</h1><p>Choose a command from the “Commands” menu to buy or sell an investment, update prices for all investments, get gain for the portfolio, search for relevant investments, or quit the program.</p></div></html>", JLabel.CENTER);
                panel.add(welcomeLabel, BorderLayout.CENTER);
                return panel;

        }

        //Method to create the buy panel
        /**
         * @return JPanel
         */  
        private JPanel createBuyPanel() {

        // Create a panel with a border layout
        JPanel panel = new JPanel(new BorderLayout());

        // Create a left panel with a border layout
        JPanel leftPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Buying an Investment", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        leftPanel.add(titleLabel, BorderLayout.NORTH);

        // Create an input panel with a grid layout
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        JLabel typeLabel = new JLabel("Type:");
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Stock", "MutualFund"});
        JLabel symbolLabel = new JLabel("Symbol:");
        JTextField symbolField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        // Set the size of the text fields
        inputPanel.add(typeLabel);
        inputPanel.add(typeComboBox);
        inputPanel.add(symbolLabel);
        inputPanel.add(symbolField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);

        // Add the input panel to the left panel
        leftPanel.add(inputPanel, BorderLayout.CENTER);

        // Create a right panel with a grid layout
        JPanel rightPanel = new JPanel(new GridLayout(2, 1));
        JButton resetButton = new JButton("Reset");
        JButton buyButton = new JButton("Buy");

        // Set the size of the buttons
        rightPanel.add(resetButton);
        rightPanel.add(buyButton);

        // Add the right panel to the main panel
        JTextArea messageArea = new JTextArea(5, 20);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);

        // Add the panels to the main panel
        panel.add(leftPanel, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);
        panel.add(scrollPane, BorderLayout.SOUTH);

        // Reset button action listener
        /**  
         * @param e ActionEvent
         * @return void
         */
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                typeComboBox.setSelectedIndex(0);
                symbolField.setText("");
                nameField.setText("");
                quantityField.setText("");
                priceField.setText("");
                messageArea.setText("");

            }
        });

        // Buy button action listener
        /**  
         * @param e ActionEvent
         * @return void
         */
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String type = (String) typeComboBox.getSelectedItem();
                    String symbol = symbolField.getText();
                    String name = nameField.getText();
                    int quantity = Integer.parseInt(quantityField.getText());
                    double price = Double.parseDouble(priceField.getText());

                    if (type.equals("Stock")) {

                        portfolio.buyStock(symbol, name, quantity, price);

                    } else {

                        portfolio.buyMutualFund(symbol, name, quantity, price);

                    }

                    messageArea.setText("Successfully Bought " + quantity + " " + name + " " + type + " at $" + price + " each");

                } catch (Exception ex) {
                        
                    messageArea.setText("Error: " + ex.getMessage());

                }
            }
        });

        return panel;
        }

        //Method to create the sell panel
        /**
         * @return JPanel
         */
        private JPanel createSellPanel() {

        // Create a panel with a border layout
        JPanel panel = new JPanel(new BorderLayout());

        // Create a left panel with a border layout
        JPanel leftPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Selling an Investment", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        leftPanel.add(titleLabel, BorderLayout.NORTH);

        // Create an input panel with a grid layout
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JLabel symbolLabel = new JLabel("Symbol:");
        JTextField symbolField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        // Set the size of the text fields
        Dimension textFieldSize = new Dimension(100, 20);
        symbolField.setPreferredSize(textFieldSize);
        quantityField.setPreferredSize(textFieldSize);
        priceField.setPreferredSize(textFieldSize);

        // Add the text fields to the input panel
        inputPanel.add(symbolLabel);
        inputPanel.add(symbolField);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);

        // Add the input panel to the left panel
        leftPanel.add(inputPanel, BorderLayout.CENTER);

        // Create a right panel with a grid layout
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JButton resetButton = new JButton("Reset");
        JButton sellButton = new JButton("Sell");

        // Set the size of the buttons
        Dimension buttonSize = new Dimension(100, 30);
        resetButton.setPreferredSize(buttonSize);
        sellButton.setPreferredSize(buttonSize);

        // Add the buttons to the right panel
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(resetButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(sellButton);
        rightPanel.add(Box.createVerticalGlue());

        // Add the right panel to the main panel
        JTextArea messageArea = new JTextArea(5, 20);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);

        // Add the panels to the main panel
        panel.add(leftPanel, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);
        panel.add(scrollPane, BorderLayout.SOUTH);

        // Reset button action listener
        /**  
         * @param e ActionEvent
         * @return void
         */
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                symbolField.setText("");
                quantityField.setText("");
                priceField.setText("");
                messageArea.setText("");

            }
        });

        // Sell button action listener
        /**  
         * @param e ActionEvent
         * @return void
         */
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String symbol = symbolField.getText();
                    int quantity = Integer.parseInt(quantityField.getText());
                    double price = Double.parseDouble(priceField.getText());

                    portfolio.sellStock(symbol, quantity, price);

                    messageArea.setText("Successfully Sold " + quantity + " " + symbol + " at $" + price + " each");

                } catch (Exception ex) {

                    messageArea.setText("Error: " + ex.getMessage());

                }
            }
        });

        return panel;
    }

        //Method to create the search panel
        /**
         * @return JPanel
         */
        private JPanel createSearchPanel() {

        // Create a panel with a border layout
        JPanel panel = new JPanel(new BorderLayout());

        // Create a top panel with a border layout
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Searching for an Investment", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        // Create an input panel with a grid layout
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        JLabel symbolLabel = new JLabel("Symbol:");
        JTextField symbolField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel lowPriceLabel = new JLabel("Low Price:");
        JTextField lowPriceField = new JTextField();
        JLabel highPriceLabel = new JLabel("High Price:");
        JTextField highPriceField = new JTextField();

        // Set the size of the text fields
        inputPanel.add(symbolLabel);
        inputPanel.add(symbolField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(lowPriceLabel);
        inputPanel.add(lowPriceField);
        inputPanel.add(highPriceLabel);
        inputPanel.add(highPriceField);

        // Add the input panel to the top panel
        topPanel.add(inputPanel, BorderLayout.CENTER);

        // Create a right panel with a grid layout
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JButton resetButton = new JButton("Reset");
        JButton searchButton = new JButton("Search");

        // Set the size of the buttons
        Dimension buttonSize = new Dimension(100, 30);
        resetButton.setPreferredSize(buttonSize);
        searchButton.setPreferredSize(buttonSize);

        // Add the buttons to the right panel
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(resetButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(searchButton);
        rightPanel.add(Box.createVerticalGlue());

        // Create a bottom panel with a border layout
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JLabel searchResultsLabel = new JLabel("Search Results:");
        JTextArea searchResultArea = new JTextArea(10, 30);
        searchResultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(searchResultArea);

        // Add the search results label and text area to the bottom panel
        bottomPanel.add(searchResultsLabel, BorderLayout.NORTH);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the panels to the main panel
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(rightPanel, BorderLayout.EAST);
        panel.add(bottomPanel, BorderLayout.CENTER);

        // Reset button action listener
        /**  
         * @param e ActionEvent
         * @return void
         */
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                symbolField.setText("");
                nameField.setText("");
                lowPriceField.setText("");
                highPriceField.setText("");
                searchResultArea.setText("");

            }
        });

        // Search button action listener
        /**  
         * @param e ActionEvent
         * @return void
         */
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Search for the investment
                try {

                    String symbol = symbolField.getText();
                    String name = nameField.getText();
                    String lowPrice = lowPriceField.getText();
                    String highPrice = highPriceField.getText();
                    String priceRange = lowPrice + "-" + highPrice;

                    String results = portfolio.search(symbol, name, priceRange);
                    searchResultArea.setText(results);

                } catch (Exception ex) {

                    searchResultArea.setText("Error: " + ex.getMessage());

                }
            }
        });

        return panel;
    }

        //Method to create the update prices panel
        /**
         * @return JPanel
         */
        private JPanel createUpdatePricesPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        // Create a left panel with a border layout
        JPanel leftPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Updating investment", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        leftPanel.add(titleLabel, BorderLayout.NORTH);

        // Create an input panel with a grid layout
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JLabel symbolLabel = new JLabel("Symbol:");
        JTextField symbolField = new JTextField();
        symbolField.setEditable(false);
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        nameField.setEditable(false);
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        // Set the size of the text fields
        inputPanel.add(symbolLabel);
        inputPanel.add(symbolField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);

        // Add the input panel to the left panel
        leftPanel.add(inputPanel, BorderLayout.CENTER);

        // Create a right panel with a grid layout
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JButton prevButton = new JButton("Prev");
        JButton nextButton = new JButton("Next");
        JButton saveButton = new JButton("Save");

        // Set the size of the buttons
        Dimension buttonSize = new Dimension(100, 30);
        prevButton.setPreferredSize(buttonSize);
        nextButton.setPreferredSize(buttonSize);
        saveButton.setPreferredSize(buttonSize);

        // Add the buttons to the right panel
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(prevButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(nextButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(saveButton);
        rightPanel.add(Box.createVerticalGlue());

        // Add the right panel to the main panel
        JTextArea messageArea = new JTextArea(5, 20);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);

        // Add the panels to the main panel
        panel.add(leftPanel, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);
        panel.add(scrollPane, BorderLayout.SOUTH);

        // Prev button action listener
        final int[] currentIndex = {0};

        /**  
         * @param e ActionEvent
         * @return void
         */
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (currentIndex[0] > 0) {

                        currentIndex[0] --;
                        updateFields();

                }

            }

            //Method to update fields
            private void updateFields() {
                
                Investment investment = portfolio.getInvestment(currentIndex[0]);
                symbolField.setText(investment.getSymbol());
                nameField.setText(investment.getName());
                priceField.setText(String.valueOf(investment.getPrice()));
                messageArea.setText("");

            }
        });

        // Next button action listener
        /**  
         * @param e ActionEvent
         * @return void
         */
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (currentIndex[0] < portfolio.getInvestments().size() - 1) {
                        currentIndex[0] ++;
                        updateFields();

                }
            }
                //Method to update fields
                private void updateFields() {

                        Investment investment = portfolio.getInvestment(currentIndex[0]);
                        symbolField.setText(investment.getSymbol());
                        nameField.setText(investment.getName());
                        priceField.setText(String.valueOf(investment.getPrice()));
                        messageArea.setText("");

                }
        });

        // Save button action listener
        /**  
         * @param e ActionEvent
         * @return void
         */
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Update the price of the investment
                try {

                    double price = Double.parseDouble(priceField.getText());
                    portfolio.updatePrice(symbolField.getText(), price);
                    messageArea.setText("Price updated successfully");

                } catch (Exception ex) {

                    messageArea.setText("Error: " + ex.getMessage());

                }
            }
        });

        // Update the fields
        if (!portfolio.getInvestments().isEmpty()) {

            Investment inve = portfolio.getInvestment(currentIndex[0]);
                symbolField.setText(inve.getSymbol());
                nameField.setText(inve.getName());
                priceField.setText(String.valueOf(inve.getPrice()));

        }

        return panel;
    }

        //Method to create the view gain panel
        /**
         * @return JPanel
         */
        private JPanel createViewGainPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        // Create a top panel with a border layout
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Getting Total Gain", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        // Create a panel with a grid layout
        JPanel gainPanel = new JPanel(new GridLayout(1, 2));
        JLabel totalGainLabel = new JLabel("Total Gain:");
        totalGainField = new JTextField();
        totalGainField.setEditable(false);

        // Set the size of the text field
        gainPanel.add(totalGainLabel);
        gainPanel.add(totalGainField);

        // Add the panels to the top panel
        topPanel.add(gainPanel, BorderLayout.CENTER);
        
        // Create a bottom panel with a border layout
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JLabel individualGainLabel = new JLabel("Individual Gain:");
        JTextArea individualGainArea = new JTextArea(10, 30);
        individualGainArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(individualGainArea);

        // Add the panels to the bottom panel
        bottomPanel.add(individualGainLabel, BorderLayout.NORTH);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the panels to the main panel
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.CENTER);

        // Update the total gain
        updateTotalGain(individualGainArea);

        return panel;
    }

        //Method to update the total gain
        /**
         * @param individualGainArea JTextArea
         * @return void
         */
        private void updateTotalGain(JTextArea individualGainArea) {

        // Update the total gain and individual gain
        try {

                double totalGain = portfolio.getGain(); // Ensure this method exists in Portfolio
                totalGainField.setText(String.format("%.2f", totalGain)); // Format as currency/decimal

                StringBuilder sb = new StringBuilder();

                for (Investment investment : portfolio.getInvestments()) {

                        double gain = investment.getGain();
                        sb.append(investment.getName()).append(" (").append(investment.getSymbol()).append("): $").append(String.format("%.2f", gain)).append("\n");
                
                }

                individualGainArea.setText(sb.toString());

        } catch (Exception e) {

                totalGainField.setText("Error calculating gain: " + e.getMessage());
                individualGainArea.setText("Error calculating gain: " + e.getMessage());

        }
        }
        
    

        //Main method
        /**
         * @param args The command line arguments
         * @return void
         * @throws IOException
         */
        public static void main(String[] args) {

        // Check if the filename is provided
        if (args.length != 1) {
            System.out.println("Usage: java MainGUI <filename>");
            return;

        }

        // Load the investments from the file
        String filename = args[0];
        Portfolio portfolio = new Portfolio();
        portfolio.loadInvestments(filename);

        // Create the main GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new MainGUI(filename).setVisible(true);
                
            }
        });
    }
}
