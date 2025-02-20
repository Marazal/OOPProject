package bankingApp_v3.menus;

import java.util.ArrayList;
import java.util.Scanner;

import bankingApp_v3.classes.Account;
import bankingApp_v3.classes.Savings;
import bankingApp_v3.classes.User;
import bankingApp_v3.utilMethods.Utilities;

public class ManageSavings {
	static Scanner scanner = new Scanner(System.in);
	static boolean runManageSavings = true;
	
	 // MANAGE SAVINGS ACCOUNT
    public static void manageSavings(User user, ArrayList<Account> accountsList, ArrayList<Savings> savings) {
    	
    	System.out.print("Enter Account Number: ");
        int accountNum = scanner.nextInt();
        Account account = Utilities.findAccount(accountNum, accountsList);
 
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }
        
        System.out.print("Enter Pin: ");
        if(scanner.nextInt() != account.getAccountPIN()) {
        	System.out.println("Wrong Pin!");
        	return;
        }
    		
    	while (runManageSavings) {

            System.out.println("\n=== Savings Management ===");
            System.out.println("1. Create Savings");
            System.out.println("2. Change Saving Goal");
            System.out.println("3. Add to Savings");
            System.out.println("4. Withdraw from Savings");
            System.out.println("5. View Savings Details");
            System.out.println("6. Return to User Menu");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1 -> createSavings(user, accountNum, account, savings);
                
                case 2 -> changeSavingGoal(savings);
                
                case 3 -> addToSavings(account, savings);
                
                case 4 -> withdrawFromSavings(account, savings);
                
                case 5 -> viewSavingsDetails(account, savings);
                
                case 6 -> runManageSavings = false;
                
                
                default -> System.out.println("Invalid option! Try again.");
            }
    	}
    	
    }
    
    public static void createSavings(User user, int accountNum, Account account, ArrayList<Savings> savings) {
    	int savingId = savings.size() + 1;
    	System.out.print("Enter savings goal amount: ");
        double goal = scanner.nextDouble();
        Savings saving = new Savings(accountNum, account.getAccountPIN(), account.getBalance(), savingId, 0);
        savings.add(saving);
        saving.setSavingGoal(goal);
        System.out.print("Saving Created!");
        System.out.println("Savings ID: " + savingId);
        System.out.println("Savings goal set to: $" + goal);
    }
    
    public static void changeSavingGoal(ArrayList<Savings> savings) {
    	System.out.print("Enter savings goal ID: ");
    	int savingId = scanner.nextInt();
    	double goal = 0;
    	for (Savings saving : savings) {
        	if(saving.getSavingID() == savingId) {
        		System.out.print("Enter savings goal amount: ");
                goal = scanner.nextDouble();
        		saving.setSavingGoal(goal);
        	}
        	else {
        		System.out.println("No saving with this ID found.");
        		break;
        	}
        }
        System.out.println("Savings goal set to: $" + goal);
    }
    
    
    
    public static void addToSavings(Account account, ArrayList<Savings> savings) {
    	System.out.print("Enter savings goal ID: ");
    	int savingId = scanner.nextInt();
    	for (Savings saving : savings) {
        	if(saving.getSavingID() == savingId) {
        		System.out.print("Enter savings added amount: ");
        		saving.addToSaving(scanner.nextDouble());
        		account.setBalance(account.getBalance() - saving.getAddedAmount());
        	}
        	else {
        		System.out.println("No saving with this ID found.");
        		break;
        	}
        }

    }
    
    public static void withdrawFromSavings(Account account, ArrayList<Savings> savings) {
    	System.out.print("Enter savings goal ID: ");
    	int savingId = scanner.nextInt();
    	for (Savings saving : savings) {
        	if(saving.getSavingID() == savingId) {
        		System.out.print("Enter saving withdrawal amount: ");
        		
        		double withdrawalAmount = scanner.nextDouble();
        		
        		account.setBalance(account.getBalance() - withdrawalAmount);
        	}
        	else {
        		System.out.println("No saving with this ID found.");
        		break;
        	}
        }
    }
    
    public static void viewSavingsDetails(Account account, ArrayList<Savings> savings) {
    	System.out.print("Enter savings goal ID: ");
    	int savingId = scanner.nextInt();
    	for (Savings saving : savings) {
        	if(saving.getSavingID() == savingId) {
        		saving.displaySavings(account);
        	}
        	else {
        		System.out.println("No saving with this ID found.");
        		break;
        	}
        }
    }
}
