package proj;

import java.util.ArrayList;

class Admin extends User {
    private boolean isAdmin;
    
    // Predefined admin credentials
    private static final String ADMIN_NAME = "AdminUser";
    private static final int ADMIN_USER_ID = 9999;
    private static final String ADMIN_PASSWORD = "admin123";

    // Constructor: Only grants admin access if credentials match
    protected Admin(String name, int userID, String password, boolean isAdmin) {
        super(name, userID, password, isAdmin);
        this.isAdmin = validateAdmin(name, userID, password);
    }

    // Method to validate admin credentials
    private boolean validateAdmin(String name, int userID, String password) {
        return name.equals(ADMIN_NAME) && userID == ADMIN_USER_ID && password.equals(ADMIN_PASSWORD);
    }

    // Getter for isAdmin
    public boolean getAdmin() {
        return isAdmin;
    }

    // Admin-only functions
    void addUser(ArrayList<User> users, User user) {
        if (isAdmin) {
            users.add(user);
            System.out.println("User added successfully.");
        } else {
            System.out.println("Access Denied! Invalid Admin Credentials.");
        }
    }

    void deleteUser(ArrayList<User> users, User user) {
        if (isAdmin) {
            if (users.remove(user)) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("User not found.");
            }
        } else {
            System.out.println("Access Denied! Invalid Admin Credentials.");
        }
    }

    void changeUserName(User user, String newName) {
        if (isAdmin) {
            user.setName(newName);
            System.out.println("User name changed successfully.");
        } else {
            System.out.println("Access Denied! Invalid Admin Credentials.");
        }
    }

    void changePassword(User user, String newPassword) {
        if (isAdmin) {
            user.setPassword(newPassword);
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Access Denied! Invalid Admin Credentials.");
        }
    }

    void searchUser(ArrayList<User> users, int userID, int accountNum) {
        if (isAdmin) {
            for (User user : users) {
                if (user.getUserID() == userID) {
                    System.out.println("User Found: " + user.getName() + ", Account Number: " + accountNum);
                    return;
                }
            }
            System.out.println("User not found.");
        } else {
            System.out.println("Access Denied! Invalid Admin Credentials.");
        }
    }
}
