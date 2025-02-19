package proj;
 
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
 
public class main {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Account> accounts = new ArrayList<>();
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
        System.out.print("Enter User ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        users.add(new User(name, userID, password, false));
        System.out.println("User registered successfully!");
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
        System.out.println("Choose account type:");
        System.out.println("1. Expense Account");
        System.out.println("2. Savings Account");
        int choice = scanner.nextInt();
        System.out.print("Enter account number: ");
        int accountNum = scanner.nextInt();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
 
        if (choice == 1) {
            accounts.add(new Expense(accountNum, pin, balance, pin, balance, null));
            System.out.println("Expense Account created successfully!");
        } else if (choice == 2) {
            accounts.add(new Savings(accountNum, pin, balance, pin, balance));
            System.out.println("Savings Account created successfully!");
        } else {
            System.out.println("Invalid choice!");
        }
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
 
        System.out.println("Choose transaction:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        int choice = scanner.nextInt();
 
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
 
        if (choice == 1) {
            account.setBalance(account.getBalance() + amount);
            System.out.println("Deposit successful! New Balance: " + account.getBalance());
        } else if (choice == 2) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                System.out.println("Withdrawal successful! New Balance: " + account.getBalance());
            } else {
                System.out.println("Insufficient balance!");
            }
        } else {
            System.out.println("Invalid choice!");
        }
    }
 
    // MANAGE EXPENSE ACCOUNT
    private static void manageExpense(User user) {
        ArrayList<Expense> expenses = new ArrayList<>();
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
                    System.out.print("Enter Expense ID: ");
                    int expenseID = scanner.nextInt();
                    System.out.print("Enter Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();
                    
                    Expense expense = new Expense(user.getUserID(), 0, 0, expenseID, amount, category);
                    expenses.add(expense);
                    System.out.println("Expense added successfully!");
                }
                case 2 -> {
                    System.out.println("\nYour Expenses:");
                    for (Expense expense : expenses) {
                        expense.displayExpense();
                        System.out.println("--------------------");
                    }
                }
                case 3 -> {
                    System.out.print("Enter Expense ID to delete: ");
                    int idToDelete = scanner.nextInt();
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
        System.out.println("\n=== Savings Management ===");
        System.out.println("1. Set Savings Goal");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. View Savings Details");
        System.out.println("5. Return to User Menu");
        System.out.print("Choose an option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1 -> {
                System.out.print("Enter savings goal amount: ");
                double goal = scanner.nextDouble();
                System.out.println("Savings goal set to: $" + goal);
            }
            case 2 -> System.out.println("Deposit feature coming soon...");
            case 3 -> System.out.println("Withdraw feature coming soon...");
            case 4 -> System.out.println("Viewing savings details coming soon...");
            case 5 -> {
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