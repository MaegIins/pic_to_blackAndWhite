import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.ImageIcon;

public interface ServiceTraitement extends Remote {
    ImageIcon traiter(ImageIcon image) throws RemoteException;
}
