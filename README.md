## Table of Contents
1. [Introduction](#introduction)
2. [Problem Statement](#problem-statement)
3. [Assumptions and Limitations](#assumptions-and-limitations)
4. [How it works](#how-it-works)
5. [Quit the program](#quit-the-program)
6. [Setup](#setup)
7. [Files](#files)
8. [Compiling Program](#compiling-program)
9. [Operations](#operations)
10. [Test Plan and Output](#test-plan-and-output)
11. [Owner](#owner)

## Introduction
Welcome to the ePortfolio project! This Java-based application is designed to help investors manage their portfolios, specifically focusing on stocks and mutual funds. The platform provides tools and insights to assist users in making informed investment decisions.

## Problem Statement
The ePortfolio project aims to provide a user-friendly interface for managing investment portfolios. Users can add, remove, and search for stocks and mutual funds, as well as view their total gains and individual asset performance.

## Assumptions and Limitations
- The application does not persist data between sessions.
- Only basic stock and mutual fund information is managed.
- The application assumes valid input from users.

## How it works
The ePortfolio application allows users to:
- Add stocks and mutual funds to their portfolio.
- Remove assets from their portfolio.
- Search for assets based on various criteria.
- View the total gain of their portfolio.
- Display detailed information about individual assets.

## Quit the program
To exit the program, simply type `quit` at any prompt. Please note that all entered values will be lost upon exiting as the program does not save data persistently.

## Setup
To set up the project, follow these steps:
1. Navigate to the project directory.
2. Ensure you have Java installed on your machine.

## Files
- `Portfolio.java`: Contains the main logic for managing the portfolio.
- `Stock.java`: Represents a stock in the portfolio.
- `MutualFund.java`: Represents a mutual fund in the portfolio.
- `main.java`: Entry point of the application.

## Compiling Program
To compile the program, navigate to the project directory and run:

```sh
javac *.java
```

## Running the Program
To run the compiled program, use the following command:
```sh
java main
```
## Another way to run Pogram
To run with JAR File you need to use java versoin 17.0.12 at minimum.
```sh
java -jar eportfolio.jar
```

## Operations
The ePortfolio application supports the following operations:
- `buy`: Buy a new stock or mutual fund to the portfolio.
- `sell`: Sell an existing asset from the portfolio.
- `update`: Update value to the existing stocks or mutual funds.
- `search`: Serch for assets based on criteria such as symbol or name.
- `gain`: Calculate and display the total gain of the portfolio.
- `quit`: To exit the program.

## Test Plan and Output
All the Test plan and Expected outputs are in Text file. <br>
for Test plan  follow *testplan.txt* <br>
for expected output follow *testPlan_expectedOutput.txt*

## Owner
This project is maintained by Bharat Garsondiya. For any questions or contributions, please contact bgarsond@uoguelph.ca.