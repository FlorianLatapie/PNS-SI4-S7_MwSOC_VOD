package main;

import interfaces.IConnection;
import objects.Connection;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws RemoteException {
        System.out.println("Server started !");

        Registry reg = LocateRegistry.createRegistry(2_001);
        IConnection c = new Connection(10_001);

        reg.rebind("Connection", c);
    }
}