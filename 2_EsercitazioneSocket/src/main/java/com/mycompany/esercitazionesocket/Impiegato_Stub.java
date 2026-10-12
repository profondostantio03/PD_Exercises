package com.mycompany.esercitazionesocket;

import java.io.*;
import java.net.*;

public class Impiegato_Stub implements Impiegato {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Impiegato_Stub(String host) throws Throwable {
        socket = new Socket(host, 9000);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public String getNome() throws Throwable {
        out.writeObject("getNome");
        out.flush();
        return (String) in.readObject();
    }

    @Override
    public String getID() throws Throwable {
        out.writeObject("getID");
        out.flush();
        return (String) in.readObject();
    }

    @Override
    public int getStipendio() throws Throwable {
        out.writeObject("getStipendio");
        out.flush();
        return in.readInt();
    }

    @Override
    public int aumentaStipendio(int diQuanto) throws Throwable {
        out.writeObject("aumentaStipendio");
        out.writeInt(diQuanto);
        out.flush();
        return in.readInt();
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
