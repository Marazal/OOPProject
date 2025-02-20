package bankingApp_v3.classes;

import java.time.LocalDate;

public class Expense extends Account {
    private int expenseID;
    private double amount;
    private String category;
    private LocalDate date;

    public Expense(int accountNum, int accountPIN, double balance, int expenseID, double amount, String category) {
        super(accountNum, accountPIN, balance); // Call parent constructor
        this.expenseID = expenseID;
        this.amount = amount;
        this.category = category;
        this.date = LocalDate.now();
    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public void displayExpense(Account account) {
        System.out.println("Expense ID: " + expenseID);
        System.out.println("Expense Date: " + getDate());
        System.out.println("Amount: $" + amount);
        System.out.println("Category: " + category);
        System.out.println("Account Number: " + account.getAccountNum());
        System.out.println("Remaining Balance: $" + account.getBalance());
    }
    
    public LocalDate getDate() {
    	return date;
    }
    
    public void setDate() {
    	this.date = LocalDate.now();
    }
}