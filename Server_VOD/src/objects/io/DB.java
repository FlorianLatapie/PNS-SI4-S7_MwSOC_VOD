package objects.io;

import objects.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is used to manage the different databases of the application
 */
public class DB {
    private static DB instance;
    private final static String user_db_path = "src/objects/io/user_db.txt";

    public static DB getInstance() {
        if (instance == null) {
            return new DB();
        }
        return instance;
    }

    private DB() {
        initDB();
    }

    /**
     * Initialize the database, if the file doesn't exist, create it
     */
    private void initDB() {
        File f = new File(user_db_path);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Read the database and return the list of users
     * @return list of users
     */
    public List<User> getUsers() {
        try {
            Scanner sc = new Scanner(new File(user_db_path));
            List<User> users = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split(";");
                User u = new User(tokens[0], tokens[1]);

                users.add(u);
            }
            sc.close();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Add a new user to the database file
     * @param u user to add
     * @return true if the user has been added, false otherwise
     */
    public void addUser(User u) {
        try {
            FileWriter fw = new FileWriter(user_db_path, true);
            fw.write(u.getMail() + ";" + u.getPwd() + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}