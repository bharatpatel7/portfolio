package ePortfolio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.PrintWriter;

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
        Investment existing = findInvestment(symbol);
        if (existing != null){
            if ((isMutualFund && existing instanceof MutualFund) || (!isMutualFund && existing instanceof Stock)){
                existing.buy(quantity, price);
            } else {
                throw new IllegalArgumentException("Investment with symbol " + symbol + " not found.");
            }
        } else {
            Investment newInvestment = isMutualFund ? new MutualFund(symbol, name, quantity, price, 0.0, "default") : new Stock(symbol, name, quantity, price, 0.0, "default");
            investment.add(newInvestment);
            updateNameIndex(newInvestment, investment.size() - 1);
        }
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

            /*if (investment instanceof Stock){

                totalGain += ((Stock) investment).getGain();

            } else if (investment instanceof MutualFund){

                totalGain += ((MutualFund) investment).getGain();

            }*/
           totalGain += investment.getGain();
        }

        //System.out.println("Total Gain: " + String.format("%.2f", totalGain));
        return totalGain;

    }

    // Method to search
    /**
     * @param symbol The symbol of the investment
     * @param keywords The keywords to search for
     * @param priceRange The price range to search for
     */
    public String search(String symbol, String keywords, String priceRange){

        boolean found = false;
        StringBuilder results = new StringBuilder();

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
    
                    //System.out.println("Invalid price range: " + priceRange);
                    return "Invalid Price Range! " + priceRange;
            }

        }

        // Loop through the investments
        for (Investment inv : investment){

            // Check if the symbol, keywords, and price match
            boolean symbolMatch = symbol == null || symbol.isEmpty() || inv.getSymbol().equalsIgnoreCase(symbol);
            boolean keywordMatch = (keywords == null || keywords.isEmpty()) || allKeywordsMatch(inv.getName(), keywordList);
            boolean priceMatch = inv.getPrice() >= minPrice && inv.getPrice() <= maxPrice;

            if (symbolMatch && keywordMatch && priceMatch){

                results.append(inv).append("\n");
                found = true;

            }

        }

        if(!found){

            results.append("found no investments");

        }

        return results.toString();
    }
    

    // Method to check if all keywords match
    /**
     * @param name The name of the investment
     * @param keywords The keywords to search for
     * @return Whether all keywords match
     */
    private boolean allKeywordsMatch(String name, String[] keywords){

        // Loop through the keywords
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
     * @return The total gain of the portfolio
     */
    public void buyStock(String symbol, String name, int quantity, double price) {

        Investment existingInvestment = findInvestment(symbol);

        if (existingInvestment != null) {

            if (existingInvestment instanceof Stock) {

                existingInvestment.buy(quantity, price);

            } else {

                throw new IllegalArgumentException("Investment with symbol " + symbol + " is not a stock.");
            
            }
        }else{
            
        Stock stock = new Stock(symbol, name, quantity, price, 0.0, "default");
        investment.add(stock);
        //System.out.println("Bought " + quantity + " stocks of " + name + " at $" + price + " each.");
    }
    }


    // Method to sell stock
    /**
     * @param symbol The symbol of the stock
     * @param quantity The quantity of the stock
     * @param price The price of the stock
     * @return The total gain of the portfolio
     */
    public void sellStock(String symbol, int quantity, double price) {

        Investment investment = findInvestment(symbol);
        if (investment != null && investment instanceof Stock) {

            investment.sell(quantity, price);
            if(investment.getQuantity() == 0){

                this.investment.remove(investment);

            }
        }else{

            throw new IllegalArgumentException("Stock with symbol " + symbol + " not found.");
        
        }
    }

    // Method to buy mutual fund
    /**
     * @param symbol The symbol of the mutual fund
     * @param name The name of the mutual fund
     * @param quantity The quantity of the mutual fund
     * @param price The price of the mutual fund
     * @return The total gain of the portfolio
     */
    public void buyMutualFund(String symbol, String name, int quantity, double price) {

        // Find the investment
        Investment existingInvestment = findInvestment(symbol);
        if (existingInvestment != null) {

            if (existingInvestment instanceof MutualFund) {

                existingInvestment.buy(quantity, price);

            } else {

                throw new IllegalArgumentException("An investment with the symbol " + symbol + " already exists as a Stock.");
            
            }
        } else {

            MutualFund mutualFund = new MutualFund(symbol, name, quantity, price, 0.0, "default");
            investment.add(mutualFund);
        
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

            if(investment.getQuantity() == 0){
                this.investment.remove(investment);
            }

        } else {

            throw new IllegalArgumentException("Mutual fund with symbol " + symbol + " not found.");
            
        }
    }


    // Method to get total gain
    /**
     * @return The total gain of the portfolio
     */
    public double getTotalGain() {

        double totalGain = 0.0;
        for (Investment investment : investment){

            totalGain += investment.getGain();

        }
        
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

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            } catch (IOException ex) {

            System.out.println("Error creating file: " + ex.getMessage());

        }

        }
    }

    // Method to update name index
    /**
     * @param investment The investment to update the name index for
     * @param index The index of the investment
     */
    private void updateNameIndex(Investment investment, int index){

        // Split the name into keywords
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
    public void saveInvestments(String filename) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))){

            for (Investment investment : investment){
                writer.println(investment.toString());
                /* writer.write("type = \"" + investment.getClass().getSimpleName().toLowerCase() + "\"\n");
                writer.write("symbol = \"" + investment.getSymbol() + "\"\n");
                writer.write("name = \"" + investment.getName() + "\"\n");
                writer.write("quantity = " + investment.getQuantity() + "\n");
                writer.write("price = " + investment.getPrice() + "\n");
                writer.write("bookValue = " + investment.getBookValue() + "\n");
                writer.write("\n"); */

            }

        } catch (IOException e){

            e.printStackTrace();

        }
    }

    // Method to get investment
    public Investment getInvestment(int index) {

        if (index >= 0 && index < investment.size()) {

            return investment.get(index);

        } else {

            throw new IndexOutOfBoundsException("Invalid index: " + index);

        }
    }

    // Method to get investments
    public List<Investment> getInvestments() {

        return investment;

    }

    // Method to get file name
    public String getFileName() {
    return fileName;

    
    
}




}


