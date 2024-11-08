package ePortfolio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a portfolio of investments
 */
public class Portfolio {
    private ArrayList<Investment> investment = new ArrayList<>();
    private HashMap<String, List<Integer>> nameIndex = new HashMap<>();
    private String fileName;

    // Constructor
    /**
     * Initializes the stock and mutual fund lists
     */
    public Portfolio(String fileName){

        this.fileName = fileName;
        loadInvestments(this.fileName);
        
    }

    // Constructor
    /**
     * Initializes the stock and mutual fund lists
     */
    public Portfolio() {

        this.investment = new ArrayList<>();

    }

    // Method to buy investment
    /**
     * @param symbol The symbol of the investment
     * @param name The name of the investment
     * @param quantity The quantity of the investment
     * @param price The price of the investment
     * @param isMutualFund Whether the investment is a mutual fund
     */
    public void buyInvestment(String symbol, String name, int quantity, double price, boolean isMutualFund){

        for (Investment inv : investment) {

            if (inv.getSymbol().equalsIgnoreCase(symbol)){

                inv.buy(quantity, price);
                return;

            }

        }
        
        // Create a new investment
        Investment newInvestment;

        // Create a new investment
        if(isMutualFund){
            newInvestment = new MutualFund(symbol, name, quantity, price, 0.0, "default");
        }else{
            newInvestment = new Stock(symbol, name, quantity, price, 0.0, "default");
        }

        // Add the investment to the list
        investment.add(newInvestment);
    }

    // Method to sell investment
    /**
     * @param symbol The symbol of the investment
     * @param quantity The quantity of the investment
     * @param price The price of the investment
     */
    public void sellInvestment(String symbol, int quantity, double price){

        // Loop through the investments
        for (Investment inv : investment){

            if(inv.getSymbol().equalsIgnoreCase(symbol)){

                inv.sell(quantity, price);

                if (inv instanceof Stock && ((Stock) inv).getQuantity() == 0){

                    this.investment.remove(inv);

                }

            if (inv instanceof MutualFund && ((MutualFund) inv).getQuantity() == 0){

                this.investment.remove(inv);

            }

            return;

        }
            System.out.println("Investment not found!");
        }
    }

    // Method to update price
    /**
     * @param symbol The symbol of the investment
     * @param price The price of the investment
     */
    public void addInvestment(Investment investment){

        this.investment.add(investment);
        //updateInvestment(investment, this.investment.size() -1);

    }

    // Method to update price
    /**
     * @param symbol The symbol of the investment
     * @param price The price of the investment
     */
    public void updateInvestment(int index, String symbol, String name) {

        if (index >= 0 && index < investment.size()) {

            Investment inv = investment.get(index);
            inv.setSymbol(symbol);
            inv.setName(name);

        } else {

            System.out.println("Invalid index");

        }
    }

    // Method to update price
    /**
     * @param symbol The symbol of the investment
     * @param price The price of the investment
     */
    public void updatePrice(String symbol, double price){

        for (Investment inv : investment){

            if(inv.getSymbol().equalsIgnoreCase(symbol)){

                inv.updatePrice(price);
                return;

            }

        }

        System.out.println("Investmemt not found!");
    }

    // Method to get gain
    /**
     * @return The total gain of the portfolio
     */
    public double getGain(){

        double totalGain = 0;

        for(Investment investment : investment){

            if (investment instanceof Stock){

                totalGain += ((Stock) investment).getGain();

            } else if (investment instanceof MutualFund){

                totalGain += ((MutualFund) investment).getGain();

            }
        }

        System.out.println("Total Gain: " + String.format("%.2f", totalGain));
        return totalGain;

    }

