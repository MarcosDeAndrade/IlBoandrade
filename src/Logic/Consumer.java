/*
    |---------------------------------------|
    |                 WAITER                |
    |---------------------------------------|
 */
package Logic;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {

    private Meson eMes, mMes, dMes;
    private Semaphore seMutex, seProd, seCon;
    private Semaphore smMutex, smProd, smCon;
    private Semaphore sdMutex, sdProd, sdCon;
    private boolean hired;
    private int time;

    public Consumer(Semaphore mutex, Semaphore sProd, Semaphore sCon) {
        this.seMutex = mutex;
        this.seProd = sProd;
        this.seCon = sCon;
        this.hired = true;
    }

    public Consumer(Semaphore seMutex, Semaphore seProd, Semaphore seCon, Semaphore smMutex, Semaphore smProd, Semaphore smCon) {
        this.seMutex = seMutex;
        this.seProd = seProd;
        this.seCon = seCon;
        this.smMutex = smMutex;
        this.smProd = smProd;
        this.smCon = smCon;
        this.hired = true;
    }

    public Consumer(Semaphore seMutex, Semaphore seProd, Semaphore seCon, Semaphore smMutex, Semaphore smProd, Semaphore smCon, Semaphore sdMutex, Semaphore sdProd, Semaphore sdCon) {
        this.seMutex = seMutex;
        this.seProd = seProd;
        this.seCon = seCon;
        this.smMutex = smMutex;
        this.smProd = smProd;
        this.smCon = smCon;
        this.sdMutex = sdMutex;
        this.sdProd = sdProd;
        this.sdCon = sdCon;
        this.hired = true;
    }

    public Consumer(Meson eMes, Meson mMes, Meson dMes, Semaphore seMutex, Semaphore seProd, Semaphore seCon, Semaphore smMutex, Semaphore smProd, Semaphore smCon, Semaphore sdMutex, Semaphore sdProd, Semaphore sdCon, int time) {
        this.eMes = eMes;
        this.mMes = mMes;
        this.dMes = dMes;
        this.seMutex = seMutex;
        this.seProd = seProd;
        this.seCon = seCon;
        this.smMutex = smMutex;
        this.smProd = smProd;
        this.smCon = smCon;
        this.sdMutex = sdMutex;
        this.sdProd = sdProd;
        this.sdCon = sdCon;
        this.time = time;
        this.hired = true;
    }

    /*
    @Override
    public void run() {
        while (this.hired) {
            try {
                this.seCon.acquire(3);
                this.seMutex.acquire();
                for (int i = 0; i < 3; i++) {
                    this.eMes.cook(Restaurant.eCosPointer, 0);
                    Restaurant.eCosPointer = (Restaurant.eCosPointer + 1) % this.eMes.getSize();
                    Restaurant.subEntryCount();
                    System.out.println("Consumer entry " + this.getName() + " || Queue => " + seCon.getQueueLength());
                }
                this.seMutex.release();
                this.seProd.release(3);

                this.smCon.acquire(2);
                this.smMutex.acquire();
                for (int i = 0; i < 2; i++) {
                    this.mMes.cook(Restaurant.mCosPointer, 0);
                    Restaurant.mCosPointer = (Restaurant.mCosPointer + 1) % this.mMes.getSize();
                    Restaurant.subMainCount();
                    System.out.println("Consumer main " + this.getName() + " || Queue => " + seCon.getQueueLength());
                }
                this.smMutex.release();
                this.smProd.release(2);

                this.sdCon.acquire();
                this.sdMutex.acquire();
                this.dMes.cook(Restaurant.dCosPointer, 0);
                Restaurant.dCosPointer = (Restaurant.dCosPointer + 1) % this.dMes.getSize();
                Restaurant.subDesCount();
                System.out.println("Consumer dessert " + this.getName() + " || Queue => " + seCon.getQueueLength());
                this.sdMutex.release();
                this.sdProd.release();

                this.Serve();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }*/

    @Override
    public void run() {
        while (this.hired) {
            try {
                this.seCon.acquire(3);
                this.smCon.acquire(2);
                this.sdCon.acquire();

                this.seMutex.acquire();
                for (int i = 0; i < 3; i++) {
                    this.eMes.cook(Restaurant.eCosPointer, 0);
                    Restaurant.eCosPointer = (Restaurant.eCosPointer + 1) % this.eMes.getSize();
                }
                Restaurant.subEntryCount();
                this.seMutex.release();

                this.smMutex.acquire();
                for (int i = 0; i < 2; i++) {
                    this.mMes.cook(Restaurant.mCosPointer, 0);
                    Restaurant.mCosPointer = (Restaurant.mCosPointer + 1) % this.mMes.getSize();
                }
                Restaurant.subMainCount();
                this.smMutex.release();

                this.sdMutex.acquire();
                this.dMes.cook(Restaurant.dCosPointer, 0);
                Restaurant.dCosPointer = (Restaurant.dCosPointer + 1) % this.dMes.getSize();
                Restaurant.subDesCount();
                this.sdMutex.release();

                this.seProd.release(3);
                this.smProd.release(2);
                this.sdProd.release();

                this.Serve();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void Serve() throws InterruptedException {
        Thread.sleep(this.time);
        System.out.println("Served");
        Reader.addOrder();
    }

    public void Fire() {
        this.hired = false;
    }
}
