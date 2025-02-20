package bankingApp_v3.menus;

import java.util.ArrayList;
import java.util.Scanner;

import bankingApp_v3.classes.Expense;
import bankingApp_v3.classes.User;
import bankingApp_v3.utilMethods.Utilities;
import bankingApp_v3.classes.Account;

public class ManageExpense {
	static Scanner scanner = new Scanner(System.in);
	static boolean runManageExpense = true;

	// MANAGE EXPENSE ACCOUNT
	public static void manageExpense(User user, ArrayList<Account> accountsList, ArrayList<Expense> expenses) {
		System.out.print("Enter Account Number: ");
		int accountNum = scanner.nextInt();
		Account account = Utilities.findAccount(accountNum, accountsList);
		
		if (account == null) {
			System.out.println("Account not found!");
			return;
			
		}
		
        System.out.print("Enter Pin: ");
		if (scanner.nextInt() != account.getAccountPIN()) {
			System.out.println("Wrong Pin!");
			return;
		}
		
		while (runManageExpense) {
			System.out.println("\n=== Expense Management ===");
			System.out.println("1. Add Expense");
			System.out.println("2. View Expenses");
			System.out.println("3. Delete Expense");
			System.out.println("4. Return to User Menu");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1 -> addExpense(user, accountNum, account, expenses);

			case 2 -> viewExpenses(account, expenses);

			case 3 -> deleteExpense(account, expenses);

			case 4 -> {
				runManageExpense = false;
			}
			default -> System.out.println("Invalid option! Try again.");
			}
		}
	}

	public static void addExpense(User user, int accountNum, Account account, ArrayList<Expense> expenses) {
		int expenseID = expenses.size() + 1;
		System.out.print("Enter Amount: ");
		double amount = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("Enter Category: ");
		String category = scanner.nextLine();

		Expense expense = new Expense(accountNum, account.getAccountPIN(), account.getBalance(), expenseID, amount,
				category);
		expenses.add(expense);
		account.setBalance(account.getBalance() - expense.getAmount());
		System.out.println("Expense added successfully!");
	}

	public static void viewExpenses(Account account, ArrayList<Expense> expenses) {
		System.out.println("\nYour Expenses:");
		for (Expense expense : expenses) {
			expense.displayExpense(account);
			System.out.println("--------------------");
		}
	}

	public static void deleteExpense(Account account, ArrayList<Expense> expenses) {
		System.out.print("Enter Expense ID to delete: ");
		int idToDelete = scanner.nextInt();
		for (Expense expense : expenses) {
			if (expense.getExpenseID() == idToDelete) {
				account.setBalance(account.getBalance() + expense.getAmount());
			}
		}
		expenses.removeIf(expense -> expense.getExpenseID() == idToDelete);
		System.out.println("Expense deleted successfully!");
	}
}
