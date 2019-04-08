/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.threads;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel
 */
public class Consumer extends Thread {

    Semaphore seMutex, seProd, seCon;
    Semaphore smMutex, smProd, smCon;
    Semaphore sdMutex, sdProd, sdCon;
    boolean hired;

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

    @Override
    public void run() {
        while (this.hired) {
            try {
                this.seCon.acquire(3);
                this.seMutex.acquire();
                for (int i = 0; i < 3; i++) {
                    System.out.println("Consumer entry " + this.getName() + " || Queue => " + seCon.getQueueLength());
                }
                this.seMutex.release();
                this.seProd.release(3);

                this.smCon.acquire(2);
                this.smMutex.acquire();
                for (int i = 0; i < 2; i++) {
                    System.out.println("Consumer main " + this.getName() + " || Queue => " + seCon.getQueueLength());
                }
                this.smMutex.release();
                this.smProd.release(2);

                this.sdCon.acquire();
                this.sdMutex.acquire();
                System.out.println("Consumer dessert " + this.getName() + " || Queue => " + seCon.getQueueLength());
                this.sdMutex.release();
                this.sdProd.release();
                
                this.Serve();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void Serve() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Served");
    }

    public void Fire() {
        this.hired = false;
    }
}
