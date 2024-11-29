
```markdown
# **Test Plan for ePortfolio System**

## **Overview**
The ePortfolio system allows users to manage stocks and mutual funds in a portfolio. Key operations include:
- Adding a new investment (stock or mutual fund).
- Updating the investment quantity and price.
- Calculating the total value of the portfolio.
- Displaying portfolio details.
- Handling user inputs in a menu-driven interface.

The goal of testing is to ensure that the system performs as expected for all operations.

---

## **Key Testing Scenarios**

1. **Adding an Investment**
   - Validate input for stock and mutual fund investments.
   - Ensure the system handles both investment types correctly (e.g., stock commission vs. mutual fund redemption fees).
   - Check edge cases, such as negative values or non-numeric inputs.

2. **Updating an Investment**
   - Verify that updates to quantity and price are reflected accurately in portfolio calculations.

3. **Displaying Portfolio**
   - Ensure correct display of total portfolio value and individual investment details for small and large portfolios.

4. **Selling an Investment**
   - Verify correct reduction in holdings, including handling of fees (stock commission or mutual fund redemption).
   - Test for error messages when selling quantities greater than available.

5. **Calculating Portfolio Value**
   - Confirm accurate recalculation of total portfolio value after changes to investments.

6. **Menu Navigation and Input Validation**
   - Validate all menu inputs and ensure invalid choices prompt appropriate error messages.
   - Ensure case-insensitive commands (e.g., `q`, `Q`, `quit`, `QUIT`).

---

## **Test Cases**

### **Case 1: Add a Stock Investment**
**Test Objective:** Verify the system can correctly add a stock to the portfolio.

- **Test Input:**
  - Investment Type: Stock  
  - Stock Symbol: "AAPL"  
  - Stock Quantity: 10  
  - Stock Price: 150.00  

- **Expected Output:**
  - Portfolio includes the stock with the correct quantity and price.
  - Total portfolio value is 10 × 150 = 1500.
  - Stock purchase commission is handled correctly.

---

### **Case 2: Add a Mutual Fund Investment**
**Test Objective:** Verify the system can correctly add a mutual fund to the portfolio.

- **Test Input:**
  - Investment Type: Mutual Fund  
  - Mutual Fund Name: "Vanguard 500"  
  - Quantity: 5  
  - Price per Unit: 200.00  

- **Expected Output:**
  - Portfolio includes the mutual fund with the correct quantity and price.
  - Total portfolio value is 5 × 200 = 1000.
  - Redemption fee is handled correctly.

---

### **Case 3: Update Stock Investment Price**
**Test Objective:** Verify that updating the stock price adjusts the portfolio value correctly.

- **Test Input:**
  - Stock Symbol: "AAPL"  
  - New Price: 155.00  

- **Expected Output:**
  - "AAPL" stock price is updated.
  - Portfolio value is updated to 10 × 155 = 1550.

---

### **Case 4: Sell Stock Investment**
**Test Objective:** Verify selling an investment reduces holdings and applies the correct fee.

- **Test Input:**
  - Stock Symbol: "AAPL"  
  - Quantity to Sell: 5  

- **Expected Output:**
  - Remaining "AAPL" quantity is 5.
  - Total portfolio value decreases by 5 × 155 = 775.
  - $9.99 commission fee is applied.

---

### **Case 5: Attempt to Sell More Than Available Stock**
**Test Objective:** Ensure error is displayed when attempting to sell more than available.

- **Test Input:**
  - Stock Symbol: "AAPL"  
  - Quantity to Sell: 15  

- **Expected Output:**
  - Error message: "You cannot sell more than the available quantity."
  - Portfolio remains unchanged.

---

### **Case 6: Display Portfolio with Multiple Investments**
**Test Objective:** Verify correct display of multiple investments in the portfolio.

- **Test Input:**
  - Add Stock: "AAPL", 10 shares at $150  
  - Add Mutual Fund: "Vanguard 500", 5 units at $200  

- **Expected Output:**
  - Portfolio displays both investments.
  - Total portfolio value is (10 × 150) + (5 × 200) = 1500 + 1000 = 2500.

---

### **Case 7: Invalid Input for Menu Command**
**Test Objective:** Verify system handles invalid menu inputs appropriately.

- **Test Input:**
  - Enter "exit" or "bye" as a menu command.

- **Expected Output:**
  - Error message: "Invalid option. Please enter a valid option."
  - User is prompted to re-enter a valid command.

---

### **Case 8: Quit Program**
**Test Objective:** Verify the system exits cleanly when the user opts to quit.

- **Test Input:**  
  - Enter "q" or "quit" at the main menu.

- **Expected Output:**
  - Program exits cleanly without further prompts.

---

## **Conclusion**
The **Test Plan** and **Test Cases** outlined here ensure that the ePortfolio system functions correctly across different scenarios.
