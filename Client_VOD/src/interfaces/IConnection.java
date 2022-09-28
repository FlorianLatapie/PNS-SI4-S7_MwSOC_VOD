package interfaces;

import exceptions.InvalidCredentialsException;
import exceptions.SignUpFailed;

import java.rmi.RemoteException;

public interface IConnection extends java.rmi.Remote {
    boolean signUp(String mail, String pwd) throws SignUpFailed, RemoteException;

    IVODService login(String mail, String pwd) throws InvalidCredentialsException, RemoteException;
}

