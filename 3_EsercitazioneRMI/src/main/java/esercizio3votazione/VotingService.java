package esercizio3votazione;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/*
 * @author luigi
 */
public interface VotingService extends Remote{
    List<String> getListaCandidati() throws RemoteException;
    double vota(String nomeCandidato, int punteggio) throws RemoteException, IllegalArgumentException;
    Map<String, Double> getRisultati() throws RemoteException;
}
