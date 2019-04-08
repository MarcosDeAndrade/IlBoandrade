/*
 ------------------------------------------------------------------------------
 |       Metropolitan University, Caracas - Venezuela                         |
 |       Systems Engineering                                                  |
 |       Operating Systems                                                    |
 |       * Professor:   Luis García                                           |
 |       * Creators:    Samuel Boada, Marcos De Andrade                       |
 |       Proyect  N°1 - Multithreading                                        |
 |       Period  1819-2 (febrero 2019)                                        |
 ------------------------------------------------------------------------------
 */
package GUI;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class menu extends javax.swing.JFrame {

    private final int screenSizeW;
    private final int screenSizeH;
    private final int btnSizeW;
    private final int btnSizeH;
    private final int smallSquareBtnSize;
    private final int percent = 25, smallBtnPercent;
    private final int btnMarginX;
    private final int btnMarginY;

    private final ImageIcon bg, ex, hi, cr, info, ins, st;
    private ImageIcon hoverIcon;
    private final JButton exitBtn;
    private final JButton hideBtn;
    private final JButton startBtn;
    private final JButton insBtn;
    private final JButton infoBtn;
    private final JButton credBtn;
    private final JLabel background;
    private final Cursor pointer;

    // Windows
    private final InsWindow insWindow;
    private final InfoWindow infoWindow;

    public menu() {
        initComponents();

        this.screenSizeW = Toolkit.getDefaultToolkit().getScreenSize().width - (Toolkit.getDefaultToolkit().getScreenSize().width * this.percent) / 100;
        this.screenSizeH = (this.screenSizeW * 920) / 1920;
        this.setState(JFrame.NORMAL);
        this.setSize(dimension(0, 0));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        System.out.println("Res: " + this.screenSizeW + "x" + this.screenSizeH);

        //  Setting Background Label        
        this.bg = new ImageIcon(getClass().getResource("/Img/Background/1. Menu.png"));
        this.background = new JLabel();
        this.background.setSize(dimension(0, 0));
        this.background.setIcon(new ImageIcon(this.bg.getImage().getScaledInstance(this.screenSizeW, this.screenSizeH, Image.SCALE_SMOOTH)));
        this.background.setLocation(0, 0);

        //  Setting Buttons
        this.pointer = new Cursor(Cursor.HAND_CURSOR);

        this.btnSizeW = (this.screenSizeW * 22) / 100;
        this.btnSizeH = (this.btnSizeW * 144) / 386;
        this.btnMarginX = this.screenSizeW - ((this.screenSizeW * 34) / 100);
        this.btnMarginY = this.screenSizeH * 30 / 100;
        this.smallBtnPercent = 2;
        this.smallSquareBtnSize = (this.screenSizeW * this.smallBtnPercent) / 100;
        System.out.println("Btn Res: " + this.btnSizeW + "x" + this.btnSizeH);

        this.ex = new ImageIcon(getClass().getResource("/Img/Buttons/2. Close (Light Grey).png"));
        this.exitBtn = new JButton();
        this.exitBtn.setSize(new Dimension(this.smallSquareBtnSize, this.smallSquareBtnSize));
        this.exitBtn.setIcon(new ImageIcon(this.ex.getImage().getScaledInstance(this.smallSquareBtnSize, this.smallSquareBtnSize, Image.SCALE_SMOOTH)));
        this.exitBtn.setLocation(this.screenSizeW - this.smallSquareBtnSize - 10, 10);
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

        this.hi = new ImageIcon(getClass().getResource("/Img/Buttons/4. Minimize (Light Grey).png"));
        this.hideBtn = new JButton();
        this.hideBtn.setSize(new Dimension(this.smallSquareBtnSize, this.smallSquareBtnSize));
        this.hideBtn.setIcon(new ImageIcon(this.hi.getImage().getScaledInstance(this.smallSquareBtnSize, this.smallSquareBtnSize, Image.SCALE_SMOOTH)));
        this.hideBtn.setLocation(this.screenSizeW - (this.smallSquareBtnSize * 2) - 20, 10);
        this.hideBtn.setContentAreaFilled(false);
        this.hideBtn.setCursor(pointer);
        this.hideBtn.setBorder(null);
        this.hideBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hideBtnActionPerformed();
            }
        });

        this.st = new ImageIcon(getClass().getResource("/Img/Buttons/12. Start (Disable).png"));
        this.startBtn = new JButton();
        this.startBtn.setSize(new Dimension(this.btnSizeW, this.btnSizeH));
        this.startBtn.setIcon(new ImageIcon(this.st.getImage().getScaledInstance(this.btnSizeW, this.btnSizeH, Image.SCALE_SMOOTH)));
        this.startBtn.setLocation(this.btnMarginX, this.btnMarginY);
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
        
        this.startBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evtMouseEvent) {
                changeStartBtnBackground(true);
            }

            @Override
            public void mouseExited(MouseEvent evtMouseEvent) {
                changeStartBtnBackground(false);
            }
        });

        this.ins = new ImageIcon(getClass().getResource("/Img/Buttons/14. Instructions (Disable).png"));
        this.insBtn = new JButton();
        this.insBtn.setSize(new Dimension(this.btnSizeW, this.btnSizeH));
        this.insBtn.setIcon(new ImageIcon(this.ins.getImage().getScaledInstance(this.btnSizeW, this.btnSizeH, Image.SCALE_SMOOTH)));
        this.insBtn.setLocation(this.btnMarginX, this.btnMarginY + this.btnSizeH + ((this.screenSizeH * 2) / 100));
        this.insBtn.setContentAreaFilled(false);
        this.insBtn.setFocusPainted(false);
        this.insBtn.setCursor(pointer);
        this.insBtn.setBorder(null);
        this.insBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                insBtnActionPerformed();
            }
        });
        this.insBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evtMouseEvent) {
                changeInstructionsBackground(true);
            }

            @Override
            public void mouseExited(MouseEvent evtMouseEvent) {
                changeInstructionsBackground(false);
            }
        });

        this.cr = new ImageIcon(getClass().getResource("/Img/Buttons/16. Instructions (Disable).png"));
        this.credBtn = new JButton();
        this.credBtn.setSize(new Dimension(this.btnSizeW, this.btnSizeH));
        this.credBtn.setIcon(new ImageIcon(this.cr.getImage().getScaledInstance(this.btnSizeW, this.btnSizeH, Image.SCALE_SMOOTH)));
        this.credBtn.setLocation(this.btnMarginX, this.btnMarginY + (this.btnSizeH * 2) + ((this.screenSizeH * 4) / 100));
        this.credBtn.setContentAreaFilled(false);
        this.credBtn.setFocusPainted(false);
        this.credBtn.setCursor(pointer);
        this.credBtn.setBorder(null);
        this.credBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                credBtnActionPerformed();
            }
        });
        this.credBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evtMouseEvent) {
                changeCreditsBackground(true);
            }
            
            @Override
            public void mouseExited(MouseEvent evtMouseEvent) {
                changeCreditsBackground(false);
            }
        });

        this.info = new ImageIcon(getClass().getResource("/Img/Buttons/5. Info (Black).png"));
        this.infoBtn = new JButton();
        this.infoBtn.setSize(new Dimension(this.smallSquareBtnSize, this.smallSquareBtnSize));
        this.infoBtn.setIcon(new ImageIcon(this.info.getImage().getScaledInstance(this.smallSquareBtnSize, this.smallSquareBtnSize, Image.SCALE_SMOOTH)));
        this.infoBtn.setLocation(10, this.screenSizeH - this.smallSquareBtnSize - 10);
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

        //  Adding Elements
        this.setLayout(null);
        this.add(this.background);
        this.background.add(this.exitBtn);
        this.background.add(this.startBtn);
        this.background.add(this.insBtn);
        this.background.add(this.credBtn);
        this.background.add(this.hideBtn);
        this.background.add(this.infoBtn);

        // Setting Windows
        this.insWindow = new InsWindow(this.screenSizeW, this.screenSizeH, this);
        this.infoWindow = new InfoWindow(this.screenSizeW, this.screenSizeH, this);
    }

    private Dimension dimension(int x, int y) {
        return new Dimension(this.screenSizeW + x, this.screenSizeH + y);
    }

    private void changeStartBtnBackground(boolean entered) {
        if (entered) {
            this.hoverIcon = new ImageIcon(getClass().getResource("/Img/Buttons/11. Start (Enable).png"));
            this.startBtn.setIcon(new ImageIcon(this.hoverIcon.getImage().getScaledInstance(this.btnSizeW, this.btnSizeH, Image.SCALE_SMOOTH)));
        } else {
            this.startBtn.setIcon(new ImageIcon(this.st.getImage().getScaledInstance(this.btnSizeW, this.btnSizeH, Image.SCALE_SMOOTH)));
        }
    }

    private void changeInstructionsBackground(boolean entered) {
        if (entered) {
            this.hoverIcon = new ImageIcon(getClass().getResource("/Img/Buttons/13. Instructions (Enable).png"));
            this.insBtn.setIcon(new ImageIcon(this.hoverIcon.getImage().getScaledInstance(this.btnSizeW, this.btnSizeH, Image.SCALE_SMOOTH)));
        } else {
            this.insBtn.setIcon(new ImageIcon(this.ins.getImage().getScaledInstance(this.btnSizeW, this.btnSizeH, Image.SCALE_SMOOTH)));
        }
    }

    private void changeCreditsBackground(boolean entered) {
        if (entered) {
            this.hoverIcon = new ImageIcon(getClass().getResource("/Img/Buttons/15. Credits (Enable).png"));
            this.credBtn.setIcon(new ImageIcon(this.hoverIcon.getImage().getScaledInstance(this.btnSizeW, this.btnSizeH, Image.SCALE_SMOOTH)));
        } else {
            this.credBtn.setIcon(new ImageIcon(this.cr.getImage().getScaledInstance(this.btnSizeW, this.btnSizeH, Image.SCALE_SMOOTH)));
        }
    }

    private void startBtnActionPerformed() {
        this.insWindow.setVisible(true);
        this.insWindow.setEnabled(true);

        this.setVisible(false);
        this.setEnabled(false);
    }

    private void credBtnActionPerformed() {
        this.infoWindow.setVisible(true);
        this.infoWindow.setEnabled(true);

        this.setVisible(false);
        this.setEnabled(false);
    }

    private void insBtnActionPerformed() {
        this.insWindow.setVisible(true);
        this.insWindow.setEnabled(true);

        this.setVisible(false);
        this.setEnabled(false);
    }

    private void infoBtnActionPerformed() {
        this.infoWindow.setVisible(true);
        this.infoWindow.setEnabled(true);

        this.setVisible(false);
        this.setEnabled(false);
    }

    private void hideBtnActionPerformed() {
        this.setState(JFrame.ICONIFIED);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 338, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
