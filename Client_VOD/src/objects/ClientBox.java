package objects;

import interfaces.IClientBox;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientBox extends UnicastRemoteObject implements IClientBox, Serializable {
    public ClientBox() throws RemoteException {
    }

    @Override
    public void stream(byte[] chunk) throws RemoteException {
        System.out.println("Streaming chunk of size " + chunk.length);
        for (byte b : chunk) {
            System.out.print(b + " ");
        }
        System.out.println();
    }
}
