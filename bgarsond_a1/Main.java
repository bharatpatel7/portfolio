package bgarsond_a1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Portfolio portfolio = new Portfolio();
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

    //Method to buy investment
    /**
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
        String symbol = scanner.nextLine().trim();

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
     * @param scanner The scanner object to read user input
     * @param portfolio The portfolio object to store investments
     */
    private static void sellInvestment(Scanner scanner, Portfolio portfolio) {
        System.out.print("Enter the type (stock or mutual fund): ");
        String type = scanner.nextLine().trim().toLowerCase();

        System.out.print("Enter the symbol: ");
        String symbol = scanner.nextLine().trim();

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
        String symbol = scanner.nextLine().trim();

        System.out.print("Enter keywords for the name (or leave empty for none): ");
        String keywords = scanner.nextLine().trim();

        System.out.print("Enter price range (e.g., '10.00-50.00' or leave empty for none): ");
        String priceRange = scanner.nextLine().trim();

        portfolio.search(symbol, keywords, priceRange); // Assuming you implement this overload
    }
}
