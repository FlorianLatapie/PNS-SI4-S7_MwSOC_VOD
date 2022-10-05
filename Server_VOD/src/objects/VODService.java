package objects;

import interfaces.IBill;
import interfaces.IClientBox;
import interfaces.IMovieDesc;
import interfaces.IVODService;
import objects.io.DB;

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

    private final DB db;

    public static VODService getInstance() throws RemoteException {
        if (instance == null) {
            instance = new VODService(10_002);
        }
        return instance;
    }

    private VODService(int port) throws RemoteException {
        super(port);
        db = DB.getInstance();
        catalog = new HashSet<>();
        importDB();
    }

    // TODO use DB class to import the catalog

    private boolean importDB() throws RemoteException {
        return catalog.addAll(db.getMovies());
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
        byte[] chunk = getMovie(isbn).getInfos().getBytes();
        //byte[] chunk = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // copy the first 5 bytes of the chunk
        byte[] firstChunk = new byte[5];
        System.arraycopy(chunk, 0, firstChunk, 0, 5);
        box.stream(firstChunk);

        new Thread(() -> {
            byte[] lastChunks = new byte[5];
            for (int i = 5; i < chunk.length - 5; i += 5){
                System.arraycopy(chunk, i, lastChunks, 0, 5);
                try {
                    box.stream(lastChunks);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("End of the movie");
        }).start();
        System.out.println("Bill returned");
        return new Bill(new Random().nextInt(100));
    }
}