package interfaces;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * This interface is used to manage the VOD service for the client : show catalog, play a movie
 */
public interface IVODService extends java.rmi.Remote, Serializable {
    /**
     * Method to get the catalog of the movies
     *
     * @return a formatted string of the catalog of the movies, starting with a number and an hyphen, each movie is separated by a new line
     */
    ArrayList<IMovieDesc> viewCatalog() throws RemoteException;

    /**
     * Method to play a movie
     *
     * @param isbn unique id of a movie
     * @param box the client box to stream the movie
     * @return the bill for the client
     */

    IBill playMovie(String isbn, IClientBox box) throws RemoteException;
}
