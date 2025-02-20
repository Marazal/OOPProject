/*
 * Ekaterina Kazakova 6720065
 * Tay Zar Aung Htet 6722113
 * Marazal Bahrainee Islam 6720051
 */

package bankingApp_v3;

import java.util.ArrayList;
import bankingApp_v3.classes.Account;
import bankingApp_v3.classes.User;
import bankingApp_v3.classes.Expense;
import bankingApp_v3.classes.Savings;

import bankingApp_v3.menus.StartLoginMenu;
 
public class Main {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static ArrayList<Savings> savings = new ArrayList<>();
    
    public static void main(String[] args) {
    	StartLoginMenu.startLoginMenu(users, accounts, expenses, savings);
    	
    }
}

/*
 * Ekaterina Kazakova 6720065
 * Tay Zar Aung Htet 6722113
 * Marazal Bahrainee Islam 6720051
 */