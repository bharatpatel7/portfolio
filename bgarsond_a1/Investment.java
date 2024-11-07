package bgarsond_a1;

public abstract class Investment {

        protected String symbol;
        protected String name;
        protected double price;
        protected int quantity;
        protected double bookValue;

        public Investment(String symbol, String name, double price, int quantity){
                if (symbol == null || name == null) {
                        throw new IllegalArgumentException("Symbol and name cannot be null");
                }
                this.symbol = symbol;
                this.name = name;
                this.price = price;
                this.quantity = quantity;
                this.bookValue = calculateBookValue(quantity, price);
        }

        protected abstract double calculateBookValue(int quantity, double price);

        public void buy(int quantity, double price){

                double additionalBookValue = calculateBookValue(quantity, price);
                
                this.bookValue += additionalBookValue;
                this.quantity += quantity;
                this.price = price;

        }

        public void updatePrice(double price){

                this.price = price;

        }
        
        public void setSymbol(String symbol){
                if (symbol == null) {
                        throw new IllegalArgumentException("Symbol cannot be null");
                }
                this.symbol = symbol;
        }

        public void setQuantity(int quantity){
                
                this.quantity = quantity;
        }

        public void setPrice(double price){
                
                this.price = price;
        }

        public void setBookValue(double bookValue){
                
                this.bookValue = bookValue;
        }
        

        public void sell(int quantity, double price){

                if (this.quantity >= quantity){
                        this.quantity -= quantity;
                        this.price = price;
                }else{
                        System.out.println("Not enough Quantity to sell!!");
                }
        }

        public void setName(String name){
                if (name == null) {
                        throw new IllegalArgumentException("Name cannot be null");
                }
                this.name = name;
        }

         

        public String getName(){

                return this.name;

        }

        public double getPrice(){

                return this.price;

        }

        public int getQuantity(){

                return this.quantity;

        }

        public String getSymbol(){

                return this.symbol;

        }

        public double getBookValue(){

                return bookValue;

        }

        public abstract double getGain();

        @Override
        public String toString(){

                return "Investment [symbol=" + symbol + "\n name=" + name + "\n quantity" + quantity + "\n price=" + price + "\n bookValue=" + bookValue + "]";
        }
}