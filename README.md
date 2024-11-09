
# ePortfolio System

## Overview
The ePortfolio System is a Java-based application for managing and tracking investments in stocks and mutual funds. It allows users to add, update, sell, and view investments while calculating the total portfolio value. This project supports fundamental investment management features, including automatic handling of commissions and fees specific to each investment type.

## Features
- **Add Investment**: Users can add new stocks or mutual funds to their portfolio.
- **Update Investment**: Update the quantity and price of existing investments.
- **Sell Investment**: Sell part or all of a stock or mutual fund holding with applicable fees.
- **Display Portfolio**: View the total value of the portfolio and details of each investment.

## Prerequisites
- **Java Development Kit (JDK)** version 8.

## Setup
1. **Compile the Code**: Compile the Java files.
   ```bash
   javac ePortfolio/*.java
   ```

2. **Run the Application**: Start the program by running the main file.
   ```bash
   java ePortfolio.Main 2430
   ```

2. **Run the Application with JAR file**: Start the program by running the main file.
   ```bash
   java jar ePortfolio.jar 2430
   ```

## Usage
The ePortfolio application operates through a menu-driven interface with the following commands:

- **Add Investment**: Add either a stock or mutual fund by specifying type, symbol, quantity, and price.
- **Update Investment**: Update the price or quantity of an existing investment by providing the investment symbol.
- **Sell Investment**: Sell a specified quantity of an investment. Stocks incur a commission fee, while mutual funds incur a redemption fee.
- **Display Portfolio**: Displays all investments along with the current portfolio value.
- **Quit**: Exit the application by entering “q” or “quit” (case-insensitive).

### Input Handling
The application is designed with defensive programming principles to handle a wide range of inputs:
- Commands are case-insensitive (e.g., "Quit" and "quit" are both valid).
- Invalid inputs prompt the user to re-enter values until valid data is provided.
- When adding or selling investments, invalid or negative quantities/prices are rejected with an error message.

## Testing

For detailed test scenarios, refer to the provided **Test Plan** document.
