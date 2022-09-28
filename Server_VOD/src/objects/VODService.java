package objects;

import interfaces.IBill;
import interfaces.IClientBox;
import interfaces.IMovieDesc;
import interfaces.IVODService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class VODService extends UnicastRemoteObject implements IVODService {
    private static VODService instance = null;

    public static VODService getInstance() throws RemoteException {
        if (instance == null) {
            instance = new VODService(10_002);
        }
        return instance;
    }

    private VODService(int port) throws RemoteException {
        super(port);
    }

    @Override
    public List<IMovieDesc> viewCatalog() throws RemoteException {
        return null;
    }

    @Override
    public IBill playMovie(String isbn, IClientBox box) throws RemoteException {
        return null;
    }
}