package main;

import interfaces.IBill;
import interfaces.IConnection;
import interfaces.IMovieDesc;
import interfaces.IVODService;
import objects.ClientBox;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: java -jar client.jar <server_ip> <server_port>");
            System.exit(1);
        }

        System.out.println("Client started !\n");


        Registry reg = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
        IConnection c = (IConnection) reg.lookup("Connection");

        Scanner sc = new Scanner(System.in);
        IVODService VODService = null;

        do {
            try {
                System.out.println("1 - Sign up\n2 - Login");
                var sign = sc.nextLine();
                if (Integer.parseInt(sign) == 1) {
                    trySignUp(c);
                } else if (Integer.parseInt(sign) == 2) {
                    VODService = tryLogin(c);
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        } while (VODService == null);

        while (true) {
            chooseMovie(VODService);
        }
    }

    private static void askToExit() {
        Scanner sc = new Scanner(System.in);
        String choice;
        do {
            System.out.println("Do you want to quit ? y/n");
            choice = sc.nextLine();
        } while (!Objects.equals(choice, "y") && !Objects.equals(choice, "n"));
        if (choice.equals("y"))
            System.exit(0);
    }

    /**
     * Displays the catalog and ask the user to select a movie
     *
     * @param VODService the VOD service used to get the Bill
     * @throws RemoteException if the connection to the server failed
     */
    private static void chooseMovie(IVODService VODService) throws RemoteException {
        Scanner sc = new Scanner(System.in);
        String choice;
        int intChoice;

        var catalogArrayList = VODService.viewCatalog();
        String sb = formattedMovieList(catalogArrayList);

        do {
            System.out.println("Catalog :");
            System.out.println(sb);

            System.out.print("Choose a movie (type 0 to quit) :");
            choice = sc.nextLine();
            try {
                intChoice = Integer.parseInt(choice) - 1;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                intChoice = -2;
            }

        } while (intChoice < -1 || intChoice > catalogArrayList.size());


        if (intChoice != -1) {
            IBill bill = VODService.playMovie(catalogArrayList.get(intChoice).getIsbn(), new ClientBox());
            System.out.println("-------------------------> Bill price : " + bill.getPrice());
            return;
        }
        askToExit();
    }

    /**
     * Generate a String from the list of movie desc with number and hyphen in front of the movie
     *
     * @param catalogArrayList the list of movie description
     * @return the formatted movie list
     */
    private static String formattedMovieList(List<IMovieDesc> catalogArrayList) {
        StringBuilder sb = new StringBuilder();
        catalogArrayList.forEach(movie -> {
            try {
                sb.append(catalogArrayList.indexOf(movie) + 1).append(" - ").append(movie.getInfos()).append("\n");
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
        return sb.toString();
    }

    /**
     * Ask the user to login
     *
     * @param c the Connection object
     * @return a VODService object
     */
    private static IVODService tryLogin(IConnection c) {
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print("Mail : ");
                var mail = sc.nextLine();
                System.out.print("Password : ");
                var psw = sc.nextLine();
                return c.login(mail, psw);
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
            }
        } while (true);
    }

    /**
     * Ask the user to create an account
     *
     * @param c the Connection object
     */
    private static void trySignUp(IConnection c) {
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print("Mail : ");
                var mail = sc.nextLine();
                System.out.print("Password : ");
                var psw = sc.nextLine();
                c.signUp(mail, psw);
                System.out.println("User created");
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
            }
        } while (true);
    }
}