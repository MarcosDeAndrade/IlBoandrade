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
 * @author Samuel
 */
public class InsWindow extends JFrame {

    private final int width, height;
    private final int btnPercent, marginPercent;
    private final int btnSizeW, btnSizeH;
    private final int smallSquareBtnSize, marginX, marginY;

    private final JFrame parent;
    private final ImageIcon bg, bc, ex, info, st;
    private final JButton backBtn;
    private final JButton infoBtn;
    private final JButton exitBtn;
    private final JButton startBtn;
    private final Cursor pointer;
    private final JLabel background;

    private final InfoWindow infoWindow;

    public InsWindow(final int width, final int height, final JFrame parent) {
        this.width = width;
        this.height = height;
        this.parent = parent;

        this.setSize(new Dimension(width, height));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        this.bg = new ImageIcon(getClass().getResource("/Img/Background/2. Instructions.png"));
        this.background = new JLabel();
        this.background.setSize(new Dimension(width, height));
        this.background.setIcon(new ImageIcon(this.bg.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));

        this.pointer = new Cursor(Cursor.HAND_CURSOR);

        this.btnPercent = 2;
        this.marginPercent = 7;
        this.smallSquareBtnSize = (this.width * this.btnPercent) / 100;
        this.btnSizeH = this.smallSquareBtnSize + 20;
        this.btnSizeW = (this.btnSizeH * 386) / 144;
        this.marginX = (this.width * (this.marginPercent - 5)) / 100;
        this.marginY = (this.height * this.marginPercent) / 100;

        this.st = new ImageIcon(getClass().getResource("/Img/Buttons/19. Start-2 (Enable).png "));
        this.startBtn = new JButton();
        this.startBtn.setSize(new Dimension(this.btnSizeW - ((this.btnSizeW * 35) / 100), this.btnSizeH));
        this.startBtn.setIcon(new ImageIcon(this.st.getImage().getScaledInstance(this.btnSizeW - ((this.btnSizeW * 35) / 100), this.btnSizeH, Image.SCALE_SMOOTH)));
        this.startBtn.setLocation(this.width - this.btnSizeW - this.marginX, this.height - this.btnSizeH - this.marginY + 12);
        this.startBtn.setContentAreaFilled(false);
        this.startBtn.setFocusPainted(false);
        this.startBtn.setCursor(pointer);
        this.startBtn.setBorder(null);
        this.startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                startBtnActionPerformed();
            }
        });

        this.bc = new ImageIcon(getClass().getResource("/Img/Buttons/17. Back (Black).png"));
        this.backBtn = new JButton();
        this.backBtn.setSize(new Dimension(this.smallSquareBtnSize, this.smallSquareBtnSize));
        this.backBtn.setIcon(new ImageIcon(this.bc.getImage().getScaledInstance(this.smallSquareBtnSize, this.smallSquareBtnSize, Image.SCALE_SMOOTH)));
        this.backBtn.setLocation(this.width - this.smallSquareBtnSize - this.btnSizeW - this.marginX - 8, this.height - this.smallSquareBtnSize - this.marginY);
        this.backBtn.setCursor(pointer);
        this.backBtn.setContentAreaFilled(false);
        this.backBtn.setBorder(null);
        this.backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                backBtnActionPerformed();
            }
        });

        this.ex = new ImageIcon(getClass().getResource("/Img/Buttons/2. Close (Light Grey).png"));
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

        this.infoWindow = new InfoWindow(width, height, this);

        this.add(this.background);
        this.background.add(this.backBtn);
        this.background.add(this.exitBtn);
        this.background.add(this.infoBtn);
        this.background.add(this.startBtn);
    }

    private void backBtnActionPerformed() {
        this.setVisible(false);
        this.setEnabled(false);
        this.parent.setEnabled(true);
        this.parent.setVisible(true);
    }

    private void infoBtnActionPerformed() {
        this.infoWindow.setVisible(true);
        this.infoWindow.setEnabled(true);

        this.setVisible(false);
        this.setEnabled(false);
    }

    private void startBtnActionPerformed() {
        JSON_Reader json = new JSON_Reader(width, height, this);
        /*if (json.getWorkTime() < 5) {
            AudioClip sonido = java.applet.Applet.newAudioClip(getClass().getResource("/Audio/EG_Music.wav"));
            sonido.play();
        }*/
        this.setVisible(false);
        this.setEnabled(false);
    }

}
