package main;

import interfaces.IConnection;
import objects.Connection;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Main class of the server
 */
public class Main {
    public static void main(String[] args) throws RemoteException {
        if (args.length != 1) {
            System.out.println("Usage: java -jar server.jar <server_port>");
            System.exit(1);
        }

        System.out.println("Server started !");

        Registry reg = LocateRegistry.createRegistry(Integer.parseInt(args[0]));
        IConnection c = new Connection();

        reg.rebind("Connection", c);
    }
}