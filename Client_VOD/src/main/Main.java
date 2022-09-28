package main;

import interfaces.IConnection;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Client started");

        Registry reg = LocateRegistry.getRegistry("localHost", 2001);
        IConnection c = (IConnection) reg.lookup("Connection");

        try {
            c.signUp("test", "test");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            c.signUp("Ludo", "123");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            c.login("GRR", "234");
        } catch (Exception e) {
            System.out.println(e);
        }

        var loginObject = c.login("Ludo", "123");
    }
}