/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.threads;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Samuel
 */
public class main {

    Semaphore seMutex, smMutex, seProd, smProd, seCon, smCon, sdMutex, sdProd, sdCon;
    Producer[] eProd, mProd, dProd;
    Consumer con, con2, con3;
    Consumer[] con4;

    public main(int x, int y, int z, int a) {
        this.seProd = new Semaphore(x);
        this.smProd = new Semaphore(y);
        this.sdProd = new Semaphore(z);
        this.seMutex = new Semaphore(1);
        this.smMutex = new Semaphore(1);
        this.sdMutex = new Semaphore(z);
        this.seCon = new Semaphore(0);
        this.smCon = new Semaphore(0);
        this.sdCon = new Semaphore(0);

        this.eProd = new Producer[x];
        this.mProd = new Producer[y];
        this.dProd = new Producer[z];

        for (int i = 0; i < eProd.length; i++) {
            if (this.eProd[i] == null) {
                this.eProd[i] = new Producer(seMutex, seProd, seCon, 1);
                this.eProd[i].start();
            }
        }
        for (int i = 0; i < mProd.length; i++) {
            if (this.mProd[i] == null) {
                this.mProd[i] = new Producer(smMutex, smProd, smCon, 2);
                this.mProd[i].start();
            }
        }
        for (int i = 0; i < dProd.length; i++) {
            if (this.dProd[i] == null) {
                this.dProd[i] = new Producer(sdMutex, sdProd, sdCon, 3);
                this.dProd[i].start();
            }
        }

        //this.con = new Consumer(seMutex, seProd, seCon, smMutex, smProd, smCon);
        //this.con.start();
        //this.con2 = new Consumer(seMutex, seProd, seCon);        
        //this.con2.start();
        //this.con3 = new Consumer(seMutex, seProd, seCon, smMutex, smProd, smCon, sdMutex, sdProd, sdCon);
        //this.con3.start();
        this.con4 = new Consumer[a];
        for (int i = 0; i < con4.length; i++) {
            this.con4[i] = new Consumer(seMutex, seProd, seCon, smMutex, smProd, smCon, sdMutex, sdProd, sdCon);
            this.con4[i].start();
        }
    }

    public static void main(String[] args) {
        new main(5, 4, 3, 2);
    }

}
