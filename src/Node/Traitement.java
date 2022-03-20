import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import javax.swing.ImageIcon;

public class Traitement implements ServiceTraitement {
    public Traitement() {
    }

    public ImageIcon traiter(ImageIcon image) throws RemoteException {
        String host = "";

        try {
            host = RemoteServer.getClientHost();
        } catch (ServerNotActiveException e) {
            System.out.println("Client not responding");
        }

        System.out.println("Processing host " + host + " request");
        BufferedImage buffer = new BufferedImage(image.getIconWidth(), image.getIconHeight(), BufferedImage.TYPE_USHORT_GRAY);
        Graphics2D newImage = buffer.createGraphics();
        image.paintIcon((Component)null, newImage, 0, 0);
        newImage.dispose();
        return new ImageIcon(buffer);
    }
}