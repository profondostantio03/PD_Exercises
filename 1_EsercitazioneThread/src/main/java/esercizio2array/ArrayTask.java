package esercizio2array;

public class ArrayTask extends Thread{
    public int array[];
    public int startIndex, endIndex, value;
    
    public ArrayTask(int[] array, int startIndex, int endIndex, int value){
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.value = value;
    }
    
    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            array[i] = value;
        }
    }
}
