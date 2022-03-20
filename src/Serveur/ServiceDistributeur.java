import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.ImageIcon;

public interface ServiceDistributeur extends Remote {
    ImageIcon distribuerTraitement(ImageIcon image) throws RemoteException;

    void enregistrerClient(ServiceTraitement service) throws RemoteException;
}
