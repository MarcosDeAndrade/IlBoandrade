/*
    |---------------------------------------|
    |              WAITER BOSS              |
    |---------------------------------------|
 */
package Logic;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Writer extends Thread {

    private static int hoursLeft;
    private int workTime, breakTime;
    private static boolean status;
    private Semaphore mutex;

    public Writer(int hoursLeft, int workTime, int breakTime, Semaphore mutex) {
        this.hoursLeft = hoursLeft;
        this.workTime = workTime;
        this.breakTime = breakTime;
        this.status = false;
        this.mutex = mutex;
    }

    //Methods
    @Override
    public void run() {
        while (true) {
            try {
                this.mutex.acquire();
                this.status = true;
                Thread.sleep(workTime);
                /*System.out.println("*-*-*-*-*-*-*-*");
                System.out.println("Writer Working");
                System.out.println("*-*-*-*-*-*-*-*");*/
                this.hoursLeft--;

                /*System.out.println("*-*-*-*-*-*-*-*");
                System.out.println("Time for closing: " + hoursLeft);
                System.out.println("*-*-*-*-*-*-*-*");*/
                this.status = false;
                this.mutex.release();

                /*System.out.println("*-*-*-*-*-*-*-*");
                System.out.println("I'm Sleeping Now zZzZ");
                System.out.println("*-*-*-*-*-*-*-*");*/
                Thread.sleep(breakTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static int getHoursLeft() {
        return Writer.hoursLeft;
    }

    public static void initHoursLeft(int hoursToClose) {
        Writer.hoursLeft = hoursToClose;
    }

    //If status is true the waiter is working. If status is false the waiter is resting
    public static String statusWaiter() {
        if (Writer.status) {
            return "Working";
        } else {
            return "Resting";
        }
    }

}
