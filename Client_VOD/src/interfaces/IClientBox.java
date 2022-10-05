package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface is used to stream the chunk of the "video" to the client
 */
public interface IClientBox extends Remote {
    /**
     * Method to stream the chunk of the "video" to the client
     *
     * @param chunk video chunk to stream
     */
    void stream(byte[] chunk) throws RemoteException;
}
