package bankingApp_v3.menus;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bankingApp_v3.classes.Account;
import bankingApp_v3.classes.Expense;
import bankingApp_v3.classes.Savings;
import bankingApp_v3.classes.User;
import bankingApp_v3.utilMethods.Utilities;
import bankingApp_v3.classes.Transaction;

public class UserMainMenu {

	static Scanner scanner = new Scanner(System.in);
	static boolean runUserMainMenu = true;

	public static void userMainMenu(User currentUser, ArrayList<Account> accountsList, ArrayList<Expense> expenses, ArrayList<Savings> savings) {		
		
	     while (runUserMainMenu) {
	    	 
	            System.out.println("\n=== User Menu ===");
	            System.out.println("1. Create Expense and/or Saving Account");
	            System.out.println("2. Perform Transactions");
	            System.out.println("3. Manage Expense Account");
	            System.out.println("4. Manage Savings Account");
	            System.out.println("5. Generate Report");
	            System.out.println("6. Logout");
	            System.out.print("Choose an option: ");
	 
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline
	 
	            switch (choice) {
	                case 1 -> createAccount(currentUser, accountsList);
	                
	                case 2 -> performTransaction(currentUser, accountsList);
	                
	                // These methods are complex, and already have their own branches inside so, they are implemented separately
	                // in another file.
	                case 3 -> ManageExpense.manageExpense(currentUser, accountsList, expenses);
	                case 4 -> ManageSavings.manageSavings(currentUser, accountsList, savings);
	                
	                case 5 -> generateReport(currentUser, accountsList);
	                
	                case 6 -> {
	                    System.out.println("Logging out...");
	                    runUserMainMenu = false;
	                }
	                default -> System.out.println("Invalid option! Try again.");
	            }
	        }
	}

	// CREATE ACCOUNT
	public static void createAccount(User user, ArrayList<Account> accountsList) {
		
        int accountNum = 1000000 + accountsList.size();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        accountsList.add(new Account(accountNum, pin, balance));
        
		System.out.println("Account created successfully!");
		System.out.println("Account ID: " + accountNum);
		System.out.println("Balance: $" + balance);

	}
	
	// PERFORM TRANSACTIONS
    public static void performTransaction(User user, ArrayList<Account> accountsList) {
    	
        System.out.print("Enter Account Number: ");
        int accountNum = scanner.nextInt();
        Account account = Utilities.findAccount(accountNum, accountsList);
 
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }
        
        Transaction transaction = new Transaction();
        System.out.println("Choose transaction:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        int choice = scanner.nextInt();
 
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter Pin: ");
        if(scanner.nextInt() != account.getAccountPIN()) {
        	System.out.println("Wrong Pin!");
        	return;
        }
        if (choice == 1) {
            transaction.deposit(account, amount);
            System.out.println("Deposit successful! New Balance: " + account.getBalance());
        }
        else if (choice == 2) {
        	transaction.withdraw(amount, account);
        	System.out.println("Withdrawal successful! New Balance: " + account.getBalance());
        }
        else if (choice == 3) {
        	System.out.print("Enter the Account Number you want to Transfer: ");
            int recieverNum = scanner.nextInt();
            Account reciever = Utilities.findAccount(recieverNum, accountsList);
     
            if (reciever == null) {
                System.out.println("Account not found!");
                return;
            }
            transaction.transfer(account, amount, reciever);
        	System.out.println("Transfer successful! New Balance: " + account.getBalance());
        }
        else {
            System.out.println("Invalid choice!");
        }
     
    }
    
    // Manage Expenses are implemented in their own class files
    
    // Manage Savings are implemented in their own class files

	
    // GENERATE REPORT AND SAVE TO FILE
    public static void generateReport(User user, ArrayList<Account> accountsList) {
        try {
            FileWriter writer = new FileWriter(user.getName() + "_report.txt");
            
            writer.write("=== Banking Report ===\n");
            writer.write("User: " + user.getName() + "\n");
            writer.write("Accounts:\n");
 
            for (Account account : accountsList) {
                writer.write("Account Number: " + account.getAccountNum() + " | Balance: $" + account.getBalance() + "\n");
            }
 
            writer.close();
            
            System.out.println("Report generated successfully! File saved as " + user.getName() + "_report.txt");
            
        } catch (IOException e) {
            System.out.println("Error generating report!");
        }
    }
	

}
