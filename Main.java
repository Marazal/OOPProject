package proj;
 
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
 
public class Main {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static ArrayList<Savings> savings = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Banking System ===");
            System.out.println("1. Register User");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> registerUser();
                case 2 -> loginUser();
                case 3 -> {
                    System.out.println("Exiting system...");
                    return;
                }
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }
    // REGISTER A NEW USER
    private static void registerUser() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        int userID = users.size();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        users.add(new User(name, userID, password));
        System.out.println("User registered successfully!");
        System.out.println("Name: " + name);
        System.out.println("User ID: " + userID);
        System.out.println("Password: " + password);
    }
 
    // LOGIN EXISTING USER
    private static void loginUser() {
        System.out.print("Enter User ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
 
        User loggedInUser = null;
        for (User user : users) {
            if (user.getUserID() == userID && user.getPassword().equals(password)) {
                loggedInUser = user;
                break;
            }
        }
 
        if (loggedInUser == null) {
            System.out.println("Invalid credentials! Try again.");
            return;
        }
 
        System.out.println("Login successful! Welcome, " + loggedInUser.getName());
        userMenu(loggedInUser);
    }
 
	private static void userMenu(User user) {
        while (true) {
            System.out.println("\n=== User Menu ===");
            System.out.println("1. Create Account");
            System.out.println("2. Perform Transactions");
            System.out.println("3. Manage Expense Account");
            System.out.println("4. Manage Savings Account");
            System.out.println("5. Generate Report");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
 
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
 
            switch (choice) {
                case 1 -> createAccount(user);
                case 2 -> performTransaction(user);
                case 3 -> manageExpense(user);
                case 4 -> manageSavings(user);
                case 5 -> generateReport(user);
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }
 
    // CREATE ACCOUNT
    private static void createAccount(User user) {
        int accountNum = 1000000 + accounts.size();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        accounts.add(new Account(accountNum, pin, balance));
    }
 
    // PERFORM TRANSACTIONS
    private static void performTransaction(User user) {
        System.out.print("Enter Account Number: ");
        int accountNum = scanner.nextInt();
        Account account = findAccount(accountNum);
 
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
            Account reciever = findAccount(recieverNum);
     
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
 
    // MANAGE EXPENSE ACCOUNT
    private static void manageExpense(User user) {
    	System.out.print("Enter Account Number: ");
        int accountNum = scanner.nextInt();
        Account account = findAccount(accountNum);
 
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }
        if(scanner.nextInt() != account.getAccountPIN()) {
        	System.out.println("Wrong Pin!");
        	return;
        }
        while (true) {
            System.out.println("\n=== Expense Management ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Return to User Menu");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1 -> {
                    int expenseID = expenses.size() + 1;
                    System.out.print("Enter Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();
                    
                    Expense expense = new Expense(accountNum, account.getAccountPIN(), account.getBalance(), expenseID, amount, category);
                    expenses.add(expense);
                    account.setBalance(account.getBalance() - expense.getAmount());
                    System.out.println("Expense added successfully!");
                }
                case 2 -> {
                    System.out.println("\nYour Expenses:");
                    for (Expense expense : expenses) {
                        expense.displayExpense(account);
                        System.out.println("--------------------");
                    }
                }
                case 3 -> {
                    System.out.print("Enter Expense ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    for (Expense expense : expenses) {
                        if(expense.getExpenseID() == idToDelete) {
                        	account.setBalance(account.getBalance() + expense.getAmount());
                        }
                    }
                    expenses.removeIf(expense -> expense.getExpenseID() == idToDelete);
                    System.out.println("Expense deleted successfully!");
                }
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }
 
    // MANAGE SAVINGS ACCOUNT
    private static void manageSavings(User user) {
    	System.out.print("Enter Account Number: ");
        int accountNum = scanner.nextInt();
        Account account = findAccount(accountNum);
 
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }
        if(scanner.nextInt() != account.getAccountPIN()) {
        	System.out.println("Wrong Pin!");
        	return;
        }
        System.out.println("\n=== Savings Management ===");
        System.out.println("1. Create Savings");
        System.out.println("2. Change Saving Goal");
        System.out.println("3. Add to Saving");
        System.out.println("4. Withdraw from Saving");
        System.out.println("5. View Savings Details");
        System.out.println("6. Return to User Menu");
        System.out.print("Choose an option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1 -> {
            	int savingId = savings.size() + 1;
            	System.out.print("Enter savings goal amount: ");
                double goal = scanner.nextDouble();
                Savings saving = new Savings(accountNum, account.getAccountPIN(), account.getBalance(), savingId, 0);
                savings.add(saving);
                saving.setSavingGoal(goal);
                System.out.print("Saving Created!");
                System.out.println("Savings goal set to: $" + goal);
            }
            case 2 -> {
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
            case 3 -> {
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
            case 4 -> {
            	System.out.print("Enter savings goal ID: ");
            	int savingId = scanner.nextInt();
            	for (Savings saving : savings) {
                	if(saving.getSavingID() == savingId) {
                		System.out.print("Enter saving widrawl amount: ");
                		saving.withdrawFromSaving(scanner.nextDouble());
                		account.setBalance(account.getBalance() - saving.getAccountNum());
                	}
                	else {
                		System.out.println("No saving with this ID found.");
                		break;
                	}
                }
            }
            case 5 -> {
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
            case 6 -> {
                return;
            }
            default -> System.out.println("Invalid option! Try again.");
        }
    }
 
    // GENERATE REPORT AND SAVE TO FILE
    private static void generateReport(User user) {
        try {
            FileWriter writer = new FileWriter(user.getName() + "_report.txt");
            writer.write("=== Banking Report ===\n");
            writer.write("User: " + user.getName() + "\n");
            writer.write("Accounts:\n");
 
            for (Account account : accounts) {
                writer.write("Account Number: " + account.getAccountNum() + " | Balance: $" + account.getBalance() + "\n");
            }
 
            writer.close();
            System.out.println("Report generated successfully! File saved as " + user.getName() + "_report.txt");
        } catch (IOException e) {
            System.out.println("Error generating report!");
        }
    }
 
    // FIND ACCOUNT BY NUMBER
    private static Account findAccount(int accountNum) {
        for (Account account : accounts) {
            if (account.getAccountNum() == accountNum) {
                return account;
            }
        }
        return null;
    }
}