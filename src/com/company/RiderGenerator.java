package com.company;
import java.util.Random;

public class RiderGenerator implements  Runnable{
    private float arrivalMeanTime;
    private WaitingArea waitingArea;
    private static Random random;

    public RiderGenerator(float arrivalMeanTime, WaitingArea waitingArea) {
        this.arrivalMeanTime = arrivalMeanTime;
        this.waitingArea = waitingArea;
        random = new Random();
    }

    public void run() {
        int riderIndex = 1;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Rider rider = new Rider(waitingArea.getMultiplex(), waitingArea.getBus(), waitingArea.getAllAboard(), waitingArea.getMutex(), riderIndex, waitingArea);
                (new Thread(rider)).start();
                riderIndex++;
                float lambda = 1 / arrivalMeanTime;
                long t =  Math.round(-Math.log(1 - random.nextFloat()) / lambda);
                Thread.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
