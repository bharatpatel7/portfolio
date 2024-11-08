
### **Test Plan for ePortfolio System**

#### **Overview**
The ePortfolio system allows users to manage stocks and mutual funds in a portfolio. The key operations include:
- Adding a new investment (stock or mutual fund)
- Updating the investment quantity and price
- Calculating the total value of the portfolio
- Displaying portfolio details
- Handling user inputs in a menu-driven interface

The goal of testing is to ensure that the system performs as expected for all operations.

#### **Key Testing Scenarios**

1. **Adding an Investment**
   - Validating input for stock and mutual fund investments.
   - Ensuring the system handles both types correctly (e.g., stock commission vs. mutual fund redemption fees).
   - Checking for edge cases such as negative values or non-numeric inputs.

2. **Updating an Investment**
   - Verifying that updating an investment correctly adjusts its quantity and price.
   - Ensuring that updating a stock’s price or mutual fund’s quantity reflects accurately in portfolio calculations.

3. **Displaying Portfolio**
   - Checking that the portfolio displays the correct total value, including individual investment details.
   - Ensuring the portfolio display handles both small and large portfolios correctly.

4. **Selling an Investment**
   - Verifying that selling an investment reduces the portfolio’s holdings correctly, including commissions and fees.
   - Ensuring that attempting to sell more than the available quantity results in an error message.

5. **Calculating Portfolio Value**
   - Ensuring that the portfolio’s total value is calculated correctly after every change in investment value.

6. **Menu Navigation and Input Validation**
   - Testing menu inputs for correctness, ensuring that invalid choices prompt the user to re-enter values.
   - Ensuring case-insensitivity for commands (e.g., ‘q’, ‘Q’, ‘quit’, ‘QUIT’ should all work to quit).

---

### **Test Cases**

#### **Case 1: Add a Stock Investment**
**Test Objective:** the system can correctly add a stock to the portfolio.

- **Test Input:**
  - Investment Type: Stock
  - Stock Symbol: "AAPL"
  - Stock Quantity: 10
  - Stock Price: 150.00

- **Expected Output:**
  - Portfolio should include the stock with the correct quantity and price.
  - The total portfolio value should reflect the stock’s value (10 * 150 = 1500).
  - Correct handling of commission for stock purchase.

---

#### **Case 2: Add a Mutual Fund Investment**
**Test Objective:** the system can correctly add a mutual fund to the portfolio.

- **Test Input:**
  - Investment Type: MutualFund
  - Mutual Fund Name: "Vanguard 500"
  - Quantity: 5
  - Price per Unit: 200.00

- **Expected Output:**
  - Portfolio should include the mutual fund with the correct quantity and price.
  - The total portfolio value should reflect the mutual fund value (5 * 200 = 1000).
  - Correct handling of redemption fee for mutual fund purchase.

---

#### **Case 3: Update Stock Investment Price**
**Test Objective:** Test if updating the price of a stock correctly updates the total portfolio value.

- **Test Input:**
  - Stock Symbol: "AAPL"
  - New Price: 155.00

- **Expected Output:**
  - The new price for "AAPL" should be updated.
  - Portfolio value should update to 10 * 155 = 1550.

---

#### **Case 4: Sell Stock Investment**
**Test Objective:** Ensure that selling an investment properly reduces the portfolio holdings and applies the correct fee.

- **Test Input:**
  - Sell Stock: "AAPL"
  - Quantity to Sell: 5

- **Expected Output:**
  - Portfolio should reflect that only 5 shares of "AAPL" remain.
  - The total portfolio value should decrease by the amount of the sale (5 * 155 = 775).
  - Ensure commission fee of $9.99 is applied to the sale.

---

#### **Case 5: Attempt to Sell More Than Available Stock**
**Test Objective:** Ensure that attempting to sell more stock than available results in an error.

- **Test Input:**
  - Sell Stock: "AAPL"
  - Quantity to Sell: 15 (more than 10 in the portfolio)

- **Expected Output:**
  - Display error message: "You cannot sell more than the available quantity."
  - Portfolio should remain unchanged.

---

#### **Case 6: Display Portfolio with Multiple Investments**
**Test Objective:** Verify that the system correctly displays multiple investments in the portfolio.

- **Test Input:**
  - Add Stock: "AAPL" with 10 shares at $150
  - Add Mutual Fund: "Vanguard 500" with 5 units at $200

- **Expected Output:**
  - Portfolio display should list both the stock and mutual fund.
  - Total portfolio value should be correctly calculated as (10 * 150) + (5 * 200) = 1500 + 1000 = 2500.

---

#### **Case 7: Invalid Input for Menu Command**
**Test Objective:** Test the program’s ability to handle invalid menu input.

- **Test Input:** 
  - Enter "exit" or "bye" as a command.

- **Expected Output:**
  - Display error message: "Invalid option. Please enter a valid option."
  - Prompt user to enter a valid option.

---

#### **Case 8: Quit Program**
**Test Objective:** Ensure that the program quits correctly when the user opts to quit.

- **Test Input:** 
  - Enter "q" or "quit" at the main menu.

- **Expected Output:**
  - Program should exit cleanly.
  - No further prompts or operations should occur.


---

### **Conclusion**

The **Test Plan** and **Test Cases** outlined here ensure that the ePortfolio system functions correctly across different scenarios.