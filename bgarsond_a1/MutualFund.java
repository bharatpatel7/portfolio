package bgarsond_a1;

public class MutualFund {
        private String symbol;
        private String name;
        private double price;
        private int quantity;
        private double bookValue;

        public MutualFund(String symbol, String name, double price, int quantity) {
            this.symbol = symbol;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.bookValue = calculateBookValue(quantity, price);
        }

        public static double calculateBookValue(int quantity, double price) {
            return quantity * price;
        }

        public void buy(int quantity, double price) {
                double additionalBookValue = calculateBookValue(quantity, price);
                this.bookValue += additionalBookValue;
                this.quantity += quantity;
                this.price = price;
        }

        public void sell(int quantity, double price){
            if (this.quantity < quantity) {
                System.out.println("Not enough mutual fund to sell.");
                return;
            }
            double sellValue = price * quantity - 45.00;
            this.bookValue *= (double) (this.quantity - quantity) / this.quantity;
            this.quantity -= quantity;
            System.out.println("Sold " + quantity + " mutual funds. Gain: " + (sellValue - this.bookValue));
        }

        public void updatePrice(double price) {
            this.price = price;
        }

        public double getGain() {
            return this.price * this.quantity - this.bookValue - 45.00;
        }

        @Override
        public String toString() {
            return "MutualFund [symbol=" + symbol + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", bookValue=" + bookValue + "]";
        }

}