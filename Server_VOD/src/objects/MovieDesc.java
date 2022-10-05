package objects;

import interfaces.IMovieDesc;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class MovieDesc extends UnicastRemoteObject implements IMovieDesc, Serializable, java.rmi.Remote {
    private final String isbn;
    private final String name;
    private final String synopsis;

    public MovieDesc(String isbn, String name, String synopsis) throws RemoteException {
        super();
        this.isbn = isbn;
        this.name = name;
        this.synopsis = synopsis;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    @Override
    public String getInfos() {
        return name + " (" + isbn + ") : " + synopsis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDesc movieDesc = (MovieDesc) o;
        return Objects.equals(isbn, movieDesc.isbn) && Objects.equals(name, movieDesc.name) && Objects.equals(synopsis, movieDesc.synopsis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, name, synopsis);
    }

    @Override
    public String toString() {
        return "MovieDesc{" +
                "isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }
}
