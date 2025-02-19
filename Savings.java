package proj;

class Savings extends Account {
    private int savingID;
    private double amount;

    public Savings(int accountNum, int accountPIN, double balance, int savingID, double amount) {
        super(accountNum, accountPIN, balance);
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

    public void deposit(double depositAmount) {
        if (depositAmount > 0) {
            this.amount += depositAmount; // Increase savings amount
            setBalance(getBalance() + depositAmount); // Increase account balance
            System.out.println("Deposited $" + depositAmount + " into savings.");
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double withdrawAmount) {
        if (withdrawAmount > 0 && withdrawAmount <= amount) {
            this.amount -= withdrawAmount; // Reduce savings amount
            setBalance(getBalance() - withdrawAmount); // Reduce account balance
            System.out.println("Withdrew $" + withdrawAmount + " from savings.");
        } else {
            System.out.println("Invalid withdraw amount or insufficient funds.");
        }
    }

    public void displaySavings() {
        System.out.println("Savings ID: " + savingID);
        System.out.println("Savings Amount: $" + amount);
        System.out.println("Account Number: " + getAccountNum());
        System.out.println("Total Account Balance: $" + getBalance());
    }
}
