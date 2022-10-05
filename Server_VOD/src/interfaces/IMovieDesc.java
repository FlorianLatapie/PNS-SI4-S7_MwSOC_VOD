package interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface is used to describe a movie
 */
public interface IMovieDesc extends Remote, Serializable {
    String getIsbn() throws RemoteException;

    String getName() throws RemoteException;

    String getSynopsis() throws RemoteException;

    String getInfos() throws RemoteException;
}
