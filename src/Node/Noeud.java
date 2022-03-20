import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Noeud {
    public Noeud() {
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        int port = 1099;
        String ip = "";

        if(args.length == 2){
            port = Integer.parseInt(args[1]);
            ip = args[0];
        }else{
            System.out.println("Incorrect number of argument, should be : java Noeud [serverIP] [port]");
            System.exit(1);
        }
       

        System.out.println("Node Started");
        Registry reg = LocateRegistry.getRegistry(ip, port);
        ServiceDistributeur serviceDistributeur = (ServiceDistributeur)reg.lookup("BlackAndWhite");
        Traitement traitement = new Traitement();
        serviceDistributeur.enregistrerClient((ServiceTraitement)UnicastRemoteObject.exportObject(traitement, 0));
    }
}
