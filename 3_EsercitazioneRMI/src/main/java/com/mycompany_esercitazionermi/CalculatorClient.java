package com.mycompany_esercitazionermi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {
    public static void main(String[] args) {
        String host = (args.length > 0) ? args[0] : "localhost";

        try {
            // Connessione al Registry RMI sull'host specificato
            Registry registry = LocateRegistry.getRegistry(host, 1099);

            // Lookup del riferimento remoto
            Calculator calc = (Calculator) registry.lookup("CalculatorService");

            // Test delle operazioni aritmetiche
            double x = 12.5;
            double y = 2.5;

            System.out.println("--- Test Operazioni Calcolatrice ---");
            System.out.println(x + " + " + y + " = " + calc.somma(x, y));
            System.out.println(x + " - " + y + " = " + calc.sottrazione(x, y));
            System.out.println(x + " * " + y + " = " + calc.moltiplicazione(x, y));
            System.out.println(x + " / " + y + " = " + calc.divisione(x, y));

            // Test gestione divisione per zero
            try {
                System.out.println("\nTentativo divisione per zero: " + x + " / 0");
                calc.divisione(x, 0);
            } catch (ArithmeticException ae) {
                System.err.println("Eccezione catturata correttamente: " + ae.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Errore sul client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}