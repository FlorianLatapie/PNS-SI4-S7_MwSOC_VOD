package main;

import exceptions.SignUpFailed;
import interfaces.IConnection;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Client started !\n");

        Registry reg = LocateRegistry.getRegistry("localHost", 2001);
        IConnection c = (IConnection) reg.lookup("Connection");

        System.out.println("Try sign up with existing user ('test', 'test'), should throw an error");
        try {
            c.signUp("test", "test");
        } catch (SignUpFailed e) {
            System.out.println(e.getMessage() + "\n");
        }

        System.out.println("Try sign up with new user ('Ludo', '123'), should be successful at the first launch during the server lifetime");
        try {
            c.signUp("ludo@mail.com", "123");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("Try to log in with not existing user ('jean.daniel@jaimail.com', 'monbeaumotdepasse'), should throw an error");
        try {
            c.login("jean.daniel@jaimail.com", "monbeaumotdepasse");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n");
        }

        System.out.println("Try to log in with existing user ('ludo@mail.com', '123'), should be successful");
        var VODService = c.login("ludo@mail.com", "123");

        System.out.println("Display the catalog :");
        System.out.println(VODService.viewCatalog());
    }
}