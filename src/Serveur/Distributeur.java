import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;
import java.net.*;

public class Distributeur implements ServiceDistributeur {
    private ArrayList<ServiceTraitement> tab = new ArrayList();

    public Distributeur() {
    }

    public ImageIcon distribuerTraitement(ImageIcon image) throws RemoteException {
        System.out.println("Processing Started");
        ArrayList chunks = this.createChunk(image);
        int node = 0;
        Iterator iterator = chunks.iterator();

        while(iterator.hasNext()) {
            Chunk chunk = (Chunk)iterator.next();
            if (node >= this.tab.size()) {
                node = 0;
            }

            try {
                chunk.setImg(((ServiceTraitement)this.tab.get(node)).traiter(chunk.getImg()));
                node++;
            } catch (Exception e) {
                System.out.println("Node " + node + " not responding, removed from the list");
                this.tab.remove(node);
            }
        }

        System.out.println("Process Over");
        return this.createImage(chunks, image.getIconWidth(), image.getIconHeight());
    }

    public void enregistrerClient(ServiceTraitement noeud) throws RemoteException {
        String ip = "";
        try {
            ip = RemoteServer.getClientHost();
        } catch (ServerNotActiveException e) {
        }

        System.out.println("Node " + ip + " connected");
        this.tab.add(noeud);
    }

    private ArrayList<Chunk> createChunk(ImageIcon image) {
        BufferedImage buffer = new BufferedImage(image.getIconWidth(), image.getIconHeight(), 1);
        Graphics2D newImage = buffer.createGraphics();
        image.paintIcon((Component)null, newImage, 0, 0);
        newImage.dispose();
        byte b = 2;
        int nb = this.tab.size() / b;
        if (this.tab.size() % 2 != 0) {
            nb++;
        }

        int int1 = buffer.getWidth() / b;
        int int2 = buffer.getHeight() / nb;
        ArrayList list = new ArrayList();

        for(int x = 0; x < nb; x++) {
            for(int y = 0; y < b; y++) {
                BufferedImage buffered = new BufferedImage(int1, int2, buffer.getType());
                Graphics2D newImage2 = buffered.createGraphics();
                newImage2.drawImage(buffer, 0, 0, int1, int2, int1 * y, int2 * x, int1 * y + int1, int2 * x + int2, (ImageObserver)null);
                newImage2.dispose();
                list.add(new Chunk(new ImageIcon(buffered), x, y));
            }
        }

        return list;
    }

    private ImageIcon createImage(ArrayList<Chunk> chunks, int width, int height) {
        BufferedImage buffer = new BufferedImage(width, height, 1);
        Graphics2D newImage = buffer.createGraphics();
        Iterator iterator = chunks.iterator();

        while(iterator.hasNext()) {
            Chunk chunk = (Chunk)iterator.next();
            chunk.getImg().paintIcon((Component)null, newImage, chunk.getImg().getIconWidth() * chunk.getCol(), chunk.getImg().getIconHeight() * chunk.getRow());
        }

        return new ImageIcon(buffer);
    }
}
