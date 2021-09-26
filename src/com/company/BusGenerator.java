package com.company;
import java.util.Random;

public class BusGenerator implements  Runnable{
    private float arrivalMeanTime;
    private WaitingArea waitingArea;
    private static Random random;

    public BusGenerator(float arrivalMeanTime, WaitingArea waitingArea) {
        this.arrivalMeanTime = arrivalMeanTime;
        this.waitingArea = waitingArea;
        random = new Random();
    }

    public void run() {
        int busIndex = 1;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Bus bus = new Bus(waitingArea.getMultiplex(), waitingArea.getBus(), waitingArea.getAllAboard(), waitingArea.getMutex(), busIndex, waitingArea);
                (new Thread(bus)).start();
                busIndex++;
                float lambda = 1 / arrivalMeanTime;
                long t =  Math.round(-Math.log(1 - random.nextFloat()) / lambda);
                Thread.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All buses have finished arriving");
    }
}
