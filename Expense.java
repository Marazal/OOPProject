package proj;

class Expense extends Account {
    private int expenseID;
    private double amount;
    private String category;

    public Expense(int accountNum, int accountPIN, double balance, int expenseID, double amount, String category) {
        super(accountNum, accountPIN, balance); // Call parent constructor
        this.expenseID = expenseID;
        this.amount = amount;
        this.category = category;
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
    
    public void displayExpense() {
        System.out.println("Expense ID: " + expenseID);
        System.out.println("Amount: $" + amount);
        System.out.println("Category: " + category);
        System.out.println("Account Number: " + getAccountNum()); // From parent class
        System.out.println("Remaining Balance: $" + getBalance());
    }
}
