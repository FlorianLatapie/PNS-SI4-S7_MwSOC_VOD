package interfaces;

import java.rmi.RemoteException;
import java.util.List;

public interface IVODService extends java.rmi.Remote {
    String viewCatalog() throws RemoteException;

    IBill playMovie(String isbn, IClientBox box) throws RemoteException;
}
