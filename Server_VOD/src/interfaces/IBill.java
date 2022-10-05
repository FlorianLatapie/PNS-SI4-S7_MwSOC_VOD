package interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface is used to manage the bill of the client
 */
public interface IBill extends Remote, Serializable {
    int getPrice() throws RemoteException;
}
