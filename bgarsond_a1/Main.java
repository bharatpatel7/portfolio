package bgarsond_a1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Portfolio portfolio = new Portfolio();
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("Enter a command (buy, sell, updaet, getGain, search, quit): ");
            command = scanner.nextLine();
            
            if (command.equalsIgnoreCase("buy")) {
                System.out.println("Enter the type (stock or mutual fund): ");
                String type = scanner.nextLine();

                System.out.println("Enter the symbol: ");
                String symbol = scanner.nextLine();

                System.out.println("Enter the name: ");
                String name = scanner.nextLine();

                System.out.println("Enter the quantity: ");
                int quantity = scanner.nextInt();

                System.out.println("Enter the price: ");
                double price = scanner.nextDouble();

                scanner.nextLine(); //clearing buffer

                if (type.equalsIgnoreCase("stock")) {
                    portfolio.buyStock(symbol, name, quantity, price);
                } else if (type.equalsIgnoreCase("mutual fund")) {
                    portfolio.buyMutualFund(symbol, name, quantity, price);
                }

            } else if (command.equalsIgnoreCase("sell")) {
                System.out.println("Enter the type (stock or mutual fund): ");
                String type = scanner.nextLine();

                System.out.println("Enter the symbol: ");
                String symbol = scanner.nextLine();

                System.out.println("Enter the quantity: ");
                int quantity = scanner.nextInt();

                System.out.println("Enter the price: ");
                double price = scanner.nextDouble();

                scanner.nextLine(); //clearing buffer

                if (type.equalsIgnoreCase("stock")) {
                    portfolio.sellStock(symbol, quantity, price);
                } else if (type.equalsIgnoreCase("mutual fund")) {
                    portfolio.sellMutualFund(symbol, quantity, price);
                }
            } else if (command.equalsIgnoreCase("update")) {
                System.out.println("Enter the symbol: ");
                String symbol = scanner.nextLine();

                System.out.println("Enter the price: ");
                double price = scanner.nextDouble();

                scanner.nextLine(); //clearing buffer

                portfolio.updatePrice(symbol, price);
            } else if (command.equalsIgnoreCase("getGain")) {
                System.out.println("Total Gain:" + portfolio.getGain());
                } else if (command.equalsIgnoreCase("search")) {
                    System.out.println("Enter the symbol: ");
                    String symbol = scanner.nextLine();
                    portfolio.search(symbol);
                } else if (command.equalsIgnoreCase("quit")) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid command.");
                }

                //scanner.close();
            
        }

    }
}