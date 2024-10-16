package bgarsond_a1;

public class MutualFund {
        private String symbol;
        private String name;
        private double price;
        private int quantity;
        private double bookValue;

        //Constructor
        public MutualFund(String symbol, String name, double price, int quantity) {
            this.symbol = symbol;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.bookValue = calculateBookValue(quantity, price);
        }

        //Method to calculate book value
        public static double calculateBookValue(int quantity, double price) {
            return quantity * price;
        }

        //Method to buy mutual fund
        public void buy(int quantity, double price) {
                double additionalBookValue = calculateBookValue(quantity, price);
                this.bookValue += additionalBookValue;
                this.quantity += quantity;
                this.price = price;
        }

        //Method to sell mutual fund
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
        public void updatePrice(double price) {
            this.price = price;
        }

        //Method to get gain
        public double getGain() {
            // Calculate the potential gain if the mutual fund is sold at the current price
            double gain = (this.quantity * this.price - 45.00) - this.bookValue;
            return Math.round(gain * 100.0) / 100.0; // Rounded to two decimal places
        }

        //Method to get symbol
        public String getSymbol() {
                return this.symbol;
        }


        //Method to get name
        @Override
        public String toString() {
            return "MutualFund [symbol=" + symbol + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", bookValue=" + bookValue + "]";
        }

}