package objects;

import java.util.HashSet;
import java.util.Set;

public class UserDatabase {
    private static UserDatabase instance;
    Set<User> users;

    public static UserDatabase getInstance() {
        if (instance == null) {
            return new UserDatabase();
        }
        return instance;
    }

    private UserDatabase() {
        users = new HashSet<>();
        importDB();
    }


    public boolean addUserToDB(User u) {
        return users.add(u);
    }

    private void importDB() {
        // later we will import the database from a file
        users.add(new User("test", "test"));
    }

    public boolean checkThatUserExists(User user) {
        return users.contains(user);
    }
}
