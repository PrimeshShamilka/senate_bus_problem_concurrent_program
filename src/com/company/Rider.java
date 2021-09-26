package com.company;
import java.util.concurrent.Semaphore;

public class Rider implements Runnable{
    private final Semaphore multiplex;
    private final Semaphore bus;
    private final Semaphore allAboard;
    private final Semaphore mutex;
    private final int index;
    private WaitingArea waitingArea;

    public Rider(Semaphore multiplex, Semaphore bus, Semaphore allAboard, Semaphore mutex, int index, WaitingArea waitingArea){
        this.multiplex = multiplex;
        this.bus = bus;
        this.allAboard = allAboard;
        this.mutex = mutex;
        this.index = index;
        this.waitingArea = waitingArea;
    }

    public void run(){
        try {
            multiplex.acquire();
                mutex.acquire();
                    waitingArea.incRidersCount();
                mutex.release();
                bus.acquire();
            multiplex.release();
            boardBus();
            waitingArea.decRidersCount();
            if (waitingArea.getRidersCount() == 0){
                allAboard.release();
            }
            else {
                bus.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void boardBus(){
        System.out.println("Rider : " + index + " boarded");
    }

    public void enterWaitingArea(){
        System.out.println("Rider : " + index + " entered waiting area" );
    }

}
