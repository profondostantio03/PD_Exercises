package esercizio3votazione;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * @author luigi
 */
public class VotingServiceImpl extends UnicastRemoteObject implements VotingService{
    private static final long serialVersionUID = 1L;
    
    private final Map<String, Candidato> candidati;
    
    public VotingServiceImpl() throws RemoteException{
        super();
        this.candidati = new HashMap<>();
        inizializzaCandidati();
    }
    
    public void inizializzaCandidati() throws RemoteException{
        candidati.put("Alice", new Candidato("Alice"));
        candidati.put("Bob", new Candidato("Bob"));
        candidati.put("Charlie", new Candidato("Charlie"));
    }
    
    @Override
    public synchronized List<String> getListaCandidati() throws RemoteException{
        return new ArrayList<>(candidati.keySet());
    }
    
    @Override
    public synchronized double vota(String nomeCandidato, int punteggio) throws RemoteException, IllegalArgumentException{
        Candidato c = candidati.get(nomeCandidato);
        if(c==null){
            throw new IllegalArgumentException("Errore: candidato inesistente");
        }
        if(punteggio<1 || punteggio>10){
            throw new IllegalArgumentException("Errore: il punteggio deve essere compreso tra 1 e 10");
        }
        c.registraVoto(punteggio);
        double media = c.getMediaPunteggio();
        System.out.println("[VotingServer] Voto registrato per \" + nomeCandidato + \": \" + punteggio + \" (Nuova media: \" + String.format(\"%.2f\", media) + \")\"");
        return media;
    }
    
    @Override
    public synchronized Map<String, Double> getRisultati() throws RemoteException{
        Map<String, Double> risultati = new LinkedHashMap<>();
        for(Candidato c : candidati.values()){
            risultati.put(c.getNome(), c.getMediaPunteggio());
        }
        return risultati;
    }
}
