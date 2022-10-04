package interfaces;

import exceptions.InvalidCredentialsException;
import exceptions.SignUpFailed;

import java.rmi.RemoteException;

/**
 * Interface for the client connection, it is implemented as the main object of the RMI connection
 */
public interface IConnection extends java.rmi.Remote {
    /**
     * create a new user
     *
     * @param mail mail of the user
     * @param pwd  password of the user
     * @return true if the user is created
     * @throws SignUpFailed    if the user already exists
     * @throws RemoteException if the connection is lost
     */
    boolean signUp(String mail, String pwd) throws SignUpFailed, RemoteException;

    /**
     * login the user
     *
     * @param mail mail of the user
     * @param pwd  password of the user
     * @return the VODService object
     * @throws InvalidCredentialsException if the user doesn't exists or the password is wrong
     * @throws RemoteException             if the connection is lost
     */
    IVODService login(String mail, String pwd) throws InvalidCredentialsException, RemoteException;
}

