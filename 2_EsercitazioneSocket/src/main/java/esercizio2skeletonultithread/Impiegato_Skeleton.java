package esercizio2skeletonultithread;

import java.io.*;
import java.net.*;

public class Impiegato_Skeleton {
    public static void main(String[] args) {
        ImpiegatoServer impiegato = new ImpiegatoServer("Mario Rossi", "01721", 30000);
        
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("Server multithread in ascolto sulla porta 9000...");
            
            // ciclo infinito per accettare continuamente nuove connessioni (da client diversi)
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Accettata una nuova connessione da un client.");
                
                // per ogni client accettato, creiamo e avviamo un nuovo thread dedicato
                new ClientHandler(socket, impiegato).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// classe thread per gestire le richieste concorrenti del singolo client
class ClientHandler extends Thread {
    private Socket socket;
    private ImpiegatoServer mioServer;

    public ClientHandler(Socket socket, ImpiegatoServer server) {
        this.socket = socket;
        this.mioServer = server;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                String metodo = (String) inStream.readObject();
                System.out.println("[" + Thread.currentThread().getName() + "] Comando richiesto: " + metodo);

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
        } catch (EOFException e) {
            System.out.println("[" + Thread.currentThread().getName() + "] Connessione terminata dal client.");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
