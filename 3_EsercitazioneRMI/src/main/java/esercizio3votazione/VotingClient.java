/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package esercizio3votazione;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Map;

/*
 * @author luigi
 */
public class VotingClient {
    public static void main(String[] args){
        String host = (args.length > 0) ? args[0] : "localhost";
        String candidatoScelto = (args.length > 1) ? args[1] : "Alice";
        int votoDaAssegnare = (args.length > 2) ? Integer.parseInt(args[2]) : 8;
        
        try{
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            VotingService service  = (VotingService) registry.lookup("VotingService");
            
            System.out.println("== Lista candidati disponibili ==");
            List<String> lista = service.getListaCandidati();
            for(String nome: lista){
                System.out.println("-" + nome);
            }
            System.out.println("\n=== 2. Espressione del Voto ===");
            System.out.println("Votazione per '" + candidatoScelto + "' con punteggio: " + votoDaAssegnare);
            double mediaAggiornata = service.vota(candidatoScelto, votoDaAssegnare);
            System.out.println("Voto accettato! Score medio attuale di " + candidatoScelto + ": " + String.format("%.2f", mediaAggiornata));

            System.out.println("\n=== 3. Risultati Complessivi delle Votazioni ===");
            Map<String, Double> risultati = service.getRisultati();
            for (Map.Entry<String, Double> entry : risultati.entrySet()) {
                System.out.println("Candidato: " + entry.getKey() + " -> Media Votazioni: " + String.format("%.2f", entry.getValue()));
            }
        } catch(IllegalArgumentException iae){
            System.err.println("[Client] Errore di validazione: " + iae.getMessage());
        } catch(Exception e){
            System.err.println("[Client] Errore di comunicazione remota: " + e.getMessage());
            e.printStackTrace();
        }
    }    
}
