package com.company;

import java.util.concurrent.Semaphore;

public class WaitingArea {
    private static int ridersCount = 0;
    private static int maxBusCapacity = 50;
    private static final Semaphore multiplex = new Semaphore(50); // waiting area entrance sem
    private static final Semaphore bus = new Semaphore(0);
    private static final Semaphore allAboard = new Semaphore(0);
    private static final Semaphore mutex = new Semaphore(1);

    public void incRidersCount(){
        ridersCount ++;
    }

    public void decRidersCount(){
        ridersCount--;
    }

    public Semaphore getMultiplex(){
        return multiplex;
    }

    public Semaphore getBus(){
        return bus;
    }

    public Semaphore getAllAboard(){
        return allAboard;
    }

    public Semaphore getMutex(){
        return mutex;
    }

    public int getRidersCount(){
        return ridersCount;
    }
}
