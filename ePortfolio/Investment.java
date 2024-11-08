package ePortfolio;

/**
 * Represents an investment in the portfolio
 */
//Abstract class Investment
public abstract class Investment {

        protected String symbol;
        protected String name;
        protected double price;
        protected int quantity;
        protected double bookValue;

        //Constructor
        /**
         * @param symbol The symbol of the investment
         * @param name The name of the investment
         * @param price The price of the investment
         * @param quantity The quantity of the investment
         */
        public Investment(String symbol, String name, int quantity, double price, double bookValue, String type){

                if (symbol == null || name == null) {

                        throw new IllegalArgumentException("Symbol and name cannot be null");

                }

                this.symbol = symbol;
                this.name = name;
                this.price = price;
                this.quantity = quantity;
                this.bookValue = calculateBookValue(quantity, price);
        }

        //Method to get symbol
        /**
         * @return The symbol of the investment
         */
        public String getSymbol(){

                return this.symbol;

        }

        //Method to get name
        /**
         * @return The name of the investment
         */
        public String getName(){

                return name;
        }

        //Method to get quantity
        /**
         * @return The quantity of the investment
         */
        public int getQuantity(){

                return this.quantity;

        }

        //Method to get price
        /**
         * @return The price of the investment
         */
        public double getPrice(){

                return this.price;

        }

        //Method to get book value
        /**
         * @return The book value of the investment
         */
        public double getBookValue(){

                return bookValue;

        }

        //Method to get type
        /**
         * @return The type of the investment
         */
        public abstract String getType();

        //Method to calculate value
        /**
         * @return The value of the investment
         */
        public abstract double calculateValue();

        //Method to calculate book value
        /**
         * This method calculates the book value of the investment
         * @param quantity The quantity of the investment
         * @param price The price of the investment
         * @return The book value of the investment
         */
        protected abstract double calculateBookValue(int quantity, double price);

        //public abstract double getGain();
        /**
         * This method allows the user to buy an investment
         * The user can buy a stock or mutual fund
         * @param quantity The quantity of the investment
         * @param price The price of the investment
         */
        public void buy(int quantity, double price){

                double additionalBookValue = calculateBookValue(quantity, price);
                
                this.bookValue += additionalBookValue;
                this.quantity += quantity;
                this.price = price;

        }

        //Method to update price
        /**
         * @param price The price of the investment
         */
        public void updatePrice(double price){

                this.price = price;

        }
        
        //Method to set symbol
        /**
         * @param symbol The symbol of the investment
         */
        public void setSymbol(String symbol){
                if (symbol == null) {
                        throw new IllegalArgumentException("Symbol cannot be null");
                }
                this.symbol = symbol;
        }

        //Method to set quantity
        /**
         * @param quantity The quantity of the investment
         */
        public void setQuantity(int quantity){
                
                this.quantity = quantity;
        }

        //Method to set price
        /**
         * @param price The price of the investment
         */
        public void setPrice(double price){
                
                this.price = price;
        }

        //Method to set book value
        /**
         * @param bookValue The book value of the investment
         */
        public void setBookValue(double bookValue){
                
                this.bookValue = bookValue;
        }
        

        //Method to sell investment
        /**
         * This method allows the user to sell an investment
         * The user can sell a stock or mutual fund
         * @param quantity The quantity of the investment
         * @param price The price of the investment
         */
        public void sell(int quantity, double price){

                if (this.quantity >= quantity){
                        this.quantity -= quantity;
                        this.price = price;
                }else{
                        System.out.println("Not enough Quantity to sell!!");
                }
        }

        //Method to set name
        /**
         * @param name The name of the investment
         */
        public void setName(String name){
                if (name == null) {
                        throw new IllegalArgumentException("Name cannot be null");
                }
                this.name = name;
        }

        //Method to get gain
        /**
         * @return The gain of the investment
         */
        public abstract double getGain();

        //Method to get type
        /**
         * @return The type of the investment
         */
        @Override
        public String toString(){

                return "Investment [symbol=" + symbol + 
                        "\n name=" + name +
                        "\n quantity" + quantity + 
                        "\n price=" + price +
                        "\n bookValue=" + bookValue + "]";
        }
}
