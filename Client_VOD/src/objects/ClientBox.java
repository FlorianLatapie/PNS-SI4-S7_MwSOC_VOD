package objects;

import interfaces.IClientBox;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientBox extends UnicastRemoteObject implements IClientBox {

    protected ClientBox(int port) throws RemoteException {
        super(port);
    }

    @Override
    public void stream(byte[] chunk) throws RemoteException {

    }
}
