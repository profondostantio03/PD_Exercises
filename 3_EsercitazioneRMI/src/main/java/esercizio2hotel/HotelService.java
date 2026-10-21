/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package esercizio2hotel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/*
 * @author luigi
 */
public interface HotelService extends Remote{
    List<Stanza> getStanzeLibere() throws RemoteException;
    boolean prenotaStanza(int numeroStanza, String nomeCliente) throws RemoteException;
}
