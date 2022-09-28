package interfaces;

import java.rmi.RemoteException;

public interface IClientBox extends java.rmi.Remote {
    void stream(byte[] chunck) throws RemoteException;
}
