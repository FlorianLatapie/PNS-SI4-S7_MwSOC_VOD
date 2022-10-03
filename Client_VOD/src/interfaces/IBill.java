package interfaces;

import java.io.Serializable;

/**
 * This interface is used to manage the bill of the client
 */
public interface IBill extends java.rmi.Remote, Serializable {
    int getPrice() throws java.rmi.RemoteException;
}
