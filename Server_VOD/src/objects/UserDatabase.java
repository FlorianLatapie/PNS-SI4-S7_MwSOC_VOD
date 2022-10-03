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


    public boolean addUserToDB(User u) {
        if (checkThatUserExists(u)) {
            return false;
        }
        db.addUser(u);
        return users.add(u);
    }

    private boolean importDB() {
        return users.addAll(db.getUsers());
    }

    public boolean checkThatUserExists(User user) {
        return users.contains(user);
    }
}
