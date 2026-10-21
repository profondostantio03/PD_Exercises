package esercizio3votazione;

import java.io.Serializable;

/*
 * @author luigi
 */
public class Candidato implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private final String nome;
    private int numeroVoti;
    private double sommaPunteggi;
    
    public Candidato(String nome){
        this.nome = nome;
        this.numeroVoti = numeroVoti = 0;
        this.sommaPunteggi = 0.0;
    }
    
    public String getNome(){
        return nome;
    }
    
    public synchronized int getNumeroVoti(){
        return numeroVoti;
    }
    
    public synchronized double getMediaPunteggio(){
        if(numeroVoti == 0){
            return 0.0;
        }
        return sommaPunteggi/numeroVoti;
    }
    
    public synchronized void registraVoto(int punteggio){
        this.sommaPunteggi += punteggio;
        this.numeroVoti++;
    }
    
    @Override
    public String toString(){
        return "Candidato: " + nome + " | Voti totali: " + getNumeroVoti() + " | Media: " + String.format("%.f", getMediaPunteggio());
    }
}
