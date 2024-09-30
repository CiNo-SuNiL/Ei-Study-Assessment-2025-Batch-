import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, User> users;
    private User loggedInUser;

    public AuthService() {
        // Hardcoded users (in a real system, you'd pull these from a database)
        users = new HashMap<>();
        users.put("admin", new User("admin", "admin123", "Admin"));
        users.put("cino", new User("cino", "cino123", "User"));
    }

    public boolean login(String username, String password) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.validateCredentials(username, password)) {
                loggedInUser = user;
                System.out.println("Logged in as " + username + " (" + user.getRole() + ")");
                System.out.println();
                return true;
            }
        }
        System.out.println("Invalid username or password.");
        System.out.println();
        return false;
    }

    public void logout() {
        if (loggedInUser != null) {
            System.out.println("User " + loggedInUser.getUsername() + " logged out.");
            System.out.println();
            loggedInUser = null;
        } else {
            System.out.println(loggedInUser);
            System.out.println("No user is logged in.");
            System.out.println();
        }
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public boolean isAdmin() {
        return loggedInUser != null && "Admin".equals(loggedInUser.getRole());
    }

    public boolean isUser() {
        return loggedInUser != null && "User".equals(loggedInUser.getRole());
    }

    public boolean isLoggedIn() {
        if(loggedInUser==null){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean checkUser(String newusername){
        if (users.containsKey(newusername)){
            return true;
        }
        return false;
    }
    public void addUser(String newusername, String newpassword){
        users.put(newusername, new User(newusername,newpassword, "User"));
    }
}
