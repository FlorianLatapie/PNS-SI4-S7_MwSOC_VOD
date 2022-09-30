package interfaces;

public interface IMovieDesc extends java.rmi.Remote {
    String getIsbn();

    String getName();

    String getSynopsis();
}
