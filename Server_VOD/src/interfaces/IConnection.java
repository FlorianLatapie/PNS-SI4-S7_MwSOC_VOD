package interfaces;

import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;

public interface IConnection extends java.rmi.Remote {
    boolean signIn (String mail, String pwd) throws SignInFailed;
    IVODService login(String mail, String pwd) throws InvalidCredentialsException;
}

