package bankingApp_v3.utilMethods;

import java.util.ArrayList;

import bankingApp_v3.classes.Account;

public class Utilities {
	
    // FIND ACCOUNT BY NUMBER
    public static Account findAccount(int accountNum, ArrayList<Account> accountsList) {
        for (Account account : accountsList) {
            if (account.getAccountNum() == accountNum) {
                return account;
            }
        }
        return null;
    }

}
