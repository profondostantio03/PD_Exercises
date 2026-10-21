package com.mycompany_testrmiregistry;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MessengerClient {
    public static void main(String[] args) {
        String host = (args.length > 0) ? args[0] : "localhost";
        int port = 1099;

        try {
            // ottiene il riferimento al Registry remoto/locale
            Registry registry = LocateRegistry.getRegistry(host, port);

            // lookup dell'oggetto remoto tramite l'identificativo
            MessengerService server = (MessengerService) registry.lookup("MessengerService");

            // invocazione trasparente del metodo remoto
            String response = server.sendMessage("Client Message da " + host);
            System.out.println("[Client] Risposta dal server: " + response);
        } catch (Exception e) {
            System.err.println("[Client] Errore durante la comunicazione:");
            e.printStackTrace();
        }
    }
}
