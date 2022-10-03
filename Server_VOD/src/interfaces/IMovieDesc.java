package interfaces;

import java.io.Serializable;

/**
 * This interface is used to describe a movie
 */
public interface IMovieDesc extends java.rmi.Remote, Serializable {
    String getIsbn() throws java.rmi.RemoteException;

    String getName() throws java.rmi.RemoteException;

    String getSynopsis() throws java.rmi.RemoteException;

    String getInfos() throws java.rmi.RemoteException;
}
