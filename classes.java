import java.util.ArrayList;

class Account {
    protected int accountNum;
    protected int accountPIN;
    protected double balance;

    public Account(int accountNum, int accountPIN, double balance) {
        this.accountNum = accountNum;
        this.accountPIN = accountPIN;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

class SavingsAccount extends Account {
    private double savingsGoal;

    public SavingsAccount(int accountNum, int accountPIN, double balance, double savingsGoal) {
        super(accountNum, accountPIN, balance);
        this.savingsGoal = savingsGoal;
    }

    public void addToSavings(double amount) {
        this.balance += amount;
        System.out.println("Savings updated: " + this.balance);
    }
}

class ExpenseAccount extends Account {
    private ArrayList<String> categories = new ArrayList<>();
    private ArrayList<Double> expenses = new ArrayList<>();

    public ExpenseAccount(int accountNum, int accountPIN, double balance) {
        super(accountNum, accountPIN, balance);
    }

    public void addExpense(String category, double amount) {
        categories.add(category);
        expenses.add(amount);
        this.balance -= amount;
        System.out.println("Expense recorded under " + category + ": " + amount);
    }
}

class User {
    private String name;
    private int userID;
    private String password;
    private boolean isAdmin;

    public User(String name, int userID, String password, boolean isAdmin) {
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}

class Admin extends User {
    public Admin(String name, int userID, String password) {
        super(name, userID, password, true);
    }

    public void addUser(User user) {
        System.out.println("User " + user.getName() + " added.");
    }

    public void deleteUser(User user) {
        System.out.println("User " + user.getName() + " deleted.");
    }
}

class Transaction {
    public double withdraw(double balance, double transactionAmount) {
        if (balance >= transactionAmount) {
            return balance - transactionAmount;
        } else {
            System.out.println("Insufficient funds");
            return balance;
        }
    }

    public double deposit(double balance, double transactionAmount) {
        return balance + transactionAmount;
    }
}

class Report {
    private int reportID;
    private String reportType;

    public Report(int reportID, String reportType) {
        this.reportID = reportID;
        this.reportType = reportType;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}