    // Method to search
    /**
     * @param symbol The symbol of the investment
     * @param keywords The keywords to search for
     * @param priceRange The price range to search for
     */
    public void search(String symbol, String keywords, String priceRange){

        boolean found = false;

        // Split the keywords
        String[] keywordList = keywords != null ? keywords.split(" ") : new String[0];
        double minPrice = 0.0;
        double maxPrice = Double.MAX_VALUE;

        // Check if the price range is not empty
        if (priceRange != null && !priceRange.isEmpty()){

            String[] prices = priceRange.split("-");

            // Check if the price range is valid
            try {
                    
                    minPrice = Double.parseDouble(prices[0]);
                    maxPrice = Double.parseDouble(prices[1]);
    
                } catch (NumberFormatException e){
    
                    System.out.println("Invalid price range: " + priceRange);
                    return;
            }

        }

        // Loop through the investments
        for (Investment inv : investment){

            // Check if the symbol, keywords, and price match
            boolean symbolMatch = symbol == null || symbol.isEmpty() || inv.getSymbol().equalsIgnoreCase(symbol);
            boolean keywordMatch = (keywords == null || keywords.isEmpty()) || allKeywordsMatch(inv.getName(), keywordList);
            boolean priceMatch = inv.getPrice() >= minPrice && inv.getPrice() <= maxPrice;

            if (symbolMatch && keywordMatch && priceMatch){

                System.out.println(inv);
                found = true;

            }

        }

        if(!found){

            System.out.println("No Mathch Found!");

        }
    }
    

    // Method to check if all keywords match
    /**
     * @param name The name of the investment
     * @param keywords The keywords to search for
     * @return Whether all keywords match
     */
    private boolean allKeywordsMatch(String name, String[] keywords){
        for (String keyword : keywords){
            if(!name.toLowerCase().contains(keyword.toLowerCase())){
                return false;
            }
        }
        return true;
    }

    // Method to buy stock
    /**
     * @param symbol The symbol of the stock
     * @param name The name of the stock
     * @param quantity The quantity of the stock
     * @param price The price of the stock
     */
    public void buyStock(String symbol, String name, int quantity, double price) {
        Stock stock = new Stock(symbol, name, quantity, price, 0.0, "default");
        investment.add(stock);
        System.out.println("Bought " + quantity + " stocks of " + name + " at $" + price + " each.");
    }

    

    // Method to sell stock
    /**
     * @param symbol The symbol of the stock
     * @param quantity The quantity of the stock
     * @param price The price of the stock
     */
    public void sellStock(String symbol, int quantity, double price) {
        for (Investment investment : investment) {
            if (investment instanceof Stock && investment.getSymbol().equals(symbol)) {
                double gain = (price * quantity) - investment.getBookValue();
                System.out.println("Payment received: " + (price * quantity));
                System.out.println("Gain: " + String.format("%.2f", gain));
                this.investment.remove(investment);
                break;
            }
        }
    }

    // Method to buy mutual fund
    /**
     * @param symbol The symbol of the mutual fund
     * @param name The name of the mutual fund
     * @param quantity The quantity of the mutual fund
     * @param price The price of the mutual fund
     */
    public void buyMutualFund(String symbol, String name, int quantity, double price) {
        Investment investment = findInvestment(symbol);
        if (investment != null && investment instanceof MutualFund){
            investment.buy(quantity, price);
        } else {
            this.investment.add(new MutualFund(symbol, name, quantity, price, 0.0, "default"));
        }
    }

    // Method to sell mutual fund
    /**
     * @param symbol The symbol of the mutual fund
     * @param quantity The quantity of the mutual fund
     * @param price The price of the mutual fund
     */
    public void sellMutualFund(String symbol, int quantity, double price) {
        Investment investment = findInvestment(symbol);
        if (investment != null && investment instanceof MutualFund){
            investment.sell(quantity, price);
            if(investment instanceof MutualFund && ((MutualFund) investment).getQuantity() == 0){
                this.investment.remove(investment);
            }
        } else {
            System.out.println("Mutual Fund not found.");
        }
    }


    // Method to get total gain
    /**
     * @return The total gain of the portfolio
     */
    public double getTotalGain() {
        return Math.round(getTotalGain() * 100.0) / 100.0; // Rounded to two decimal places
        //return 0.0;
    }

