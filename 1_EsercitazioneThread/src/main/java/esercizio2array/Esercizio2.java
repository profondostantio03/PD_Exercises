package esercizio2array;

/*
 * @author luigi
 */
public class Esercizio2 {
    public static final int arrayDim = 7200000;
    public static final int nThreads = 5;
    public static final int block = arrayDim/nThreads;
    
    public static void main(String[] args){
        int[] noThreads = new int[arrayDim];
        
        long start = System.currentTimeMillis();
        //Thread[] threads = new Thread[nThreads];
        
        for(int i=0; i<arrayDim; i++){
            noThreads[i] = 42;
        }
        
        long end = System.currentTimeMillis();
        System.out.println("Tempo di esecuzione: " + (end - start) + " ms");
        
        int[] withThreads = new int[arrayDim];
        ArrayTask[] array = new ArrayTask[nThreads];
        
        long start_threads = System.currentTimeMillis();
        
        for(int i=0; i<nThreads; i++){
            array[i]= new ArrayTask(withThreads, i*block, block, 42);
            array[i].start();
        }
        
        try {
            for(int i = 0; i < nThreads; i++) {
                array[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long end_threads = System.currentTimeMillis();
        System.out.println("Tempo di esecuzione: " + (end_threads - start_threads) + " ms");
    }
}
