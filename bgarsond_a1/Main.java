package bgarsond_a1;

import java.io.*;
import java.util.Scanner;

/**
 * Main class to run the Portfolio Management System
 * This class contains the main method to run the Portfolio Management System
 * The user can buy, sell, update prices, get gain, search investments, and quit
 * The user can buy and sell stocks and mutual funds
 */
public class Main {
    /**
     * Main method to run the Portfolio Management System
     * This method contains the main menu for the user to interact with the system
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        
        if (args.length != 1){
            System.out.println("Usage: java Portfolio <filename>");
            return;
        }

        String filename = args[0];
        Portfolio portfolio = new Portfolio();

        try{
            loadInvestments(portfolio, filename);
        }catch(IOException e){
            System.out.println("Error loading investments: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String command;


        //Main menu
        while (true) {
            System.out.println("\n------- Portfolio Management System -------");
            System.out.println("Available commands: buy, sell, update, getGain, search, quit");
            System.out.print("Please enter a command: ");
            command = scanner.nextLine().trim().toLowerCase(); // Normalize input

            switch (command) {
                //When user press buy
                case "buy":
                case "b":
                    buyInvestment(scanner, portfolio);
                    break;

                //When user press sell
                case "sell":
                case "s":
                    sellInvestment(scanner, portfolio);
                    break;

                //When user press update
                case "update":
                case "u":
                    updatePrices(scanner, portfolio);
                    break;

                //When user press getGain
                case "getgain":
                case "g":
                    double totalGain = portfolio.getGain();
                    System.out.printf("Total Gain: $%.2f%n", totalGain);
                    break;

                //When user press search
                case "search":
                case "se":
                    searchInvestments(scanner, portfolio);
                    break;

                //When user press quit
                case "quit":
                case "q":
                    portfolio.saveInvestmentToFile(filename);
                    System.out.println("Investments saved to " + filename);
                    System.out.println("Thank you for using the Portfolio Management System. Goodbye!");
                    scanner.close();
                    return;

                //When user press invalid command
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }
    }
    

    private static void loadInvestments(Portfolio portfolio, String filename) throws IOException {
        File file = new File(filename);

        if(!file.exists()){
            System.out.println("File does not exist. Creating a new file...");
            if (file.createNewFile()){
                System.out.println("File created: " + file.getName());
            }else{
                System.out.println("Error creating the file.");
            }
            return;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            Investment investment = null;

            while ((line = reader.readLine()) != null){
                line = line.trim();
                if(line.startsWith("type =")){
                    String type = line.split("=")[1].trim().replace("\"", "");
                    investment = type.equals("stock") ? new Stock("", "", 0.0, 0) : new MutualFund("", "", 0.0, 0);
                } else if (investment != null) {
                    if (line.startsWith("symbol =")){
                        investment.setSymbol(line.split("=")[1].trim().replace("\"", ""));
                    } else if (line.startsWith("name =")){
                        investment.setName(line.split("=")[1].trim().replace("\"", ""));
                    }
                }
                if (investment != null) {
                    portfolio.addInvestment(investment);
                    investment = null;
                }
        } 
        } catch (IOException e){
            System.out.println("Error loading investments: " + e.getMessage());
        }
    

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            Investment investment = null;

            while((line = reader.readLine()) != null){
                line = line.trim();
                if(line.startsWith("type =")){
                    String type = line.split("=")[1].trim().replace("\"", "");
                    investment = type.equals("stock") ? new Stock("", "", 0.0, 0) : new MutualFund("", "", 0.0, 0);
            }else if (investment != null) {
                if (line.startsWith("symbol =")){
                    investment.setSymbol(line.split("=")[1].trim().replace("\"", ""));
                }else if(line.startsWith("name =")) {
                    investment.setName(line.split("=")[1].trim().replace("\"", ""));
                }

            }
            }
            }
        }



    /**
     * This method allows the user to buy an investment
     * The user can buy a stock or mutual fund
     * @param scanner The scanner object to read user input
     * @param portfolio The portfolio object to store investments
     */
    private static void buyInvestment(Scanner scanner, Portfolio portfolio) {
        System.out.print("Enter the type (stock or mutual fund): ");
        String type = scanner.nextLine().trim().toLowerCase();//Normalize input

        //If type is not stock or mutual fund
        if (!type.equals("stock") && !type.equals("mutual fund")) {
            System.out.println("Invalid type. Please enter 'stock' or 'mutual fund'.");
            return;
        }

        System.out.print("Enter the symbol: ");
        String symbol = scanner.nextLine().trim().toLowerCase();

        System.out.print("Enter the name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter the quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter the price: ");
        double price = Double.parseDouble(scanner.nextLine().trim());

        //If type is stock
        if (type.equals("stock")) {
            portfolio.buyStock(symbol, name, quantity, price); //For buy stock
            System.out.printf("You have successfully bought %d shares of %s at $%.2f each.%n", quantity, name, price);
        } 
        else 
        {
            portfolio.buyMutualFund(symbol, name, quantity, price); //If type is mutual fund
            System.out.printf("You have successfully bought %d units of %s at $%.2f each.%n", quantity, name, price);
        }
    }

