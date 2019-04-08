/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Buttons;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Marcos
 */
public class HireBtn extends JButton {

    private final ImageIcon enabledIcon, disabledIcon;
    private final int width, height;
    private final Cursor pointer;
    private boolean enabled;

    public HireBtn(final int width, final int height, final int posX, final int posY) {
        this.width = width;
        this.height = height;

        this.enabledIcon = new ImageIcon(getClass().getResource("/Img/Buttons/7. Hire (Enable).png"));
        this.disabledIcon = new ImageIcon(getClass().getResource("/Img/Buttons/8. Hire (Disable).png"));

        this.setSize(new Dimension(width, height));
        this.setLocation(posX, posY);
        this.setIcon(new ImageIcon(this.enabledIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        this.pointer = new Cursor(Cursor.HAND_CURSOR);
        this.setCursor(pointer);
    }

    public void setEnabledIcon(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            this.setIcon(new ImageIcon(this.enabledIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        } else {
            this.setIcon(new ImageIcon(this.disabledIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        }
    }

    public boolean isEnabledBtn() {
        return this.enabled;
    }

}