    // Method to find an investment by symbol
    /**
     * @param symbol The symbol of the investment
     * @return The investment with the given symbol, or null if not found
     */
    private Investment findInvestment(String symbol) {
        for (Investment investment : investment) {
            if (investment.getSymbol().equalsIgnoreCase(symbol)) {
                return investment;
            }
        }
        return null;
    }

    // Method to load investments
    /**
     * @param filename The name of the file to load investments from
     */
    public void loadInvestments(String filename){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            Investment investment = null;

            String type = null, symbol = null, name = null;
            int quantity = 0;
            double price = 0.0, bookValue = 0.0;


            while ((line = reader.readLine()) != null){
                line = line.trim();

                if (line.startsWith("type")){
                    
                    type = line.split("=")[1].trim().replace("\"", "");

                } else if (line.startsWith("symbol")){

                    symbol = line.split("=")[1].trim().replace("\"", "");

                } else if (line.startsWith("name")){

                    name = line.split("=")[1].trim().replace("\"", "");

                } else if (line.startsWith("quantity")){

                    quantity = Integer.parseInt(line.split("=")[1].trim());

                } else if (line.startsWith("price")){

                    price = Double.parseDouble(line.split("=")[1].trim());

                } else if (line.startsWith("bookValue")){

                    bookValue = Double.parseDouble(line.split("=")[1].trim());
                } 
                
                if (line.isEmpty() || !reader.ready()){

                    if (type != null && symbol != null && name != null){

                        if(type.equalsIgnoreCase("stock")){

                            investment = new Stock(symbol, name, quantity, price, bookValue, "default");

                        } else if (type.equalsIgnoreCase("mutualfund")){

                            investment = new MutualFund(symbol, name, quantity, price, bookValue, "default");

                        } else {

                            System.out.println("Invalid type: \"" + type + "\"");

                            type = symbol = name = null;
                            quantity = 0;
                            price = bookValue = 0.0;
                            continue;
                        }

                        if (investment != null){

                            this.investment.add(investment);
                            updateNameIndex(investment, this.investment.size() - 1);

                        }
                        

                        type = symbol = name = null;
                        quantity = 0;
                        price = bookValue = 0.0;
                    }
                
                }
            
            } 

            System.out.println("Loaded " + this.investment.size() + " investments.");

        } catch (IOException | NumberFormatException e){

            System.out.println("Error reading file: " + e.getMessage());

        }
    }

    // Method to update name index
    /**
     * @param investment The investment to update the name index for
     * @param index The index of the investment
     */
    private void updateNameIndex(Investment investment, int index){
        String[] keywords = investment.getName().toLowerCase().split(" ");
        for (String keyword : keywords){
            nameIndex.computeIfAbsent(keyword, k -> new ArrayList<>()).add(index);
        }
    }

    // Method to search investments
    /**
     * @param keywords The keywords to search for
     * @return The list of investments that match the keywords
     */
    private Investment createInvestment(String type){
        if (type.equalsIgnoreCase("stock")){
            return new Stock("", "", 0, 0.0, 0.0, "default");
        } else if (type.equalsIgnoreCase("mutual fund")){
            return new MutualFund("", "", 0, 0.0, 0.0, "default");
        } else {
            throw new IllegalArgumentException("Invalid investment type.");
        }
    }

    // Method to search investments
    /**
     * @param keywords The keywords to search for
     * @return The list of investments that match the keywords
     */
    public void saveInvestment(String filename) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){

            for (Investment investment : this.investment){

                writer.write("type = \"" + investment.getClass().getSimpleName().toLowerCase() + "\"\n");
                writer.write("symbol = \"" + investment.getSymbol() + "\"\n");
                writer.write("name = \"" + investment.getName() + "\"\n");
                writer.write("quantity = " + investment.getQuantity() + "\n");
                writer.write("price = " + investment.getPrice() + "\n");
                writer.write("bookValue = " + investment.getBookValue() + "\n");
                writer.write("\n");

            }

        } catch (IOException e){

            e.printStackTrace();

        }
    }


}

