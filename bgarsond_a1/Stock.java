package bgarsond_a1;

/**
 * Represents a stock in the portfolio
 */
public class Stock {
        private String symbol;
        private String name;
        private double price;
        private int quantity;
        private double bookValue;

        //Constructor
        /**
         * @param symbol The symbol of the stock
         * @param name The name of the stock
         * @param price The price of the stock
         * @param quantity The quantity of the stock
         **/
        public Stock(String symbol, String name, double price, int quantity) {
            this.symbol = symbol;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.bookValue = calculateBookValue(quantity, price);
        }

        /**
         * This method calculates the book value of the stock
         * @param quantity The quantity of the stock
         * @param price The price of the stock
         */
        public void buy(int quantity, double price) {
                double additionalBookValue = calculateBookValue(quantity, price);   // Calculate the book value of the additional stocks
                this.bookValue += additionalBookValue;
                this.quantity += quantity;
                this.price = price;
        }

        //Method to update price
        /**
         * @param price The price of the stock
         */
        public void updatePrice(double price) {
        this.price = price;
        }

        //Method to sell stock
        /**
         * @param quantity The quantity of the stock
         * @param price The price of the stock
         */
        public void sell(int quantity, double price) {
            if (this.quantity < quantity) {
            System.out.println("Not enough stock to sell.");
            return;
        }
                double sellValue = (quantity * price) - 9.99;
                System.out.println("Payment Recived: " + sellValue);

                if (this.quantity == quantity) {
                    double gain = sellValue - this.bookValue;
                    System.out.println("Gain: " + gain);
                    this.quantity = 0;
                    this.bookValue = 0;
                    System.out.println("Sold all and final gain: " + gain);
                } else {
                    int remainingQuantity = this.quantity - quantity;
                    double soldBookValue = this.bookValue * ((double) quantity / this.quantity);
                    System.out.println("Book Value Sold: " + soldBookValue);

                    double gain = sellValue - soldBookValue;
                    System.out.println("Gain: " + gain);

                    double newBookValue = this.bookValue - soldBookValue;
                    this.bookValue = newBookValue;
                    this.quantity -= quantity;
                    System.out.println("New Book Value: " + this.bookValue);
                    System.out.println("New Quantity: " + this.quantity);
                    System.out.println("Sold " + quantity + " stocks. Gain: " + gain);
                }
        }

        //Method to get gain
        /**
         * @return The gain of the stock
         */
        public double getGain() {
                // Calculate the potential gain if the stock is sold at the current price
                double sellValue = (this.quantity * this.price) - 9.99;     // Calculate the gain
                System.out.println("Sell Value: " + sellValue);
                
                double gain = sellValue - this.bookValue;
                System.err.println("Book Value: " + this.bookValue);
                System.out.println("Gain: " + gain);
               return Math.round(gain * 100.0) / 100.0; // Rounded to two decimal places
        }

        //Method to get symbol
        /**
         * @return The symbol of the stock
         */
        public String getSymbol() {
            return this.symbol;
        }

        //Method to calculate book value
        /**
         * @param quantity The quantity of the stock
         * @param price The price of the stock
         * @return The book value of the stock
         */
        public static double calculateBookValue(int quantity, double price) {
            double Value =  quantity * price + 9.99;    // Calculate the book value
            return Math.round(Value * 100.0) / 100.0;
        }

        //Method to get name
        /**
         * @return The name of the stock
         */
        public String toString() {
            return "Stock [symbol=" + symbol + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", bookValue=" + bookValue + "]";
        }

}