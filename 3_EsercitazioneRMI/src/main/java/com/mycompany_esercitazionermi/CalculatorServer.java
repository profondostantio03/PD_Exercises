package com.mycompany_esercitazionermi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            // Istanziazione dell'oggetto remoto concreto
            Calculator calcolatrice = new CalculatorImpl();

            // Avvio del registry locale integrato sulla porta 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Registrazione dello stub nel servizio di naming
            registry.rebind("CalculatorService", calcolatrice);

            System.out.println("CalculatorServer avviato e pronto all'ascolto sulla porta 1099.");
        } catch (Exception e) {
            System.err.println("Errore durante l'avvio del server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}