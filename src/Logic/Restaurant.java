package Logic;

import GUI.App;
import Logic.Consumer;
import Logic.Meson;
import Logic.Producer;
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;

public class Restaurant {

    //  SEMAPHORES
    private Semaphore seMutex, smMutex, seProd, smProd, seCon, smCon, sdMutex, sdProd, sdCon, sManMutex;

    //  MESONS
    private Meson eMes, mMes, dMes;

    //  PRODUCERS (Chefs)
    private Producer[] eProd, mProd, dProd;

    //  CONSUMERS (Waiters)
    private Consumer[] waiters;

    //  MANAGER
    private Reader manager;

    //  WAITERS BOSS
    private Writer wBoss;

    //  WORKERS COUNTERS
    private int eProdCounter, mProdCounter, dProdCounter, wCounter;

    //  FOOD AND ORDERS COUNTERS
    private static int eCount, mCount, dCount, oCount;

    //  POINTERS
    public static int ePointer, mPointer, dPointer, eCosPointer, mCosPointer, dCosPointer;

    private int workTime;
    private int timeForClosing;
    private App app;

    /**
     *
     * @param workTime
     * @param timeForClosing
     * @param eProdLimit
     * @param mProdLimit
     * @param dProdLimit
     * @param waitersLimit
     * @param eProdInit
     * @param mProdInit
     * @param dProdInit
     * @param waiterInit
     * @param eMesLimit
     * @param mMesLimit
     * @param dMesLimit
     */
    public Restaurant(int workTime, int timeForClosing, int eProdLimit, int mProdLimit, int dProdLimit, int waitersLimit, int eProdInit, int mProdInit, int dProdInit, int waiterInit, int eMesLimit, int mMesLimit, int dMesLimit, App app) {

        this.workTime = workTime;
        this.timeForClosing = timeForClosing;
        this.app = app;

        this.eMes = new Meson(eMesLimit, "Entrada");
        this.mMes = new Meson(mMesLimit, "Plato Fuerte");
        this.dMes = new Meson(dMesLimit, "Postresito :P");

        this.seProd = new Semaphore(eMesLimit);
        this.smProd = new Semaphore(mMesLimit);
        this.sdProd = new Semaphore(dMesLimit);

        this.seMutex = new Semaphore(1);
        this.smMutex = new Semaphore(1);
        this.sdMutex = new Semaphore(1);

        this.sManMutex = new Semaphore(1);

        this.seCon = new Semaphore(0);
        this.smCon = new Semaphore(0);
        this.sdCon = new Semaphore(0);

        this.eProd = new Producer[eProdLimit];
        this.mProd = new Producer[mProdLimit];
        this.dProd = new Producer[dProdLimit];
        this.waiters = new Consumer[waitersLimit];

        this.eProdCounter = 0;
        this.mProdCounter = 0;
        this.dProdCounter = 0;
        this.wCounter = 0;

        Restaurant.ePointer = 0;
        Restaurant.mPointer = 0;
        Restaurant.dPointer = 0;

        Restaurant.eCosPointer = 0;
        Restaurant.mCosPointer = 0;
        Restaurant.dCosPointer = 0;

        Restaurant.eCount = 0;
        Restaurant.mCount = 0;
        Restaurant.dCount = 0;

        for (int i = 0; i < eProdInit; i++) {
            this.hireEntryChef();
        }

        for (int i = 0; i < mProdInit; i++) {
            this.hireMainChef();
        }

        for (int i = 0; i < dProdInit; i++) {
            this.hireDessertChef();
        }

        for (int i = 0; i < waiterInit; i++) {
            this.hireWaiter();
        }

        this.manager = new Reader(this.getHours(0.25), this.getHours(0.45), this.getHours(0.1), this.timeForClosing, this.sManMutex);
        this.manager.start();

        this.wBoss = new Writer(this.timeForClosing, this.getHours(0.05), this.getHours(8 - 0.05), this.sManMutex);
        this.wBoss.start();
    }

    public int getHours(double hours) {
        return (int) (hours * this.workTime * 1000) / 8;
    }

    public boolean hireEntryChef() {
        if (this.eProdCounter == this.eProd.length) {
            System.out.println("Can't hire any more Chef!");
            return false;
        } else {
            for (int i = 0; i < this.eProd.length; i++) {
                if (this.eProd[i] == null) {
                    this.eProd[i] = new Producer(eMes, seMutex, seProd, seCon, this.getHours(0.25), 1, "Chef Entrada");
                    this.eProd[i].start();
                    this.eProdCounter++;
                    if (!this.app.getH1().isEnabled()) {
                        this.app.getH1().setEnabled(true);
                    }
                    return true;
                }
            }
        }

        return false;
    }

