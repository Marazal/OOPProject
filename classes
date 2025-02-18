public class User {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

class Account {
    private int accountNum;
    private int accountPIN;
    private double accountTotalBalance;

    public Account(int accountNum, int accountPIN, double accountTotalBalance) {
        this.accountNum = accountNum;
        this.accountPIN = accountPIN;
        this.accountTotalBalance = accountTotalBalance;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public int getPIN() {
        return accountPIN;
    }

    public void setPIN(int accountPIN) {
        this.accountPIN = accountPIN;
    }

    public double getBalance() {
        return accountTotalBalance;
    }

    public void setBalance(double accountTotalBalance) {
        this.accountTotalBalance = accountTotalBalance;
    }
}

class Transaction {
    private double balance;
    private double transactionAmount;
    private int receiverAccNum;

    public Transaction(double balance, double transactionAmount, int receiverAccNum) {
        this.balance = balance;
        this.transactionAmount = transactionAmount;
        this.receiverAccNum = receiverAccNum;
    }

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

class Expense {
    private int expenseID;
    private double amount;
    private String category;

    public Expense(int expenseID, double amount, String category) {
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
}

class Savings {
    private int savingID;
    private double amount;

    public Savings(int savingID, double amount) {
        this.savingID = savingID;
        this.amount = amount;
    }

    public int getSavingID() {
        return savingID;
    }

    public void setSavingID(int savingID) {
        this.savingID = savingID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

