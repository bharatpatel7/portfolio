package bgarsond_a1;

import java.util.ArrayList;

public class Portfolio {
    private ArrayList<Stock> stocks;
    private ArrayList<MutualFund> mutualFunds;

    public Portfolio() {
        this.stocks = new ArrayList<Stock>();
        this.mutualFunds = new ArrayList<MutualFund>();
    }

    public void buyStock(String symbol, String name, int quantity, double price) {
        for (Stock stock : stocks) {
            if (stock.toString().contains(symbol)) {
                stock.buy(quantity, price);
                return;
            }
        }
        stocks.add(new Stock(symbol, name, price, quantity));
    }

    public void sellStock(String symbol, int quantity, double price) {
        for (Stock stock : stocks) {
            if (stock.toString().contains(symbol)) {
                stock.sell(quantity, price);
                if(stock.toString().contains("quantity=0")){
                    stocks.remove(stock);
                }
                return;
            }
        }
        System.out.println("Stock not found.");
    }

    public void buyMutualFund(String symbol, String name, int quantity, double price) {
        for (MutualFund mutualFund : mutualFunds) {
            if (mutualFund.toString().contains(symbol)) {
                mutualFund.buy(quantity, price);
                return;
            }
        }
        mutualFunds.add(new MutualFund(symbol, name, price, quantity));
    }

    public void sellMutualFund(String symbol, int quantity, double price) {
        for (MutualFund mutualFund : mutualFunds) {
            if (mutualFund.toString().contains(symbol)) {
                mutualFund.sell(quantity, price);
                if(mutualFund.toString().contains("quantity=0")){
                    mutualFunds.remove(mutualFund);
                }
                return;
            }
        }
        System.out.println("Mutual fund not found.");
    }

    public void updatePrice(String symbol, double price) {
        for (Stock stock : stocks) {
            if (stock.toString().contains(symbol)) {
                stock.updatePrice(price);
                
            }
        }
        for (MutualFund mutualFund : mutualFunds) {
            if (mutualFund.toString().contains(symbol)) {
                mutualFund.updatePrice(price);
               
            }
        }
        //System.out.println("Symbol not found.");
    }

    public double getGain() {
        double totalGain = 0;
        for (Stock stock : stocks) {
            totalGain += stock.getGain();
        }
        for (MutualFund mutualFund : mutualFunds) {
            totalGain += mutualFund.getGain();
        }
        return totalGain;
    }

    public void search(String symbol) {
        for (Stock stock : stocks) {
            if (stock.toString().contains(symbol)) {
                System.out.println(stock);
                
            }
        }
        for (MutualFund mutualFund : mutualFunds) {
            if (mutualFund.toString().contains(symbol)) {
                System.out.println(mutualFund);
                
            }
        }
        //System.out.println("Symbol not found.");
    }

    public double getTotalGain() {
        return 0.0;
    }


}