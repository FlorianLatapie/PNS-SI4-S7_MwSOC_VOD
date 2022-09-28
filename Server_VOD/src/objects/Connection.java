package objects;

import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;
import interfaces.IConnection;
import interfaces.IVODService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Connection extends UnicastRemoteObject implements IConnection {


    public Connection(int port) throws RemoteException {
        super(port);
    }

    @Override
    public boolean signIn(String mail, String pwd) throws SignInFailed, RemoteException {
        System.out.println("test sign in");
        return false;
    }

    @Override
    public IVODService login(String mail, String pwd) throws InvalidCredentialsException, RemoteException {
        System.out.println("test login");
        return null;
    }
}
