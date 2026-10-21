package com.mycompany_esercitazionermi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    double somma(double a, double b) throws RemoteException;
    double sottrazione(double a, double b) throws RemoteException;
    double moltiplicazione(double a, double b) throws RemoteException;
    double divisione(double a, double b) throws RemoteException, ArithmeticException;
}
