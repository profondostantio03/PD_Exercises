package com.mycompany.esercitazionethread;

/**
 *
 * @author luigi
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        // Crea 4 thread che incrementano tutti lo stesso contatore
        Thread t1 = new Thread(new IncrementatoreTask(counter));
        Thread t2 = new Thread(new IncrementatoreTask(counter));
        Thread t3 = new Thread(new IncrementatoreTask(counter));
        Thread t4 = new Thread(new IncrementatoreTask(counter));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Attende che i thread finiscano
        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Valore del contatore: " + counter.getCount());
    }
}
