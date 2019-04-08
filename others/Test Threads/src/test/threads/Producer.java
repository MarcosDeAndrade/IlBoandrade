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
public class Producer extends Thread {

    Semaphore mutex, sProd, sCon;
    int type;
    boolean hired;

    public Producer(Semaphore mutex, Semaphore sProd, Semaphore sCon, int type) {
        this.mutex = mutex;
        this.sProd = sProd;
        this.sCon = sCon;
        this.type = type;
        this.hired = true;
    }

    @Override
    public void run() {
        while (this.hired) {
            try {
                this.sProd.acquire();
                this.mutex.acquire();
                System.out.println("Prod " + this.getName() + ' ' + type + " || Queue => " + sProd.getQueueLength());

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
