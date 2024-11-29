
```markdown
# ePortfolio System

## Overview
The **ePortfolio System** is a Java-based application for managing and tracking investments in stocks and mutual funds. It provides users with the ability to add, update, sell, and view investments while calculating the total portfolio value. This system now includes a graphical user interface (GUI) and enhanced exception handling, making it more robust and user-friendly.

## Features
### Core Features
- **Add Investment**: Users can add new stocks or mutual funds to their portfolio.
- **Update Investment**: Update the price of existing investments with ease.
- **Sell Investment**: Sell a specified quantity of a stock or mutual fund. Applicable fees are automatically calculated:
  - Stocks incur a commission fee.
  - Mutual funds incur a redemption fee.
- **Display Portfolio**: View the total value of the portfolio and individual investment details.
- **Search Investments**: Search for investments by various criteria such as symbol, name, or price range.
- **Get Total Gain**: Calculate the overall portfolio gain, with detailed individual gains displayed.

### Additional Features in Assignment Three
- **Graphical User Interface (GUI)**:
  - A menu-driven interface allows intuitive navigation of features.
  - Interactive forms for buying, selling, updating, searching, and viewing total portfolio gains.
  - Scrollable text areas for displaying detailed feedback or results.
- **Exception Handling**:
  - Ensures robust input validation for all operations.
  - Handles incorrect input gracefully, allowing users to re-enter values.

## Prerequisites
- **Java Development Kit (JDK)** version 8 or higher.

## Setup
### Compile the Code
To compile the application, navigate to the project directory and run:
```bash
javac ePortfolio/*.java
```

### Run the Application
Start the application using:
```bash
java ePortfolio.Main <optional-arguments>
```

Alternatively, run the application using the JAR file:
```bash
java -jar ePortfolio.jar
```

## Usage
### Commands Menu
The application offers the following menu options:
1. **Buy Investment**:
   - Select the investment type (Stock/Mutual Fund) using a dropdown.
   - Enter details (symbol, name, quantity, price) in the provided fields.
   - Buttons:
     - **Reset**: Clears all input fields.
     - **Buy**: Adds a new investment or updates an existing one. Invalid input triggers an error message in the feedback area.

2. **Sell Investment**:
   - Enter details (symbol, quantity, price).
   - Buttons:
     - **Reset**: Clears all input fields.
     - **Sell**: Sells the specified investment and displays the result or error message.

3. **Update Investments**:
   - View investments sequentially using "Prev" and "Next" buttons.
   - Modify the price field and save changes.
   - Buttons:
     - **Prev/Next**: Navigate through the list of investments.
     - **Save**: Updates the price for the current investment.

4. **Get Total Gain**:
   - Displays the overall portfolio gain in a read-only field.
   - Shows individual gains for each investment in a scrollable text area.

5. **Search Investments**:
   - Enter search criteria (symbol, name, price range) in the input fields.
   - Displays matching investments in the results area.

6. **Quit**:
   - Exits the application.

### Input Handling
- **Case-Insensitive Commands**: e.g., "Quit" and "quit" are both valid.
- **Error Handling**:
  - Invalid or negative quantities/prices are rejected.
  - Input type mismatches are caught, and users are prompted to retry.

## Code Highlights
### Object-Oriented Design
- **Abstract Classes and Polymorphism**:
  - `Investment` is an abstract class with subclasses `Stock` and `MutualFund`.
  - Common functionality is defined in `Investment` and overridden as needed.
  - Abstract methods enable polymorphism for streamlined operations.
  
- **HashMap Indexing**:
  - Maintains a keyword index for fast searches.
  
- **File Handling**:
  - Investments are read/written to files for persistent storage.

### Exception Handling
- Ensures data integrity by validating inputs and handling errors at runtime.
- Invalid operations trigger exceptions with detailed feedback.

## Testing
Comprehensive test scenarios are provided in the **Test Plan** document. 


