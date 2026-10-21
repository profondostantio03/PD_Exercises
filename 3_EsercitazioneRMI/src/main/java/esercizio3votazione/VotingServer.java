package esercizio3votazione;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;

/*
 * @author luigi
 */
public class VotingServer {
    public static void main(String[] args){
        try{
            VotingService votingService = new VotingServiceImpl();
            Registry registry;
            try{
                registry = LocateRegistry.createRegistry(1099);
                System.out.println("[VotingServer] Registry RMI locale avviato sulla porta 1099.");
            } catch(ExportException e){
                System.out.println("[VotingServer] Registry già presente, connessione all'istanza esistente...");
                registry = LocateRegistry.getRegistry(1099);
            }
            registry.rebind("VotingService", votingService);
            System.out.println("[VotingServer] Servizio 'VotingService' registrato con successo. Server pronto.");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
