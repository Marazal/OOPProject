package bankingApp_v3.classes;

public class Account {
	
    private int accountNum;
    private int accountPIN;
    private double balance;

    public Account(int accountNum, int accountPIN, double balance) {
        this.accountNum = accountNum;
        this.accountPIN = accountPIN;
        this.balance = balance;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public int getAccountPIN() { 
        return accountPIN;
    }

    public void setAccountPIN(int accountPIN) {
        this.accountPIN = accountPIN;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}