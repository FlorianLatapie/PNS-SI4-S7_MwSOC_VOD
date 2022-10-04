package objects;

import objects.io.DB;

import java.util.HashSet;
import java.util.Set;

public class UserDatabase {
    private static UserDatabase instance;
    private DB db;
    Set<User> users;

    public static UserDatabase getInstance() {
        if (instance == null) {
            return new UserDatabase();
        }
        return instance;
    }

    private UserDatabase() {
        db = DB.getInstance();
        users = new HashSet<>();
        importDB();
    }

    /**
     * Add a new user to the database
     *
     * @param u user to add
     * @return true if the user has been added, false otherwise
     */
    public boolean addUserToDB(User u) {
        if (checkThatUserExists(u)) {
            return false;
        }
        db.addUser(u);
        return users.add(u);
    }

    /**
     * import the user database using DB class
     */
    private boolean importDB() {
        return users.addAll(db.getUsers());
    }

    /**
     * Check if the user exists in the database
     *
     * @param user user to check
     * @return true if the user exists, false otherwise
     */
    public boolean checkThatUserExists(User user) {
        return users.contains(user);
    }
}
