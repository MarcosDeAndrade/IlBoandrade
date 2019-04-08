/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Buttons.FireBtn;
import GUI.Buttons.HireBtn;
import Logic.Restaurant;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Marcos
 */
public class App extends JFrame {

    private final int width;
    private final int height;
    private final int btnPercent, marginPercent;
    private final int lblHeight;
    private final int btnSizeW, btnSizeH;
    private final int smallSquareBtnSize, marginX, marginY;

    private final JFrame parent;
    private final ImageIcon info, bg, bc, ex;
    private final JButton hideBtn;
    private final JButton exitBtn;
    private final JButton infoBtn;
    private final Cursor pointer;
    private final JLabel background;
    private final InfoWindow infoWindow;

    private final HireBtn h1, h2, h3, h4;
    private final FireBtn f1, f2, f3, f4;

    //  Avatars
    private final ImageIcon gm, wb, w, mc, ec, dc;
    private final JLabel gManager;
    private final JLabel wBoss;
    private final JLabel waiter;
    private final JLabel mChef;
    private final JLabel eChef;
    private final JLabel dChef;

    //  Text Labels
    public static JLabel wLabel;
    public static JLabel eLabel;
    public static JLabel mLabel;
    public static JLabel dLabel;
    public static JLabel wOrdersLabel;
    public static JLabel eFoodLabel;
    public static JLabel mFoodLabel;
    public static JLabel dFoodLabel;
    public static JLabel timer;
    public static JLabel managerLbl;
    public static JLabel wBossLbl;

    private final Font lblFont;
    private Restaurant restaurant;

    private int eProdLimit, mProdLimit, dProdLimit, waitersLimit, eProdInit, mProdInit, dProdInit, waiterInit;

    public App(final int width, final int height, final JFrame parent) {
        this.width = width;
        this.height = height;
        this.parent = parent;

        this.setState(JFrame.NORMAL);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);
        this.lblFont = new Font("Tahoma", 1, 36);

        this.bg = new ImageIcon(getClass().getResource("/Img/Background/4. Main.png"));
        this.background = new JLabel();
        this.background.setSize(new Dimension(width, height));
        this.background.setIcon(new ImageIcon(this.bg.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));

        this.pointer = new Cursor(Cursor.HAND_CURSOR);

        this.btnPercent = 2;
        this.marginPercent = 7;
        this.smallSquareBtnSize = (this.width * this.btnPercent) / 100;
        this.btnSizeH = this.smallSquareBtnSize + 20;
        this.btnSizeW = (this.btnSizeH * 386) / 144;
        this.marginX = (this.width * this.marginPercent + 3) / 100;
        this.marginY = (this.height * this.marginPercent) / 100;

