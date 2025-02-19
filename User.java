package proj;

public class User {
    private String name;
    private int userID;
    private String password;
    private boolean isAdmin;

    public User(String name, int userID, String password, boolean isAdmin) {
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

