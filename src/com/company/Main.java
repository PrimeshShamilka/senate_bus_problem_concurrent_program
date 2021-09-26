package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws  InterruptedException{
        float riderArrivalMeanTime = 30f * 10;
        float busArrivalMeanTime = 20 * 60f * 10;

        Scanner scanner = new Scanner(System.in);
        String userInput;
        WaitingArea waitingArea = new WaitingArea();

        System.out.println("\n*******  Press any key to exit.  *******\n" );

        RiderGenerator riderGenerator = new RiderGenerator(riderArrivalMeanTime, waitingArea);
        (new Thread(riderGenerator)).start();

        BusGenerator busGenerator = new BusGenerator(busArrivalMeanTime,waitingArea);
        (new Thread(busGenerator)).start();

        while(true){
            userInput = scanner.nextLine();
            if(userInput != null)
                System.exit(0);
        }
    }
}
