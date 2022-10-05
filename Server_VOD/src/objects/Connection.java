package objects;

import exceptions.InvalidCredentialsException;
import exceptions.SignUpFailed;
import interfaces.IConnection;
import interfaces.IVODService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Connection extends UnicastRemoteObject implements IConnection {
    private static final UserDatabase db = UserDatabase.getInstance();

    public Connection() throws RemoteException {
        super();
    }

    @Override
    public boolean signUp(String mail, String pwd) throws SignUpFailed, RemoteException {
        if (!db.addUserToDB(new User(mail, pwd))) {
            throw new SignUpFailed(mail);
        }
        return true;
    }

    @Override
    public IVODService login(String mail, String pwd) throws InvalidCredentialsException, RemoteException {
        if (!db.checkThatUserExists(new User(mail, pwd))) {
            throw new InvalidCredentialsException(mail);
        }
        System.out.println("Login succeed :" + mail);
        return VODService.getInstance();
    }
}
