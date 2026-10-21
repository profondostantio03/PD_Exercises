/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package esercizio2hotel;

import java.util.List;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/*
 * @author luigi
 */
public class HotelServiceImpl extends UnicastRemoteObject implements HotelService{
    private static final long serialVersionUID = 1L;
    private final Map<Integer, Stanza> stanze;
    
    public HotelServiceImpl() throws RemoteException{
        super();
        this.stanze = new HashMap<>();
        inizializzaCamere();
    }
    
    private void inizializzaCamere(){
        stanze.put(101, new Stanza(101, "Singola Standard"));
        stanze.put(102, new Stanza(102, "Doppia Classica"));
        stanze.put(103, new Stanza(103, "Suite Deluxe"));
        stanze.put(104, new Stanza(104, "Matrimoniale vista mare"));
    }
    
    @Override
    public synchronized List<Stanza> getStanzeLibere() throws RemoteException{
        List<Stanza> libere = new ArrayList<>();
        for(Stanza s : stanze.values()){
            if(!s.isOccupata()){
                libere.add(new Stanza(s.getNumero(), s.getTipologia()));
            }
        }
        return libere;
    }
    
    @Override
    public synchronized boolean prenotaStanza(int numeroStanza, String nomeCliente) throws RemoteException{
        Stanza s = stanze.get(numeroStanza);
        if(s==null || s.isOccupata()){
            System.out.println("[HotelServer] Tentativo fallito: Camera " + numeroStanza + "non disponibile per " + nomeCliente);
            return false;
        }
        
        s.setOccupata(true);
        System.out.println("[HotelServer] Prenotazione confermata: Camera " + numeroStanza + "non disponibile per " + nomeCliente);
        return true;
    }
}