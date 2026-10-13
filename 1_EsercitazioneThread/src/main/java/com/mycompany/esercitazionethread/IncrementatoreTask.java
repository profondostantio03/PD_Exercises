package com.mycompany.esercitazionethread;

class IncrementatoreTask implements Runnable {
    private Counter counter;

    public IncrementatoreTask(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.increment();
        }
    }
}
