package main;

import exceptions.SignUpFailed;
import interfaces.IConnection;
import interfaces.IMovieDesc;
import interfaces.IVODService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Client started !\n");

        Registry reg = LocateRegistry.getRegistry("localHost", 2001);
        IConnection c = (IConnection) reg.lookup("Connection");

        Scanner sc = new Scanner(System.in);
        int sign;
        IVODService VODService = null;
        do {
            System.out.println("1 - Sign up\n2 - Login");
            sign = sc.nextInt();
            if (sign == 1) trySignUp(c, sc);
            else {
                VODService = tryLogin(c, sc);
            }
        } while (VODService == null);

        while (chooseMovie(VODService, c, sc));
    }

    private static boolean chooseMovie(IVODService VODService, IConnection c, Scanner sc) throws RemoteException {
        List<IMovieDesc> catalogArrayList = VODService.viewCatalog();
        String sb = formatedMovieList(catalogArrayList);
        int choice;
        do {
            System.out.println("Catalog :");
            System.out.println(sb);

            System.out.print("Choose a movie (type 0 to quit) :");
            choice = sc.nextInt();
        }while (choice < 0 || choice >= catalogArrayList.size());

        if (choice != 0){
            VODService.playMovie(catalogArrayList.get(choice - 1).getIsbn(), clientBox);
            return true;
        }
        return false;
    }

    private static String formatedMovieList(List<IMovieDesc> catalogArrayList) throws RemoteException {
        StringBuilder sb = new StringBuilder();
        catalogArrayList.forEach(movie -> sb.append(catalogArrayList.indexOf(movie) + 1).append(" - ").append(movie).append("\n"));
        return sb.toString();
    }

    private static IVODService tryLogin(IConnection c, Scanner sc) {
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
        }while (true);
    }

    private static void trySignUp(IConnection c, Scanner sc) {
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
        }while (true);
    }
}