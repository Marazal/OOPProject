package proj;

class Transaction {

	    public double balance;
	    public  double transactionAmount;
	    public int receiverAccNum;

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
