import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Serveur {
    public Serveur() {
    }

    public static void main(String[] args) throws RemoteException {
        int port = 1099;

        if(args.length == 1){
            port = Integer.parseInt(args[0]);
        }else{
            System.out.println("Incorrect number of argument, should be : java Serveur [port]");
            System.exit(1);
        }

        Distributeur distributeur = new Distributeur();
        ServiceDistributeur serviceDistributeur = (ServiceDistributeur)UnicastRemoteObject.exportObject(distributeur, 0);
        Registry reg = LocateRegistry.createRegistry(port);
        reg.rebind("BlackAndWhite", serviceDistributeur);
        System.out.println("Service started on port " + port);
    }
}
