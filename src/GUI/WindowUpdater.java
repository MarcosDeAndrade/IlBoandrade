/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.Reader;
import Logic.Restaurant;
import Logic.Writer;

/**
 *
 * @author Marcos
 */
public class WindowUpdater extends Thread {

    private Restaurant restaurant;
    private Writer wBoss;
    private Reader manager;

    public WindowUpdater(Restaurant restaurant, Writer wBoss, Reader manager) {
        this.restaurant = restaurant;
        this.wBoss = wBoss;
        this.manager = manager;
    }

    @Override
    public void run() {
        while (true) {
            App.wLabel.setText("" + this.restaurant.getWaiterCounter());
            App.eLabel.setText("" + this.restaurant.getEntryChefCounter());
            App.mLabel.setText("" + this.restaurant.getMainChefCounter());
            App.dLabel.setText("" + this.restaurant.getDessertChefCounter());
            App.wOrdersLabel.setText("" + this.manager.getOrders());
            App.eFoodLabel.setText("" + this.restaurant.getEntryCount());
            App.mFoodLabel.setText("" + this.restaurant.getMainCount());
            App.dFoodLabel.setText("" + this.restaurant.getDesCount());
            App.managerLbl.setText(this.manager.status());
            App.wBossLbl.setText(this.wBoss.statusWaiter());
            App.timer.setText("" + this.wBoss.getHoursLeft() + ":00hs");
        }
    }
}
