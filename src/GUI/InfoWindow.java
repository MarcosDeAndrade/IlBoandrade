/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class InfoWindow extends JFrame {

    private final int width;
    private final int height;
    private final int btnPercent, marginPercent;
    private final int btnSizeW, btnSizeH;
    private final int smallSquareBtnSize, marginX, marginY;

    private final JFrame parent;
    private final ImageIcon bg, bc, ex;
    private final JButton backBtn;
    private final JButton exitBtn;
    private final Cursor pointer;
    private final JLabel background;

    public InfoWindow(final int width, final int height, final JFrame parent) {

        this.width = width;
        this.height = height;
        this.parent = parent;

        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        this.bg = new ImageIcon(getClass().getResource("/Img/Background/3. Credits.png"));
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

        this.bc = new ImageIcon(getClass().getResource("/Img/Buttons/17. Back (Black).png"));
        this.backBtn = new JButton();
        this.backBtn.setSize(new Dimension(this.smallSquareBtnSize, this.smallSquareBtnSize));
        this.backBtn.setIcon(new ImageIcon(this.bc.getImage().getScaledInstance(this.smallSquareBtnSize, this.smallSquareBtnSize, Image.SCALE_SMOOTH)));
        this.backBtn.setLocation(this.width - (this.smallSquareBtnSize*2) - 20, 10);
        this.backBtn.setCursor(pointer);
        this.backBtn.setContentAreaFilled(false);
        this.backBtn.setBorder(null);
        this.backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                backBtnActionPerformed();
            }
        });

        this.ex = new ImageIcon(getClass().getResource("/Img/Buttons/1. Close (Black).png"));
        this.exitBtn = new JButton();
        this.exitBtn.setSize(new Dimension(this.smallSquareBtnSize, this.smallSquareBtnSize));
        this.exitBtn.setIcon(new ImageIcon(this.ex.getImage().getScaledInstance(this.smallSquareBtnSize, this.smallSquareBtnSize, Image.SCALE_SMOOTH)));
        this.exitBtn.setLocation(this.width - this.smallSquareBtnSize - 10, 10);
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


        this.add(this.background);
        this.background.add(this.backBtn);
        this.background.add(this.exitBtn);
    }

    private void backBtnActionPerformed() {
        this.setVisible(false);
        this.setEnabled(false);
        this.parent.setEnabled(true);
        this.parent.setVisible(true);
    }

}
