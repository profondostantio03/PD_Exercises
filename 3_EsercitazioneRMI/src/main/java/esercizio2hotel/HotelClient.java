/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package esercizio2hotel;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.List;

/*
 * @author luigi
 */
public class HotelClient {
    public static void main(String[] args){
        String host = (args.length > 0) ? args[0] : "localhost";
        String cliente = (args.length>1) ? args[1] : "Gaetano DAlessio";
        int stanzaDaPrenotare = 104;   
            
        try{
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            HotelService hotel = (HotelService) registry.lookup("HotelService");
            System.out.println("=== Richiesta camere libere ===");
            List<Stanza> libere = hotel.getStanzeLibere();
            for(Stanza s:libere){
                System.out.println(" - " + s);
            }
            
            System.out.println("\n=== Tentativo di Prenotazione Camera " + stanzaDaPrenotare + " ===");
            boolean esito = hotel.prenotaStanza(stanzaDaPrenotare, cliente);
            if (esito) {
                System.out.println("Esito: Prenotazione RIUSCITA per " + cliente + "!");
            } else {
                System.out.println("Esito: Prenotazione FALLITA. Stanza non disponibile.");
            }

            System.out.println("\n=== Verifica Nuova Disponibilità ===");
            List<Stanza> aggiornate = hotel.getStanzeLibere();
            for (Stanza s : aggiornate) {
                System.out.println(" - " + s);
            }
            
        } catch(Exception e){
            System.err.println("[Client] Errore di comunicazione remota: ");
            e.printStackTrace();
        }
        
    }
}
