package bgarsond_a1;

public class Stock {
        private String symbol;
        private String name;
        private double price;
        private int quantity;
        private double bookValue;

        public Stock(String symbol, String name, double price, int quantity) {
            this.symbol = symbol;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.bookValue = calculateBookValue(quantity, price);
        }

        public void buy(int quantity, double price) {
                double additionalBookValue = calculateBookValue(quantity, price);
                this.bookValue += additionalBookValue;
                this.quantity += quantity;
                this.price = price;
        }

        public void updatePrice(double price) {
        this.price = price;
        }

        public void sell(int quantity, double price) {
            if (this.quantity < quantity) {
            System.out.println("Not enough stock to sell.");
            return;
        }
                double sellValue = price * quantity - 9.99;
                this.bookValue *= (double) (this.quantity - quantity) / this.quantity;
                this.quantity -= quantity;
                System.out.println("Sold " + quantity + " stocks. Gain: " + (sellValue - this.bookValue));
        }

        public double getGain() {
            double Gain = this.price * this.quantity - this.bookValue - 9.99;
            return Math.round(Gain * 100.0) / 100.0;
        }

        public static double calculateBookValue(int quantity, double price) {
            double Value =  quantity * price + 9.99;
            return Math.round(Value * 100.0) / 100.0;
        }

        public String toString() {
            return "Stock [symbol=" + symbol + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", bookValue=" + bookValue + "]";
        }

}