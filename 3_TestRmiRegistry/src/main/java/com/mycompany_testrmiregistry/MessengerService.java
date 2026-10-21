package com.mycompany_testrmiregistry;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessengerService extends Remote {
    String sendMessage(String message) throws RemoteException;
}
