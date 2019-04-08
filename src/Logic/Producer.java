/*
    |---------------------------------------|
    |                 CHEFS                 |
    |---------------------------------------|
 */
package Logic;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {

    private Meson mes;
    private Semaphore mutex, sProd, sCon;
    private int time, type;
    private boolean hired;
    private String name;

    public Producer(Semaphore mutex, Semaphore sProd, Semaphore sCon, int type, String name) {
        this.mutex = mutex;
        this.sProd = sProd;
        this.sCon = sCon;
        this.type = type;
        this.hired = true;
        this.name = name;
    }

    public Producer(Meson mes, Semaphore mutex, Semaphore sProd, Semaphore sCon, int time, int type, String name) {
        this.mes = mes;
        this.mutex = mutex;
        this.sProd = sProd;
        this.sCon = sCon;
        this.time = time;
        this.type = type;
        this.hired = true;
        this.name = name;
        System.out.println("Chef Hired!");
    }

    @Override
    public void run() {
        while (this.hired) {
            try {
                this.sProd.acquire();
                Thread.sleep(this.time);
                this.mutex.acquire();
                switch (this.type) {
                    case 1:
                        this.mes.cook(Restaurant.ePointer, type);
                        System.out.println("Entry Cooked || Space left " + this.mes.getSpaceLeft());
                        Restaurant.ePointer = (Restaurant.ePointer + 1) % this.mes.getSize();
                        Restaurant.addEntryCount();
                        break;
                    case 2:
                        this.mes.cook(Restaurant.mPointer, type);
                        System.out.println("Main Cooked || Space left " + this.mes.getSpaceLeft());
                        Restaurant.mPointer = (Restaurant.mPointer + 1) % this.mes.getSize();
                        Restaurant.addMainCount();
                        break;
                    case 3:
                        this.mes.cook(Restaurant.dPointer, type);
                        System.out.println("Dessert Cooked || Space left " + this.mes.getSpaceLeft());
                        Restaurant.dPointer = (Restaurant.dPointer + 1) % this.mes.getSize();
                        Restaurant.addDesCount();
                        break;
                }
                this.mutex.release();
                this.sCon.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Fire() {
        this.hired = false;
    }
}
