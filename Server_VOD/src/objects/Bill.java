package objects;

import interfaces.IBill;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Bill extends UnicastRemoteObject implements IBill, Serializable {
    int price;
    public Bill(int i) throws RemoteException {
        super();
        price = i;
    }

    @Override
    public int getPrice() throws RemoteException {
        return price;
    }
}
