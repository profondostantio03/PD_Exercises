/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package esercizio2hotel;

import java.io.Serializable;

/*
 * @author luigi
 */
public class Stanza implements Serializable{
    private static final long serialVersionUID = 1L;
    private final int numero;
    private final String tipologia;
    private boolean occupata;
    
    public Stanza(int numero, String tipologia){
        this.numero=numero;
        this.tipologia=tipologia;
        this.occupata=false;
    }
    
    public int getNumero(){
        return numero;
    }
    
    public String getTipologia(){
        return tipologia;
    }
    
    public boolean isOccupata(){
        return occupata;
    }
    
    public void setOccupata(boolean occupata){
        this.occupata=true;
    }
    
    @Override
    public String toString() {
        return "Stanza " + numero + " [" + tipologia + "]";
    }
}
