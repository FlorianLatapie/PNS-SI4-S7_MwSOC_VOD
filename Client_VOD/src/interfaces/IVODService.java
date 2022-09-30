package interfaces;

import java.rmi.RemoteException;

public interface IVODService extends java.rmi.Remote {
    String viewCatalog() throws RemoteException;

    IBill playMovie(String isbn, IClientBox box) throws RemoteException;
}
