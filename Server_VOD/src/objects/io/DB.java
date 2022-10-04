package objects.io;

import interfaces.IMovieDesc;
import objects.MovieDesc;
import objects.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class is used to manage the different databases of the application
 */
public class DB {
    private static DB instance;
    private final static String user_db_path = "src/objects/io/user_db.txt";
    private final static String movie_db_path = "src/objects/io/movie_db.txt";

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
        File user_db_file = new File(user_db_path);

        if (!user_db_file.exists()) {
            try {
                user_db_file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File movie_db_file = new File(movie_db_path);
        if (!movie_db_file.exists()) {
            try {
                movie_db_file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Read the database and return the list of users
     *
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
     * Read the database and return the list of movies
     *
     * @return list of movies
     */
    public List<IMovieDesc> getMovies() {
        try {
            Scanner sc = new Scanner(new File(movie_db_path));
            List<IMovieDesc> movies = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split(";");
                IMovieDesc m = new MovieDesc(tokens[0], tokens[1], tokens[2]);

                movies.add(m);
            }
            sc.close();
            return movies;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Add a new user to the database file
     *
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