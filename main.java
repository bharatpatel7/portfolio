package ePortfolio;
import java.util.*;
import javax.sound.sampled.Port;

public class Main{
        public static void main(String[] args) {
            Portfolio portfolio = new Portfolio();
                Scanner scanner = new Scanner(System.in);
                String command;

                while (true) {
                        System.out.println("Enter command (buy, sell, update, getGain, search, quit): "); 
                        command = scanner.nextLine();

                        if(command.equalsIgnoreCase("buy")){
                                System.out.println("Enter type (stock/mutualfund): ");
                                String type = scanner.nextLine();

                                System.out.println("Enter the stock symbol: ");
                                String symbol = scanner.nextLine();

                                System.out.println("Enter the name: ");
                                int shares = scanner.nextInt();

                                scanner.nextLine();

                                System.out.println("Enter quantity: ");
                                double price = scanner.nextDouble();

                                System.out.println("Enter price: ");
                                double commission = scanner.nextDouble();

                                scanner.nextLine();

                        }
                    
                }}
        }
}