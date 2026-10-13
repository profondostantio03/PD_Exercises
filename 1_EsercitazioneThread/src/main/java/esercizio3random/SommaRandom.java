package esercizio3random;
import java.util.Random;
/*
 * @author luigi
 */
public class SommaRandom {
    public static final int arrayDim = 1200000;
    public static final int nThreads = 4; // Numero di thread scelto da te
    public static final int block = arrayDim / nThreads;

    public static void main(String[] args) {
        // inizializzazione dell'array con valori pseudo-casuali
        int[] array = new int[arrayDim];
        Random rand = new Random();
        for (int i = 0; i < arrayDim; i++) {
            array[i] = rand.nextInt(10); // Numeri casuali tra 0 e 9[cite: 51]
        }


        // 1. ESECUZIONE SENZA THREAD (Sequenziale)
        
        long startSeq = System.currentTimeMillis();
        long sommaSequenziale = 0;
        for (int i = 0; i < arrayDim; i++) {
            sommaSequenziale += array[i];
        }
        long endSeq = System.currentTimeMillis();
        
        System.out.println("=== 1. SENZA THREAD ===");
        System.out.println("Somma totale: " + sommaSequenziale);
        System.out.println("Tempo impiegato: " + (endSeq - startSeq) + " ms\n");

        
        // 2. ESECUZIONE CON I THREAD (Concorrente)
        
        SumTask[] tasks = new SumTask[nThreads];
        
        long startThreads = System.currentTimeMillis();

        // Creazione e avvio dei thread
        for (int i = 0; i < nThreads; i++) {
            int inizio = i * block;
            // L'ultimo thread gestisce anche gli eventuali resti della divisione
            int fine = (i == nThreads - 1) ? arrayDim : (inizio + block);

            tasks[i] = new SumTask(array, inizio, fine, i);
            tasks[i].start(); // Avvia il thread[cite: 40, 41]
        }

        // Sincronizzazione e raccolta delle somme parziali
        long sommaTotaleThread = 0;
        try {
            System.out.println("=== 2. CON " + nThreads + " THREAD ===");
            for (int i = 0; i < nThreads; i++) {
                tasks[i].join(); // Il main attende la terminazione del thread
                long parziale = tasks[i].getSommaParziale();
                
                // Stampiamo la somma parziale di ogni singolo thread
                System.out.println("Thread " + i + " (indici da " + tasks[i].getStartIndex() 
                                   + " a " + (tasks[i].getEndIndex() - 1) + ") -> Somma parziale: " + parziale);
                
                sommaTotaleThread += parziale;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endThreads = System.currentTimeMillis();
        
        System.out.println("----------------------------------------");
        System.out.println("Somma totale combinata: " + sommaTotaleThread);
        System.out.println("Tempo impiegato: " + (endThreads - startThreads) + " ms");
    }
}
