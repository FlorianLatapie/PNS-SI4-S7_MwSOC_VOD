package interfaces;

import objects.Bill;
import objects.MovieDesc;

import java.util.List;

public interface IVODService extends java.rmi.Remote {
    List<MovieDesc> viewCatalog();
    Bill playmovie(String isbn, IClientBox box);
}
