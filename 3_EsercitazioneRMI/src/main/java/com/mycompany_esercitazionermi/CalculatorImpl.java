package com.mycompany_esercitazionermi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
    private static final long serialVersionUID = 1L;

    public CalculatorImpl() throws RemoteException {
        super();
    }

    @Override
    public double somma(double a, double b) throws RemoteException {
        return a + b;
    }

    @Override
    public double sottrazione(double a, double b) throws RemoteException {
        return a - b;
    }

    @Override
    public double moltiplicazione(double a, double b) throws RemoteException {
        return a * b;
    }

    @Override
    public double divisione(double a, double b) throws RemoteException, ArithmeticException {
        if (b == 0.0) {
            throw new ArithmeticException("Errore: divisione per zero non consentita.");
        }
        return a / b;
    }
}
