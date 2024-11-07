package bgarsond_a1;

/**
 * Represents a mutual fund in the portfolio
 */
public class MutualFund extends Investment {

        //Constructor
        /**
         * @param symbol The symbol of the mutual fund
         * @param name The name of the mutual fund
         * @param price The price of the mutual fund
         * @param quantity The quantity of the mutual fund
         */
        public MutualFund(String symbol, String name, double price, int quantity) {
            super(symbol, name, price, quantity);
        }

        //Method to calculate book value
        /**
         * @param quantity The quantity of the mutual fund
         * @param price The price of the mutual fund
         */
        protected  double calculateBookValue(int quantity, double price) {
            return quantity * price;
        }

        //Method to buy mutual fund
        /**
         * @param quantity The quantity of the mutual fund
         * @param price The price of the mutual fund
         */
        public void buy(int quantity, double price) {
                double additionalBookValue = calculateBookValue(quantity, price);
                this.bookValue += additionalBookValue;
                this.quantity += quantity;
                this.price = price;
        }

        //Method to sell mutual fund
        /**
         * @param quantity The quantity of the mutual fund
         * @param price The price of the mutual fund
         */
        public void sell(int quantity, double price){
            if (this.quantity < quantity) {
                System.out.println("Not enough mutual fund to sell.");
                return;
            } //If quantity is less than the quantity to sell
            double sellValue = price * quantity - 45.00;
            this.bookValue *= (double) (this.quantity - quantity) / this.quantity;
            this.quantity -= quantity;
            System.out.println("Sold " + quantity + " mutual funds. Gain: " + (sellValue - this.bookValue));
        }

        //Method to update price
        /**
         * @param price The price of the mutual fund
         */
        public void updatePrice(double price) {
            this.price = price;
        }

        //Method to get gain
        /**
         * @return The gain of the mutual fund
         */
        @Override
        public double getGain() {
            // Calculate the potential gain if the mutual fund is sold at the current price
            double currentSellValue = (this.quantity * this.price) - 45.00;
            double gain = currentSellValue - this.bookValue;

            return Math.round(gain * 100.0) / 100.0; // Rounded to two decimal places
        }

        //Method to get symbol
        /**
         * @return The symbol of the mutual fund
         */
        public String getSymbol() {
                return this.symbol;
        }

        public String getName() {
            return this.name;
        }

        public  double getPrice() {
            return this.price;
        }

        public int getQuantity() {
            return this.quantity;
        }



        //Method to get name
        /**
         * @return The name of the mutual fund
         */
        @Override
        public String toString() {
            return "MutualFund [symbol=" + symbol + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", bookValue=" + bookValue + "]";
        }
        

}