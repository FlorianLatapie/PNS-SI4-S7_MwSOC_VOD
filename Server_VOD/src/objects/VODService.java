package objects;

import interfaces.IBill;
import interfaces.IClientBox;
import interfaces.IMovieDesc;
import interfaces.IVODService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class VODService extends UnicastRemoteObject implements IVODService, Serializable {
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

    // TODO use DB class to import the catalog

    private void importDB() throws RemoteException {
        // later we will import the database from a file
        catalog.add(new MovieDesc("1-23-456-789-012-3", "The Matrix", "The Matrix is a 1999 science fiction action film written and directed by The Wachowskis, starring Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving, and Joe Pantoliano. It depicts a dystopian future in which reality as perceived by most humans is actually a simulated reality called the Matrix, created by sentient machines to subdue the human population, while their bodies' heat and electrical activity are used as an energy source. Computer programmer Neo learns this truth and is drawn into a rebellion against the machines, which involves other people who have been freed from the \"dream world\"."));
        catalog.add(new MovieDesc("1-23-456-789-012-4", "The Matrix Reloaded", "The Matrix Reloaded is a 2003 science fiction action film written and directed by The Wachowskis, and starring Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving, Jada Pinkett Smith, Monica Bellucci, and Lambert Wilson. It is the second installment in The Matrix trilogy. The film depicts a dystopian future in which reality as perceived by most humans is actually a simulated reality called the Matrix, created by sentient machines to subdue the human population, while their bodies' heat and electrical activity are used as an energy source. Computer programmer Neo learns this truth and is drawn into a rebellion against the machines, which involves other people who have been freed from the \"dream world\"."));
        catalog.add(new MovieDesc("1-23-456-789-012-5", "The Matrix Revolutions", "The Matrix Revolutions is a 2003 science fiction action film written and directed by The Wachowskis, and starring Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving, Jada Pinkett Smith, Monica Bellucci, and Lambert Wilson. It is the third and final installment in The Matrix trilogy. The film depicts a dystopian future in which reality as perceived by most humans is actually a simulated reality called the Matrix, created by sentient machines to subdue the human population, while their bodies' heat and electrical activity are used as an energy source. Computer programmer Neo learns this truth and is drawn into a rebellion against the machines, which involves other people who have been freed from the \"dream world\"."));
    }

    @Override
    public ArrayList<IMovieDesc> viewCatalog() throws RemoteException {
        return new ArrayList<>(catalog.stream().toList());
    }

    private IMovieDesc getMovie(String isbn) {
        return catalog.stream().filter(m -> {
            try {
                return m.getIsbn().equals(isbn);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }).findFirst().orElse(null);
    }

    @Override
    public IBill playMovie(String isbn, IClientBox box) throws RemoteException {
        byte[] chunck = getMovie(isbn).getInfos().getBytes();
        //byte[] chunck = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // copy the first 5 bytes of the chunck
        byte[] firstChunck = new byte[5];
        System.arraycopy(chunck, 0, firstChunck, 0, 5);
        box.stream(firstChunck);

        new Thread(() -> {
            byte[] lastchunks = new byte[chunck.length - 5];
            System.arraycopy(chunck, 5, lastchunks, 0, chunck.length - 5);
            try {
                box.stream(lastchunks);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }).start();
        return new Bill(new Random().nextInt(100));
    }
}