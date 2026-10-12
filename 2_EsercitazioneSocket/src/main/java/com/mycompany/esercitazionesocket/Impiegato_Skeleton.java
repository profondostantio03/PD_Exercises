package com.mycompany.esercitazionesocket;

import java.io.*;
import java.net.*;

public class Impiegato_Skeleton extends Thread {
    private ImpiegatoServer mioServer;

    public Impiegato_Skeleton(ImpiegatoServer server) {
        this.mioServer = server;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("Attendo connessioni...");
            Socket socket = serverSocket.accept();
            System.out.println("Accettata una connessione... attendo comandi.");

            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                String metodo = (String) inStream.readObject();
                System.out.println("Comando richiesto: " + metodo);

                if (metodo.equals("getNome")) {
                    outStream.writeObject(mioServer.getNome());
                    outStream.flush();
                } else if (metodo.equals("getID")) {
                    outStream.writeObject(mioServer.getID());
                    outStream.flush();
                } else if (metodo.equals("getStipendio")) {
                    outStream.writeInt(mioServer.getStipendio());
                    outStream.flush();
                } else if (metodo.equals("aumentaStipendio")) {
                    int parametro = inStream.readInt();
                    outStream.writeInt(mioServer.aumentaStipendio(parametro));
                    outStream.flush();
                } else {
                    break;
                }
            }
            socket.close();
            serverSocket.close();
        } catch (EOFException e) {
            System.out.println("Terminata la connessione");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ImpiegatoServer impiegato = new ImpiegatoServer("Mario Rossi", "01721", 30000);
        Impiegato_Skeleton skel = new Impiegato_Skeleton(impiegato);
        skel.start();
    }
}
