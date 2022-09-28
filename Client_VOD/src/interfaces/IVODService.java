package interfaces;

import java.util.List;

public interface IVODService extends java.rmi.Remote {
    List<IMovieDesc> viewCatalog();
    IBill playmovie(String isbn, IClientBox box);
}
