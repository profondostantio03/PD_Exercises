/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package esercizio2hotel;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;

/*
 * @author luigi
 */
public class HotelServer {
    public static void main(String[] args){
        try{
            HotelService hotel = new HotelServiceImpl();
            Registry registry;
            
            try{
                registry = LocateRegistry.createRegistry(1099);
                System.out.println("[HotelServer] Registry locale avviato su porta 1099");
            } catch(ExportException e){
                System.out.println("[HotelServer] Registry già presente, mi collego a quello attivo");
                registry = LocateRegistry.getRegistry(1099);
            }
            registry.rebind("HotelService", hotel);
            System.out.println("[HotelServer] HotelService registrato con successo. In attesa di clienti..");
        } catch(Exception e){
            System.err.println("[HotelServer] Errore di inizializzazione.");
            e.printStackTrace();
        }
    }
}