        this.bc = new ImageIcon(getClass().getResource("/Img/Buttons/3. Minimize (Black).png"));
        this.hideBtn = new JButton();
        this.hideBtn.setSize(new Dimension(this.smallSquareBtnSize, this.smallSquareBtnSize));
        this.hideBtn.setIcon(new ImageIcon(this.bc.getImage().getScaledInstance(this.smallSquareBtnSize, this.smallSquareBtnSize, Image.SCALE_SMOOTH)));
        this.hideBtn.setLocation(this.width - (this.smallSquareBtnSize * 2) - 15 - ((this.width * (this.btnPercent + 3)) / 100), 40);
        this.hideBtn.setCursor(pointer);
        this.hideBtn.setContentAreaFilled(false);
        this.hideBtn.setBorder(null);
        this.hideBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hideBtnActionPerformed();
            }
        });

        this.info = new ImageIcon(getClass().getResource("/Img/Buttons/5. Info (Black).png"));
        this.infoBtn = new JButton();
        this.infoBtn.setSize(new Dimension(this.smallSquareBtnSize, this.smallSquareBtnSize));
        this.infoBtn.setIcon(new ImageIcon(this.info.getImage().getScaledInstance(this.smallSquareBtnSize, this.smallSquareBtnSize, Image.SCALE_SMOOTH)));
        this.infoBtn.setLocation(10, this.height - this.smallSquareBtnSize - 10);
        this.infoBtn.setContentAreaFilled(false);
        this.infoBtn.setFocusPainted(false);
        this.infoBtn.setCursor(pointer);
        this.infoBtn.setBorder(null);
        this.infoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                infoBtnActionPerformed();
            }
        });

        this.ex = new ImageIcon(getClass().getResource("/Img/Buttons/1. Close (Black).png"));
        this.exitBtn = new JButton();
        this.exitBtn.setSize(new Dimension(this.smallSquareBtnSize, this.smallSquareBtnSize));
        this.exitBtn.setIcon(new ImageIcon(this.ex.getImage().getScaledInstance(this.smallSquareBtnSize, this.smallSquareBtnSize, Image.SCALE_SMOOTH)));
        this.exitBtn.setLocation(this.width - this.smallSquareBtnSize - ((this.width * (this.btnPercent + 3)) / 100), 40);
        this.exitBtn.setContentAreaFilled(false);
        this.exitBtn.setCursor(pointer);
        this.exitBtn.setBorder(null);
        this.exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.showConfirmDialog(null, "Do you want to close the app?", "ATENTION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        this.infoWindow = new InfoWindow(width, height, this);

        this.lblHeight = (this.height * 35) / 100;

        this.gm = new ImageIcon(getClass().getResource("/Img/Avatars/1.-General-Manager.png"));
        this.gManager = new JLabel();
        this.gManager.setSize(new Dimension((this.lblHeight * 275) / 400, this.lblHeight));
        this.gManager.setIcon(new ImageIcon(this.gm.getImage().getScaledInstance((this.lblHeight * 275) / 400, this.lblHeight, Image.SCALE_SMOOTH)));
        this.gManager.setLocation((this.width * 5) / 100, this.height - this.lblHeight - (this.height * 30) / 100);

        App.managerLbl = new JLabel();
        App.managerLbl.setSize(new Dimension(94, (this.smallSquareBtnSize + 3)));
        App.managerLbl.setLocation((this.width * 7) / 100, this.height - (this.height * 19) / 100);
        App.managerLbl.setFont(new Font("Tahoma", 1, 18));
        App.managerLbl.setForeground(Color.white);
        App.managerLbl.setHorizontalAlignment(SwingConstants.CENTER);
        //App.managerLbl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        this.wb = new ImageIcon(getClass().getResource("/Img/Avatars/2.-Waiter-Boss.png"));
        this.wBoss = new JLabel();
        this.wBoss.setSize(new Dimension((this.lblHeight * 211) / 499, this.lblHeight));
        this.wBoss.setIcon(new ImageIcon(this.wb.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        this.wBoss.setLocation((this.width * 23) / 100, this.height - this.lblHeight - (this.height * 30) / 100);

        App.wBossLbl = new JLabel();
        App.wBossLbl.setSize(new Dimension(94, (this.smallSquareBtnSize + 3)));
        App.wBossLbl.setLocation((this.width * 23) / 100, this.height - (this.height * 19) / 100);
        App.wBossLbl.setFont(new Font("Tahoma", 1, 18));
        App.wBossLbl.setForeground(Color.white);
        App.wBossLbl.setHorizontalAlignment(SwingConstants.CENTER);

        App.timer = new JLabel();
        App.timer.setSize(new Dimension(210, (this.smallSquareBtnSize + 5)));
        App.timer.setLocation((this.width * 19) / 100, (this.height * 13) / 100);
        App.timer.setFont(new Font("Calibri", 1, 42));
        App.timer.setForeground(new Color(212, 40, 11));
        App.timer.setHorizontalAlignment(SwingConstants.CENTER);

        //App.wBossLbl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        this.w = new ImageIcon(getClass().getResource("/Img/Avatars/3.-Waiter.png"));
        this.waiter = new JLabel();
        this.waiter.setSize(new Dimension((this.lblHeight * 211) / 499, this.lblHeight));
        this.waiter.setIcon(new ImageIcon(this.w.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        this.waiter.setLocation((this.width * 43) / 100, this.height - this.lblHeight - (this.height * 15) / 100);

        App.wLabel = new JLabel();
        App.wLabel.setSize(new Dimension(100, (this.smallSquareBtnSize + 3)));
        App.wLabel.setLocation((this.width * 46) / 100, (this.height * 42) / 100);
        App.wLabel.setFont(lblFont);

        App.wOrdersLabel = new JLabel();
        App.wOrdersLabel.setSize(new Dimension(90, (this.smallSquareBtnSize + 3)));
        App.wOrdersLabel.setLocation((this.width * 44) / 100, (this.height * 27) / 100);
        App.wOrdersLabel.setFont(lblFont);
        App.wOrdersLabel.setForeground(Color.white);
        App.wOrdersLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //App.wOrdersLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        this.ec = new ImageIcon(getClass().getResource("/Img/Avatars/4.-Entries-Chef.png"));
        this.eChef = new JLabel();
        this.eChef.setSize(new Dimension((this.lblHeight * 211) / 499, this.lblHeight));
        this.eChef.setIcon(new ImageIcon(this.ec.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        this.eChef.setLocation((this.width * 61) / 100, this.height - this.lblHeight - (this.height * 15) / 100);

        App.eLabel = new JLabel();
        App.eLabel.setSize(new Dimension(100, (this.smallSquareBtnSize + 3)));
        App.eLabel.setLocation((this.width * 64) / 100, (this.height * 42) / 100);
        App.eLabel.setFont(lblFont);

        App.eFoodLabel = new JLabel();
        App.eFoodLabel.setSize(new Dimension(60, (this.smallSquareBtnSize + 3)));
        App.eFoodLabel.setLocation((this.width * 63) / 100, (this.height * 27) / 100);
        App.eFoodLabel.setFont(lblFont);
        App.eFoodLabel.setForeground(Color.white);
        App.eFoodLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //App.eFoodLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        this.mc = new ImageIcon(getClass().getResource("/Img/Avatars/5.-Main-Chef.png"));
        this.mChef = new JLabel();
        this.mChef.setSize(new Dimension((this.lblHeight * 211) / 499, this.lblHeight));
        this.mChef.setIcon(new ImageIcon(this.mc.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        this.mChef.setLocation((this.width * 74) / 100, this.height - this.lblHeight - (this.height * 15) / 100);

        App.mLabel = new JLabel();
        App.mLabel.setSize(new Dimension((this.smallSquareBtnSize + 3), (this.smallSquareBtnSize + 3)));
        App.mLabel.setLocation((this.width * 77) / 100, (this.height * 42) / 100);
        App.mLabel.setFont(lblFont);

        App.mFoodLabel = new JLabel();
        App.mFoodLabel.setSize(new Dimension(60, (this.smallSquareBtnSize + 3)));
        App.mFoodLabel.setLocation((this.width * 76) / 100, (this.height * 27) / 100);
        App.mFoodLabel.setFont(lblFont);
        App.mFoodLabel.setForeground(Color.white);
        App.mFoodLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //App.mFoodLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        this.dc = new ImageIcon(getClass().getResource("/Img/Avatars/6.-Desserts-Chef.png"));
        this.dChef = new JLabel();
        this.dChef.setSize(new Dimension((this.lblHeight * 211) / 499, this.lblHeight));
        this.dChef.setIcon(new ImageIcon(this.dc.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        this.dChef.setLocation((this.width * 87) / 100, this.height - this.lblHeight - (this.height * 15) / 100);

        App.dLabel = new JLabel();
        App.dLabel.setSize(new Dimension((this.smallSquareBtnSize + 3), (this.smallSquareBtnSize + 3)));
        App.dLabel.setLocation((this.width * 90) / 100, (this.height * 42) / 100);
        App.dLabel.setFont(lblFont);

        App.dFoodLabel = new JLabel();
        App.dFoodLabel.setSize(new Dimension(60, (this.smallSquareBtnSize + 3)));
        App.dFoodLabel.setLocation((this.width * 89) / 100, (this.height * 27) / 100);
        App.dFoodLabel.setFont(lblFont);
        App.dFoodLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //App.dFoodLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        App.dFoodLabel.setForeground(Color.white);

        this.h1 = new HireBtn((this.smallSquareBtnSize + 10), ((this.smallSquareBtnSize + 10) * 132) / 202, (this.width * 66) / 100, this.height - (this.height * 12) / 100);
        this.h1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hire1ActionPerformed();
            }
        });
        this.h2 = new HireBtn((this.smallSquareBtnSize + 10), ((this.smallSquareBtnSize + 10) * 132) / 202, (this.width * 79) / 100, this.height - (this.height * 12) / 100);
        this.h2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hire2ActionPerformed();
            }
        });

        this.h3 = new HireBtn((this.smallSquareBtnSize + 10), ((this.smallSquareBtnSize + 10) * 132) / 202, (this.width * 92) / 100, this.height - (this.height * 12) / 100);
        this.h3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hire3ActionPerformed();
            }
        });

        this.h4 = new HireBtn((this.smallSquareBtnSize + 10), ((this.smallSquareBtnSize + 10) * 132) / 202, (this.width * 48) / 100, this.height - (this.height * 12) / 100);
        this.h4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hire4ActionPerformed();
            }
        });

        this.f1 = new FireBtn((this.smallSquareBtnSize + 10), ((this.smallSquareBtnSize + 10) * 132) / 202, (this.width * 61) / 100, this.height - (this.height * 12) / 100);
        this.f1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fire1ActionPerformed();
            }
        });

        this.f2 = new FireBtn((this.smallSquareBtnSize + 10), ((this.smallSquareBtnSize + 10) * 132) / 202, (this.width * 74) / 100, this.height - (this.height * 12) / 100);
        this.f2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fire2ActionPerformed();
            }
        });

        this.f3 = new FireBtn((this.smallSquareBtnSize + 10), ((this.smallSquareBtnSize + 10) * 132) / 202, (this.width * 87) / 100, this.height - (this.height * 12) / 100);
        this.f3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fire3ActionPerformed();
            }
        });

        this.f4 = new FireBtn((this.smallSquareBtnSize + 10), ((this.smallSquareBtnSize + 10) * 132) / 202, (this.width * 43) / 100, this.height - (this.height * 12) / 100);
        this.f4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fire4ActionPerformed();
            }
        });

        this.add(this.background);
        this.background.add(this.hideBtn);
        this.background.add(this.exitBtn);
        this.background.add(this.infoBtn);
        this.background.add(this.gManager);
        this.background.add(this.wBoss);
        this.background.add(this.waiter);
        this.background.add(this.mChef);
        this.background.add(this.eChef);
        this.background.add(this.dChef);
        this.background.add(this.h1);
        this.background.add(this.h2);
        this.background.add(this.h3);
        this.background.add(this.h4);
        this.background.add(this.f1);
        this.background.add(this.f2);
        this.background.add(this.f3);
        this.background.add(this.f4);
        this.background.add(App.wLabel);
        this.background.add(App.eLabel);
        this.background.add(App.mLabel);
        this.background.add(App.dLabel);
        this.background.add(App.wOrdersLabel);
        this.background.add(App.eFoodLabel);
        this.background.add(App.mFoodLabel);
        this.background.add(App.dFoodLabel);
        this.background.add(App.wBossLbl);
        this.background.add(App.managerLbl);
        this.background.add(App.timer);

    }

    public void setEntryChef(boolean enabled) {
        if (enabled == false) {
            ImageIcon hoverIcon = new ImageIcon(getClass().getResource("/Img/Avatars/4.-Entries-Chef-Disabled.png"));
            this.eChef.setIcon(new ImageIcon(hoverIcon.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        } else {
            this.eChef.setIcon(new ImageIcon(this.ec.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        }
        this.f1.setEnabled(enabled);
    }

    public void setMainChef(boolean enabled) {
        if (enabled == false) {
            ImageIcon hoverIcon = new ImageIcon(getClass().getResource("/Img/Avatars/5.-Main-Chef-Disabled.png"));
            this.mChef.setIcon(new ImageIcon(hoverIcon.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        } else {
            this.mChef.setIcon(new ImageIcon(this.mc.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        }
        this.f2.setEnabled(enabled);
    }

    public void setDessertChef(boolean enabled) {
        if (enabled == false) {
            ImageIcon hoverIcon = new ImageIcon(getClass().getResource("/Img/Avatars/6.-Desserts-Chef.-Disabledpng.png"));
            this.dChef.setIcon(new ImageIcon(hoverIcon.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        } else {
            this.dChef.setIcon(new ImageIcon(this.dc.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        }
        this.f3.setEnabled(enabled);
    }

    public void setWaiter(boolean enabled) {
        if (enabled == false) {
            ImageIcon hoverIcon = new ImageIcon(getClass().getResource("/Img/Avatars/3.-Waiter-Disabled.png"));
            this.waiter.setIcon(new ImageIcon(hoverIcon.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        } else {
            this.waiter.setIcon(new ImageIcon(this.w.getImage().getScaledInstance((this.lblHeight * 211) / 499, this.lblHeight, Image.SCALE_SMOOTH)));
        }
        this.f4.setEnabled(enabled);
    }

    private void hideBtnActionPerformed() {
        this.setState(JFrame.ICONIFIED);
    }

    private void infoBtnActionPerformed() {
        this.infoWindow.setVisible(true);
        this.infoWindow.setEnabled(true);

        this.setVisible(false);
        this.setEnabled(false);
    }

    private void hire1ActionPerformed() {
        this.restaurant.hireEntryChef();
        if (this.restaurant.getEntryChefCounter() == this.eProdLimit) {
            this.h1.setEnabledIcon(false);
        }
        if (!this.f1.isEnabledBtn()) {
            this.f1.setEnabledIcon(true);
        }
    }

    private void hire2ActionPerformed() {
        this.restaurant.hireMainChef();
        if (this.restaurant.getMainChefCounter() == this.mProdLimit) {
            this.h2.setEnabledIcon(false);
        }
        if (!this.f2.isEnabledBtn()) {
            this.f2.setEnabledIcon(true);
        }
    }

    private void hire3ActionPerformed() {
        this.restaurant.hireDessertChef();
        if (this.restaurant.getDessertChefCounter() == this.dProdLimit) {
            this.h3.setEnabledIcon(false);
        }
        if (!this.f3.isEnabledBtn()) {
            this.f3.setEnabledIcon(true);
        }
    }

    private void hire4ActionPerformed() {
        this.restaurant.hireWaiter();
        if (this.restaurant.getWaiterCounter() == this.waitersLimit) {
            this.h4.setEnabledIcon(false);
        }
        if (!this.f4.isEnabledBtn()) {
            this.f4.setEnabledIcon(true);
        }
    }

    private void fire1ActionPerformed() {
        this.restaurant.fireEntryChef();
        if (!this.h1.isEnabledBtn()) {
            this.h1.setEnabledIcon(true);
        }
        if (this.restaurant.getEntryChefCounter() == 0) {
            this.f1.setEnabledIcon(false);
        }
    }

    private void fire2ActionPerformed() {
        this.restaurant.fireMainChef();
        if (!this.h2.isEnabledBtn()) {
            this.h2.setEnabledIcon(true);
        }
        if (this.restaurant.getMainChefCounter() == 0) {
            this.f2.setEnabledIcon(false);
        }
    }

    private void fire3ActionPerformed() {
        this.restaurant.fireDesChef();
        if (!this.h3.isEnabledBtn()) {
            this.h3.setEnabledIcon(true);
        }
        if (this.restaurant.getDessertChefCounter() == 0) {
            this.f3.setEnabledIcon(false);
        }
    }

    private void fire4ActionPerformed() {
        this.restaurant.fireWaiter();
        if (!this.h4.isEnabledBtn()) {
            this.h4.setEnabledIcon(true);
        }
        if (this.restaurant.getWaiterCounter() == 0) {
            this.f4.setEnabledIcon(false);
        }
    }

    public void start(int workTime, int timeForClosing, int eProdLimit, int mProdLimit, int dProdLimit, int waitersLimit, int eProdInit, int mProdInit, int dProdInit, int waiterInit, int eMesLimit, int mMesLimit, int dMesLimit) {

        this.eProdLimit = eProdLimit;
        this.mProdLimit = mProdLimit;
        this.dProdLimit = dProdLimit;
        this.waitersLimit = waitersLimit;
        this.eProdInit = eProdInit;
        this.mProdInit = mProdInit;
        this.dProdInit = dProdInit;
        this.waiterInit = waiterInit;

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
        this.restaurant = new Restaurant(workTime, timeForClosing, eProdLimit, mProdLimit, dProdLimit, waitersLimit, eProdInit, mProdInit, dProdInit, waiterInit, eMesLimit, mMesLimit, dMesLimit, this);
        WindowUpdater windowUpdater = new WindowUpdater(this.restaurant, this.restaurant.getWriter(), this.restaurant.getReader());
        windowUpdater.start();
    }

    public HireBtn getH1() {
        return h1;
    }

    public HireBtn getH2() {
        return h2;
    }

    public HireBtn getH3() {
        return h3;
    }

    public HireBtn getH4() {
        return h4;
    }

    public void playEg() {
        
    }
}
