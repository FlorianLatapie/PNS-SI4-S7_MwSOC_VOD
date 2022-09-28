package interfaces;

import java.rmi.RemoteException;
import java.util.List;

public interface IVODService extends java.rmi.Remote {
    List<IMovieDesc> viewCatalog() throws RemoteException;

    IBill playMovie(String isbn, IClientBox box) throws RemoteException;
}
