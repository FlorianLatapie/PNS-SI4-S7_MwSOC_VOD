package objects;

import java.util.HashSet;
import java.util.Set;

public class Database {
    private static Database instance;
    Set<User> users;

    public static Database getInstance() {
        if (instance == null) {
            return new Database();
        }
        return instance;
    }

    private Database() {
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
