package bankingApp_v3.classes;

public class Savings extends Account {
    private int savingID;
    private double savingGoal;
    private double currentSaving;
    private double addedAmount;

    public Savings(int accountNum, int accountPIN, double balance, int savingID, double currentSaving) {
        super(accountNum, accountPIN, balance);
        this.savingID = savingID;
        this.currentSaving = currentSaving;
    }

    public int getSavingID() {
        return savingID;
    }

    public void setSavingID(int savingID) {
        this.savingID = savingID;
    }
    
    public double getSavingGoal() {
        return savingGoal;
    }

    public void setSavingGoal(double savingGoal) {
        this.savingGoal = savingGoal;
    }

    public double getCurrentSaving() {
        return currentSaving;
    }

    public void setCurrentSaving(double amount) {
        this.currentSaving = amount;
    }
    
    public double getAddedAmount() {
        return addedAmount;
    }

    public void setAddedAmount(double amount) {
        this.addedAmount = amount;
    }
    
    public void addToSaving(double amount) {
    	this.addedAmount = amount;
    	this.currentSaving += amount;
    }
    
    public void withdrawFromSaving(double amount) {
    	this.addedAmount = -amount;
    	this.currentSaving -= amount;
    }

    public void displaySavings(Account account) {
        System.out.println("Savings ID: " + savingID);
        System.out.println("Savings Amount: $" + getCurrentSaving());
        System.out.println("Account Number: " + account.getAccountNum());
        System.out.println("Total Account Balance: $" + account.getBalance());
    }
}
