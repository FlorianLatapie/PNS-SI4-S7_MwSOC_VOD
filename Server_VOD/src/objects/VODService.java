package objects;

import interfaces.IBill;
import interfaces.IClientBox;
import interfaces.IMovieDesc;
import interfaces.IVODService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VODService extends UnicastRemoteObject implements IVODService {
    private static VODService instance = null;
    private static Set<IMovieDesc> catalog;

    public static VODService getInstance() throws RemoteException {
        if (instance == null) {
            instance = new VODService(10_002);
        }
        return instance;
    }

    private VODService(int port) throws RemoteException {
        super(port);
        catalog = new HashSet<>();
        importDB();
    }

    private void importDB() {
        // later we will import the database from a file
        catalog.add(new MovieDesc("1234567890123", "The Matrix", "The Matrix is a 1999 science fiction action film written and directed by The Wachowskis, starring Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving, and Joe Pantoliano. It depicts a dystopian future in which reality as perceived by most humans is actually a simulated reality called the Matrix, created by sentient machines to subdue the human population, while their bodies' heat and electrical activity are used as an energy source. Computer programmer Neo learns this truth and is drawn into a rebellion against the machines, which involves other people who have been freed from the \"dream world\"."));
        catalog.add(new MovieDesc("1234567890124", "The Matrix Reloaded", "The Matrix Reloaded is a 2003 science fiction action film written and directed by The Wachowskis, and starring Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving, Jada Pinkett Smith, Monica Bellucci, and Lambert Wilson. It is the second installment in The Matrix trilogy. The film depicts a dystopian future in which reality as perceived by most humans is actually a simulated reality called the Matrix, created by sentient machines to subdue the human population, while their bodies' heat and electrical activity are used as an energy source. Computer programmer Neo learns this truth and is drawn into a rebellion against the machines, which involves other people who have been freed from the \"dream world\"."));
        catalog.add(new MovieDesc("1234567890125", "The Matrix Revolutions", "The Matrix Revolutions is a 2003 science fiction action film written and directed by The Wachowskis, and starring Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving, Jada Pinkett Smith, Monica Bellucci, and Lambert Wilson. It is the third and final installment in The Matrix trilogy. The film depicts a dystopian future in which reality as perceived by most humans is actually a simulated reality called the Matrix, created by sentient machines to subdue the human population, while their bodies' heat and electrical activity are used as an energy source. Computer programmer Neo learns this truth and is drawn into a rebellion against the machines, which involves other people who have been freed from the \"dream world\"."));
    }
    @Override
    public List<IMovieDesc> viewCatalog() throws RemoteException {
        return null;
    }

    @Override
    public IBill playMovie(String isbn, IClientBox box) throws RemoteException {
        return null;
    }
}