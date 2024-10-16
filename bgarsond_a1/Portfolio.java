package bgarsond_a1;

import java.util.ArrayList;

public class Portfolio {
    private ArrayList<Stock> stocks;
    private ArrayList<MutualFund> mutualFunds;

    // Constructor
    /**
     * Initializes the stock and mutual fund lists
     */
    public Portfolio() {
        this.stocks = new ArrayList<Stock>();
        this.mutualFunds = new ArrayList<MutualFund>();
    }

    // Method to buy stock
    /**
     * @param symbol The symbol of the stock
     * @param name The name of the stock
     * @param quantity The quantity of the stock
     * @param price The price of the stock
     */
    public void buyStock(String symbol, String name, int quantity, double price) {
        for (Stock stock : stocks) {
            if (stock.toString().contains(symbol)) {
                stock.buy(quantity, price); // Buy more of the same stock
                return;
            }
        }
        stocks.add(new Stock(symbol, name, price, quantity)); // Add new stock to the list
    }

    // Method to sell stock
    /**
     * @param symbol The symbol of the stock
     * @param quantity The quantity of the stock
     * @param price The price of the stock
     */
    public void sellStock(String symbol, int quantity, double price) {
        for (Stock stock : stocks) {
            if (stock.toString().contains(symbol)) {
                stock.sell(quantity, price);
                if(stock.toString().contains("quantity=0")){
                    stocks.remove(stock);   // Remove stock from the list if quantity is zero
                }
                return;
            }
        }
        System.out.println("Stock not found.");
    }

    // Method to buy mutual fund
    /**
     * @param symbol The symbol of the mutual fund
     * @param name The name of the mutual fund
     * @param quantity The quantity of the mutual fund
     * @param price The price of the mutual fund
     */
    public void buyMutualFund(String symbol, String name, int quantity, double price) {
        for (MutualFund mutualFund : mutualFunds) {
            if (mutualFund.toString().contains(symbol)) {
                mutualFund.buy(quantity, price);   // Buy more of the same mutual fund
                return;
            }
        }
        mutualFunds.add(new MutualFund(symbol, name, price, quantity));
    }

    // Method to sell mutual fund
    /**
     * @param symbol The symbol of the mutual fund
     * @param quantity The quantity of the mutual fund
     * @param price The price of the mutual fund
     */
    public void sellMutualFund(String symbol, int quantity, double price) {
        for (MutualFund mutualFund : mutualFunds) {
            if (mutualFund.toString().contains(symbol)) {
                mutualFund.sell(quantity, price);
                if(mutualFund.toString().contains("quantity=0")){
                    mutualFunds.remove(mutualFund);     // Remove mutual fund from the list if quantity is zero
                }
                return;
            }
        }
        System.out.println("Mutual fund not found.");
    }

    // Method to update price
    /**
     * @param symbol The symbol of the investment
     * @param price The price of the investment
     */
    public void updatePrice(String symbol, double price) {
        for (Stock stock : stocks) {
            if (stock.toString().contains(symbol)) {
                stock.updatePrice(price);  // Update price of the stock
                
            }
        }
        for (MutualFund mutualFund : mutualFunds) {
            if (mutualFund.toString().contains(symbol)) {
                mutualFund.updatePrice(price);  // Update price of the mutual fund
               
            }
        }
        //System.out.println("Symbol not found.");
    }

    // Method to get gain
    /**
     * @return The total gain of the portfolio
     */
    public double getGain() {
        double totalGain = 0;

        // Accumulate gain for all stocks
        for (Stock stock : stocks) {
            totalGain += stock.getGain();   // Get gain for each stock
        }

        // Accumulate gain for all mutual funds
        for (MutualFund mutualFund : mutualFunds) {
            totalGain += mutualFund.getGain();   // Get gain for each mutual fund
        }

        return Math.round(totalGain * 100.0) / 100.0; // Rounded to two decimal places
    }


    

    // public String getSymbol() {
    //     return this.symbol;
    // }


    // Method to search for an investment
    /**
     * @param symbol The symbol of the investment
     * @param keywords The keywords to search for
     * @param priceRange The price range to search for
     */
    public void search(String symbol, String keywords, String priceRange) {
    boolean found = false;

        // Search in the stock list
        for (Stock stock : stocks) {
            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                System.out.println(stock);  // Print stock details
                found = true;
            }
        }

        // Search in the mutual fund list
        for (MutualFund mutualFund : mutualFunds) {
            if (mutualFund.getSymbol().equalsIgnoreCase(symbol)) {
                System.out.println(mutualFund);     // Print mutual fund details
                found = true;
            }
        }


        if (!found) {
            System.out.println("No matching investment found.");
        }
    }

    // Method to get total gain
    /**
     * @return The total gain of the portfolio
     */
    public double getTotalGain() {
        return 0.0;
    }


}