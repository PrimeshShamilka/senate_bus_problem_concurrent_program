package com.company;

import java.util.concurrent.Semaphore;

public class Bus implements  Runnable{
    private final Semaphore bus;
    private final Semaphore allAboard;
    private final Semaphore mutex;
    private final int index;
    private WaitingArea waitingArea;

    public Bus(Semaphore multiplex, Semaphore bus, Semaphore allAboard, Semaphore mutex, int index, WaitingArea waitingArea){
        this.bus = bus;
        this.allAboard = allAboard;
        this.mutex = mutex;
        this.index = index;
        this.waitingArea = waitingArea;
    }

    public void run(){
        try {
            mutex.acquire(); // Prevents rider arrivals after the bus arrive
                if (waitingArea.getRidersCount() > 0){
                    bus.release();
                    allAboard.acquire();
                }
            mutex.release();
            depart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void depart(){
        System.out.println("Bus : " + index + " departed");
    }

    public void arrive(){
        System.out.println("Bus : " + index + " arrived");
        System.out.println("Waiting rider count : " + waitingArea.getRidersCount());
    }

}
