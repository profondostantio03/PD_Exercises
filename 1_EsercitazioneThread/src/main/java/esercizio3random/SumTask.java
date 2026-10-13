package esercizio3random;

/*
 * @author luigi
 */
public class SumTask extends Thread{
    private int[] array;
    private int startIndex, endIndex, threadId;
    private long sommaParziale;

    public SumTask(int[] array, int startIndex, int endIndex, int threadId) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.threadId = threadId;
        this.sommaParziale = 0;
    }

    @Override
    public void run() {
        // Ciascun thread somma solo la sua porzione di array
        for (int i = startIndex; i < endIndex; i++) {
            sommaParziale += array[i];
        }
    }

    // Metodi di accesso per leggere i risultati e gli intervalli
    public long getSommaParziale() {
        return sommaParziale;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}
