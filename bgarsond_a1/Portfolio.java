package bgarsond_a1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a portfolio of investments
 */
public class Portfolio {
    private ArrayList<Investment> investment;

    // Constructor
    /**
     * Initializes the stock and mutual fund lists
     */
    public Portfolio() {

        this.investment = new ArrayList<>();

    }

    public void buyInvestment(String symbol, String name, int quantity, double price, boolean isMutualFund){
        for (Investment inv : investment) {
            if (inv.getSymbol().equalsIgnoreCase(symbol)){
                inv.buy(quantity, price);
                return;
            }
        }

        Investment newInvestment;

        if(isMutualFund){
            newInvestment = new MutualFund(symbol, name, price, quantity);
        }else{
            newInvestment = new Stock(symbol, name, price, quantity);
        }

        investment.add(newInvestment);
    }

    public void sellInvestment(String symbol, int quantity, double price){
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

    public void addInvestment(Investment investment){
        this.investment.add(investment);
    }

    public void updateInvestment(int index, String symbol, String name) {
        if (index >= 0 && index < investment.size()) {
            Investment inv = investment.get(index);
            inv.setSymbol(symbol);
            inv.setName(name);
        } else {
            System.out.println("Invalid index");
        }
    }

    public void updatePrice(String symbol, double price){
        for (Investment inv : investment){
            if(inv.getSymbol().equalsIgnoreCase(symbol)){
                inv.updatePrice(price);
                return;
            }
        }
        System.out.println("Investmemt not found!");
    }

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

    public void search(String symbol, String keywords, String priceRange){
        boolean found = false;

        String[] keywordList = keywords.split(" ");
        double minPrice = Double.MIN_VALUE;
        double maxPrice = Double.MAX_VALUE;

        if(priceRange.contains("-")){
            String[] priceRangeList = priceRange.split("-");
            minPrice = Double.parseDouble(priceRangeList[0]);
            maxPrice = Double.parseDouble(priceRangeList[1]);
        }

        for (Investment inv : investment){
            if(inv.getSymbol().equalsIgnoreCase(symbol) && 
            allKeywordsMatch(inv.getName(), keywordList) && 
            inv.getPrice() >= minPrice && 
            inv.getPrice() <= maxPrice) {
                System.out.println(inv);
                found = true;
            }
        }

        if(!found){
            System.out.println("No Mathch Found!");
        }
    }

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
        Stock stock = new Stock(symbol, name, price, quantity);
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
            this.investment.add(new MutualFund(symbol, name, price, quantity));
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

    public void loadInvestmentFromFile(String filename){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            Investment newInvestment = null;
            while ((line = reader.readLine()) != null){
                line = line.trim();

                if(line.startsWith("type")){
                    String type = line.split("=")[1].trim().replace("\"", "");
                    newInvestment = createInvestment(type);
                } else if (line.startsWith("symbol")){
                    newInvestment.setSymbol(line.split("=")[1].trim().replace("\"", ""));
                } else if (line.startsWith("name")){
                    newInvestment.setName(line.split("=")[1].trim().replace("\"", ""));
                } else if (line.startsWith("quantity")){
                    if (newInvestment != null) {
                        newInvestment.setQuantity(Integer.parseInt(line.split("=")[1].trim()));
                    } else {
                        System.out.println("Error: newInvestment is null.");
                    }
                } else if (line.startsWith("price")){
                    if (newInvestment != null) {
                        if (newInvestment instanceof Stock) {
                            ((Stock) newInvestment).setPrice(Double.parseDouble(line.split("=")[1].trim()));
                        } else if (newInvestment instanceof MutualFund) {
                            ((MutualFund) newInvestment).setPrice(Double.parseDouble(line.split("=")[1].trim()));
                        }
                    } else {
                        System.out.println("Error: newInvestment is null.");
                    }
                } else if (line.startsWith("bookValue")){
                    if (newInvestment != null) {
                        newInvestment.setBookValue(Double.parseDouble(line.split("=")[1].trim()));
                        investment.add(newInvestment);
                    } else {
                        System.out.println("Error: newInvestment is null.");
                    }
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private Investment createInvestment(String type){
        if (type.equalsIgnoreCase("stock")){
            return new Stock("", "", 0.0, 0);
        } else if (type.equalsIgnoreCase("mutual fund")){
            return new MutualFund("", "", 0.0, 0);
        } else {
            throw new IllegalArgumentException("Invalid investment type.");
        }
    }

    public void saveInvestmentToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for (Investment investment : investment){
                writer.write("type = \"" + investment.getClass().getSimpleName().toLowerCase() + "\"\n");
                writer.write("symbol = \"" + investment.getSymbol() + "\"\n");
                writer.write("name = \"" + investment.getName() + "\"\n");
                writer.write("quantity = " + investment.getQuantity() + "\n");
                writer.write("price = " + investment.getPrice() + "\n");
                writer.write("bookValue = " + investment.getBookValue() + "\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}