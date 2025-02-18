import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static ArrayList<Savings> savingsList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Finance App!");
        
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        int userID = new Random().nextInt(10000);
        System.out.println("Your assigned User ID: " + userID);
        
        System.out.print("Create a password: ");
        String password = scanner.nextLine();
        
        System.out.print("Are you an admin? (yes/no): ");
        boolean isAdmin = scanner.nextLine().equalsIgnoreCase("yes");
        
        User user = isAdmin ? new Admin(name, userID, password) : new User(name, userID, password, false);
        users.add(user);
        
        if (!isAdmin) {
            setupUserAccount(scanner);
        }
        scanner.close();
    }
    
    private static void setupUserAccount(Scanner scanner) {
        System.out.print("Create Account Number: ");
        int accountNum = scanner.nextInt();
        
        System.out.print("Create Account PIN: ");
        int accountPIN = scanner.nextInt();
        
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        
        Account account = new Account(accountNum, accountPIN, balance);
        accounts.add(account);
        System.out.println("Account created successfully!");
        
        handleUserActions(scanner, account);
    }
    
    private static void handleUserActions(Scanner scanner, Account account) {
        while (true) {
            System.out.println("Choose an action: \n1. Deposit\n2. Withdraw\n3. Transfer Money\n4. Add Expense\n5. Add Savings\n6. Generate Report\n7. Exit");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    depositMoney(scanner, account);
                    break;
                case 2:
                    withdrawMoney(scanner, account);
                    break;
                case 3:
                    transferMoney(scanner, account);
                    break;
                case 4:
                    addExpense(scanner);
                    break;
                case 5:
                    addSavings(scanner);
                    break;
                case 6:
                    generateReport();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
    private static void depositMoney(Scanner scanner, Account account) {
        System.out.print("Enter deposit amount: ");
        double depositAmount = scanner.nextDouble();
        account.setBalance(account.getBalance() + depositAmount);
        System.out.println("New Balance: " + account.getBalance());
    }
    
    private static void withdrawMoney(Scanner scanner, Account account) {
        System.out.print("Enter withdrawal amount: ");
        double withdrawAmount = scanner.nextDouble();
        if (withdrawAmount <= account.getBalance()) {
            account.setBalance(account.getBalance() - withdrawAmount);
            System.out.println("New Balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient funds.");
        }
    }
    
    private static void transferMoney(Scanner scanner, Account account) {
        System.out.print("Enter recipient account number: ");
        int receiverAcc = scanner.nextInt();
        System.out.print("Enter amount to transfer: ");
        double transferAmount = scanner.nextDouble();
        if (transferAmount <= account.getBalance()) {
            account.setBalance(account.getBalance() - transferAmount);
            System.out.println("Transfer successful. New Balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient funds.");
        }
    }
    
    private static void addExpense(Scanner scanner) {
        System.out.print("Enter expense category: ");
        scanner.nextLine();
        String category = scanner.nextLine();
        System.out.print("Enter expense amount: ");
        double expenseAmount = scanner.nextDouble();
        expenses.add(new Expense(expenseAmount, category));
        System.out.println("Expense recorded.");
    }
    
    private static void addSavings(Scanner scanner) {
        System.out.print("Enter savings goal: ");
        double goal = scanner.nextDouble();
        Savings savings = new Savings(goal);
        savingsList.add(savings);
        System.out.println("Savings goal set.");
    }
    
    private static void generateReport() {
        try (FileWriter writer = new FileWriter("financial_report.txt")) {
            double totalExpenses = expenses.stream().mapToDouble(Expense::getExpenseAmount).sum();
            double totalSavings = savingsList.stream().mapToDouble(Savings::getCurrentSaving).sum();
            writer.write("Financial Report\n");
            writer.write("Total Expenses: " + totalExpenses + "\n");
            writer.write("Total Savings: " + totalSavings + "\n");
            System.out.println("Report generated successfully!");
        } catch (IOException e) {
            System.out.println("Error generating report.");
        }
    }
}
