package bgarsond_a1;

/**
 * Represents a stock in the portfolio
 */
public class Stock extends Investment {

        public Stock(String symbol, String name, double price, int quantity){

             
            super(symbol, name, price, quantity);

        }


        @Override
        protected double calculateBookValue(int quantity, double price){

            return quantity * price + 9.99;

        }

        @Override
        public double getGain() {
                // Calculate the potential gain if the stock is sold at the current price
                double sellValue = (this.quantity * this.price) - 9.99;     // Calculate the gain
                System.out.println("Sell Value: " + sellValue);
                
                double gain = sellValue - this.bookValue;
                System.err.println("Book Value: " + this.bookValue);
                System.out.println("Gain: " + String.format("%.2f", gain));
               return Math.round(gain * 100.0) / 100.0; // Rounded to two decimal places
        }


        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
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
                    System.out.println("Gain: " + String.format("%.2f", gain));
                    this.quantity = 0;
                    this.bookValue = 0;
                    System.out.println("Sold all and final gain: " + String.format("%.2f", gain));
                } else {
                    int remainingQuantity = this.quantity - quantity;
                    double soldBookValue = this.bookValue * ((double) quantity / this.quantity);
                    System.out.println("Book Value Sold: " + soldBookValue);

                    double gain = sellValue - soldBookValue;
                    System.out.println("Gain: " + String.format("%.2f", gain));

                    double newBookValue = this.bookValue - soldBookValue;
                    this.bookValue = newBookValue;
                    this.quantity -= quantity;
                    System.out.println("New Book Value: " + this.bookValue);
                    System.out.println("New Quantity: " + this.quantity);
                System.out.println("Sold " + quantity + " stocks. Gain: " + String.format("%.2f", gain));
                System.out.println("Gain: " + String.format("%.2f", gain));
            }
        }
    
        //Method to get symbol
        /**
         * @return The symbol of the stock
         */
        public String getSymbol() {
            return this.symbol;
        }


        //Method to get name
        /**
         * @return The name of the stock
         */
        public String toString() {
            return "Stock [symbol=" + getSymbol() + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", bookValue=" + bookValue + "]";
        }

}
