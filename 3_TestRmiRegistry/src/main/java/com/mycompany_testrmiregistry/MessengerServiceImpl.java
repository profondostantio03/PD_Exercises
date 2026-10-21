package com.mycompany_testrmiregistry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MessengerServiceImpl implements MessengerService {

    @Override
    public String sendMessage(String clientMessage) throws RemoteException {
        System.out.println("[Server] Messaggio ricevuto dal client: " + clientMessage);
        return "Server ACK: ho ricevuto \"" + clientMessage + "\"";
    }

    public static void main(String[] args) {
        try {
            // istanza dell'oggetto reale
            MessengerServiceImpl service = new MessengerServiceImpl();

            // esportazione esplicita dell'oggetto su porta anonima (0 = porta TCP casuale libera)
            MessengerService stub = (MessengerService) UnicastRemoteObject.exportObject(service, 0);

            // creazione del Registry RMI interno al processo sulla porta standard 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("[Server] Registry RMI locale avviato sulla porta 1099.");

            // registrazione dello stub sul registro locale
            registry.rebind("MessengerService", stub);
            System.out.println("[Server] Oggetto remoto associato a 'MessengerService'. Pronto.");
        } catch (RemoteException e) {
            System.err.println("[Server] Errore di inizializzazione RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
