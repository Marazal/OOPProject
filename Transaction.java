package proj;

class Transaction {
	    public Transaction() {
	    	
	    }

	    public void withdraw(double amount, Account account) {
	        if (account.getBalance() >= amount) {
	            account.setBalance(account.getBalance() - amount);
	        } else {
	            System.out.println("Insufficient funds");
	        }
	    }

	    public void deposit(Account account, double amount) {
	    	account.setBalance(account.getBalance() + amount);
	    }
	    
	    public void transfer(Account account, double amount, Account reciever) {
	    	if (account.getBalance() >= amount) {
	            account.setBalance(account.getBalance() - amount);
	            reciever.setBalance(reciever.getBalance() + amount);
	        } else {
	            System.out.println("Insufficient funds");
	        }
	    }
}