    //Method to sell investment
    /**
     * This method allows the user to sell an investment
     * The user can sell a stock or mutual fund
     * @param scanner The scanner object to read user input
     * @param portfolio The portfolio object to store investments
     */
    private static void sellInvestment(Scanner scanner, Portfolio portfolio) {
        System.out.print("Enter the type (stock or mutual fund): ");
        String type = scanner.nextLine().trim().toLowerCase();

        System.out.print("Enter the symbol: ");
        String symbol = scanner.nextLine().trim().toLowerCase();

        System.out.print("Enter the quantity to sell: ");
        int quantity = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter the selling price: ");
        double price = Double.parseDouble(scanner.nextLine().trim());

        //If type is stock
        if (type.equals("stock")) {
            System.out.print("Are you sure you want to sell " + quantity + " shares of " + symbol + "? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase(); //Normalize input
            if (!confirmation.equals("yes")) {
                System.out.println("Transaction cancelled.");
                return;
            }

            //Sell stock
            portfolio.sellStock(symbol, quantity, price);
            System.out.println("Transaction completed successfully.");

        } 
        else //If type is mutual fund
        {
            System.out.print("Are you sure you want to sell " + quantity + " units of " + symbol + "? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            if (!confirmation.equals("yes")) {
                System.out.println("Transaction cancelled.");
                return;
            }
            portfolio.sellMutualFund(symbol, quantity, price);
            System.out.println("Transaction completed successfully.");
        }
    }

    //Method to update prices
    /**
     * @param scanner The scanner object to read user input
     * @param portfolio The portfolio object to store investments
     */
    private static void updatePrices(Scanner scanner, Portfolio portfolio) {
        System.out.print("Enter the symbol of the investment to update: ");
        String symbol = scanner.nextLine().trim();

        System.out.print("Enter the new price: ");
        double price = Double.parseDouble(scanner.nextLine().trim());

        portfolio.updatePrice(symbol, price);   //Update price
        System.out.printf("Price for %s has been updated to $%.2f.%n", symbol, price);
    }

    //Method to search investments
    /**
     * @param scanner The scanner object to read user input
     * @param portfolio The portfolio object to store investments
     */
    private static void searchInvestments(Scanner scanner, Portfolio portfolio) {
        System.out.print("Enter symbol (or leave empty for none): ");
        String symbol = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter keywords for the name (or leave empty for none): ");
        String keywords = scanner.nextLine().trim();

        System.out.print("Enter price range (e.g., '10.00-50.00' or leave empty for none): ");
        String priceRange = scanner.nextLine().trim();

        portfolio.search(symbol, keywords, priceRange); // Assuming you implement this overload
    }
}
