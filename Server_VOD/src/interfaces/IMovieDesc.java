package interfaces;

/**
 * This interface is used to describe a movie
 */
public interface IMovieDesc extends java.rmi.Remote {
    String getIsbn();

    String getName();

    String getSynopsis();
}
