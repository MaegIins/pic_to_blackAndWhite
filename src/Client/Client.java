import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Client {
    public Client() {
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        int port = 1099;
        String image = "";
        String ip = "";

        if(args.length == 3){
            port = Integer.parseInt(args[2]);
            image = args[0];
            ip = args[1];
        }else{
            System.out.println("Incorrect number of argument, should be : java Client [image] [serverIP] [port]");
            System.exit(1);
        }

        Registry reg = LocateRegistry.getRegistry(ip, port);
        ServiceDistributeur service = (ServiceDistributeur)reg.lookup("BlackAndWhite");

        try {
            ImageIcon imageIcon = service.distribuerTraitement(new ImageIcon(image));
            BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), 1);
            Graphics2D graphics = bufferedImage.createGraphics();
            imageIcon.paintIcon((Component)null, graphics, 0, 0);
            graphics.dispose();
            int end = image.indexOf(".");
            String newName ="temp";
            if(end != -1){
                newName = image.substring(0,end);
            }
            File file = new File(newName + "_BlackAndWhite.png");
            ImageIO.write(bufferedImage, "png", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
