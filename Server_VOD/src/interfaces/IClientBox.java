package interfaces;

public interface IClientBox extends java.rmi.Remote {
    void stream(byte[] chunck);
}
