/*
    |---------------------------------------|
    |            GENERAL MANAGER            |
    |---------------------------------------|
 */
package Logic;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader extends Thread {

    private static boolean status;
    private int hoursToClose, minTime, maxTime, changeTime;
    public static int orders;
    private Semaphore mutex;

    //Constructor
    public Reader(int minTime, int maxTime, int changeTime, int hoursToClose, Semaphore mutex) {
        this.maxTime = maxTime;
        this.minTime = minTime;
        this.changeTime = changeTime;
        this.hoursToClose = hoursToClose;
        this.status = true;
        this.mutex = mutex;
    }

    //reinicializar el contador le toma 0.1 horas al gerente (Falta colocar esto)
    @Override
    public void run() {
        while (true) {
            try {
                //int random = this.minTIme + ((int) (Math.random() * (this.maxTime-this.minTime)) + 1);
                int random = ((int) (Math.random() * (this.maxTime - this.minTime)) + this.minTime);
                this.status = true;
                Thread.sleep(this.minTime * 4 - random);                
                //System.out.println("Reader Reading");
                this.mutex.acquire();
                /*System.out.println("**************");
                System.out.println("Reader Reading");
                System.out.println("**************");*/
                if (Writer.getHoursLeft() == 0) {
                    this.orders = 0;
                    Thread.sleep(this.changeTime);
                    Writer.initHoursLeft(this.hoursToClose);
                    /*System.out.println("/////////////////////////////////////////////");
                    System.out.println("New Day, new Objects... Starting Positively :)");
                    System.out.println("/////////////////////////////////////////////");*/
                }
                this.status = false;
                this.mutex.release();
                /*System.out.println("zZzZzZzZzZzZzZzZzZzZzZ");
                System.out.println("Reader Sleeping zZzZzZ");
                System.out.println("zZzZzZzZzZzZzZzZzZzZzZ");*/
                Thread.sleep(random);
            } catch (InterruptedException ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String status() {
        if (Reader.status) {
            return "Working";
        } else {
            return "Resting";
        }
    }

    public static int getOrders() {
        return Reader.orders;
    }

    public static void addOrder() {
        Reader.orders++;
    }

}
