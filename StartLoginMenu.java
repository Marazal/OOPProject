package bankingApp_v3.menus;

import java.util.ArrayList;
import java.util.Scanner;

import bankingApp_v3.classes.Account;
import bankingApp_v3.classes.Expense;
import bankingApp_v3.classes.Savings;
import bankingApp_v3.classes.User;

public class StartLoginMenu {
	static Scanner scanner = new Scanner(System.in);

	public static void startLoginMenu(ArrayList<User> usersList, ArrayList<Account> accountsList, ArrayList<Expense> expenses, ArrayList<Savings> savings) {
		
		boolean runStartLoginMenu = true;
		
        while (runStartLoginMenu) {
            System.out.println("\n=== Banking System ===");
            System.out.println("1. Register User");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1 -> registerUser(usersList);
                
                case 2 -> loginUser(usersList, accountsList, expenses, savings);
                
                case 3 -> {
                    System.out.println("Exiting system...");
                    runStartLoginMenu = false;
                }
                default -> System.out.println("Invalid option! Try again.");
            }
           
        }
	}
	
	
	 // REGISTER A NEW USER
	public static void registerUser(ArrayList<User> usersList) {
	
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        int userID = usersList.size();
        System.out.print("Enter password: ");
        
        String password = scanner.nextLine();
        usersList.add(new User(name, userID, password));
        
        System.out.println("User registered successfully!");
        System.out.println("Name: " + name);
        System.out.println("User ID: " + userID);
        System.out.println("Password: " + password);
	}
	
    // LOGIN EXISTING USER  --> this one leads to UserMainMenu
    public static void loginUser(ArrayList<User> usersList, ArrayList<Account> accountsList, ArrayList<Expense> expenses, ArrayList<Savings> savings) {
       
        System.out.print("Enter User ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
 
        User loggedInUser = null;
        for (User user : usersList) {
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
        
        // This leads to another menu
        UserMainMenu.userMainMenu(loggedInUser, accountsList, expenses, savings);
    }
}