    public boolean hireMainChef() {
        if (this.mProdCounter == this.mProd.length) {
            System.out.println("Can't hire any more Chef!");
            return false;
        } else {
            for (int i = 0; i < this.mProd.length; i++) {
                if (this.mProd[i] == null) {
                    this.mProd[i] = new Producer(mMes, smMutex, smProd, smCon, this.getHours(0.33), 2, "Chef Fuerte");
                    this.mProd[i].start();
                    this.mProdCounter++;
                    return true;
                }
            }
        }

        return false;
    }

    public boolean hireDessertChef() {
        if (this.dProdCounter == this.dProd.length) {
            System.out.println("Can't hire any more Chef!");
            return false;
        } else {
            for (int i = 0; i < this.dProd.length; i++) {
                if (this.dProd[i] == null) {
                    this.dProd[i] = new Producer(dMes, sdMutex, sdProd, sdCon, this.getHours(0.30), 3, "Chef Postre");
                    this.dProd[i].start();
                    this.dProdCounter++;
                    return true;
                }
            }
        }

        return false;
    }

    public boolean hireWaiter() {
        if (this.wCounter == this.waiters.length) {
            System.out.println("Can't hire any more Waiter!");
            return false;
        } else {
            for (int i = 0; i < this.waiters.length; i++) {
                if (this.waiters[i] == null) {
                    this.waiters[i] = new Consumer(eMes, mMes, dMes, seMutex, seProd, seCon, smMutex, smProd, smCon, sdMutex, sdProd, sdCon, this.getHours(0.15));
                    this.waiters[i].start();
                    this.wCounter++;

                    return true;
                }
            }
        }

        return false;
    }

    public boolean fireEntryChef() {
        if (this.eProdCounter < 0) {
            JOptionPane.showMessageDialog(null, "There's no any more chef!");
            return false;
        } else {
            for (int i = this.eProd.length - 1; i >= 0; i--) {
                if (this.eProd[i] != null) {
                    this.eProd[i].Fire();
                    this.eProd[i] = null;
                    this.eProdCounter--;

                    return true;
                }
            }
        }

        return false;
    }

    public boolean fireMainChef() {
        if (this.mProdCounter < 0) {
            JOptionPane.showMessageDialog(null, "There's no any more chef!");
            return false;
        } else {
            for (int i = this.mProd.length - 1; i >= 0; i--) {
                if (this.mProd[i] != null) {
                    this.mProd[i].Fire();
                    this.mProd[i] = null;
                    this.mProdCounter--;

                    return true;
                }
            }
        }

        return false;
    }

    public boolean fireDesChef() {
        if (this.dProdCounter < 0) {
            JOptionPane.showMessageDialog(null, "There's no any more chef!");
            return false;
        } else {
            for (int i = this.dProd.length - 1; i >= 0; i--) {
                if (this.dProd[i] != null) {
                    this.dProd[i].Fire();
                    this.dProd[i] = null;
                    this.dProdCounter--;

                    return true;
                }
            }
        }

        return false;
    }

    public boolean fireWaiter() {
        if (this.wCounter < 0) {
            JOptionPane.showMessageDialog(null, "There's no any more chef!");
            return false;
        } else {
            for (int i = this.waiters.length - 1; i >= 0; i--) {
                if (this.waiters[i] != null) {
                    this.waiters[i].Fire();
                    this.waiters[i] = null;
                    this.wCounter--;

                    return true;
                }
            }
        }

        return false;
    }

    public static void addEntryCount() {
        Restaurant.eCount++;
    }

    public static void addMainCount() {
        Restaurant.mCount++;
    }

    public static void addDesCount() {
        Restaurant.dCount++;
    }

    public static void subEntryCount() {
        Restaurant.eCount -= 3;
    }

    public static void subMainCount() {
        Restaurant.mCount -= 2;
    }

    public static void subDesCount() {
        Restaurant.dCount--;
    }

    public static void serveOrders() {
        Restaurant.oCount++;
    }

    public int getEntryCount() {
        return Restaurant.eCount;
    }

    public int getMainCount() {
        return Restaurant.mCount;
    }

    public int getDesCount() {
        return Restaurant.dCount;
    }

    public int getOrdersCount() {
        return Restaurant.oCount;
    }

    public int getEntryChefCounter() {
        return this.eProdCounter;
    }

    public int getMainChefCounter() {
        return this.mProdCounter;
    }

    public int getDessertChefCounter() {
        return this.dProdCounter;
    }

    public int getWaiterCounter() {
        return this.wCounter;
    }

    public Logic.Writer getWriter() {
        return this.wBoss;
    }

    public Logic.Reader getReader() {
        return this.manager;
    }

}
