package bgarsond_a1;

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

        //Method to calculate book value
        /**
         * @param quantity The quantity of the stock
         * @param price The price of the stock
         * @return The book value of the stock
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
                double sellValue = price * quantity - 9.99;
                this.bookValue *= (double) (this.quantity - quantity) / this.quantity;  // Calculate the new book value
                this.quantity -= quantity;
                System.out.println("Sold " + quantity + " stocks. Gain: " + (sellValue - this.bookValue));
        }

        //Method to get gain
        /**
         * @return The gain of the stock
         */
        public double getGain() {
                // Calculate the potential gain if the stock is sold at the current price
                double gain = (this.quantity * this.price - 9.99) - this.bookValue;     // Calculate the gain
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