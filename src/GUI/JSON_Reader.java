/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.applet.AudioClip;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Marcos
 */
public class JSON_Reader {

    private String file = "/Audio/Bg_Music.wav";

    public JSON_Reader(int App_WIDTH, int App_HEIGHT, JFrame App_PARENT) {
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("config.json"));

            JSONObject jsonObject = (JSONObject) obj;

            int workTime = Integer.parseInt((String) jsonObject.get("workTime"));
            int timeForClosing = Integer.parseInt((String) jsonObject.get("timeForClosing"));
            int eProdLimit = Integer.parseInt((String) jsonObject.get("eProdLimit"));
            int mProdLimit = Integer.parseInt((String) jsonObject.get("mProdLimit"));
            int dProdLimit = Integer.parseInt((String) jsonObject.get("dProdLimit"));
            int waitersLimit = Integer.parseInt((String) jsonObject.get("waitersLimit"));
            int eProdInit = Integer.parseInt((String) jsonObject.get("eProdInit"));
            int mProdInit = Integer.parseInt((String) jsonObject.get("mProdInit"));
            int dProdInit = Integer.parseInt((String) jsonObject.get("dProdInit"));
            int waiterInit = Integer.parseInt((String) jsonObject.get("waiterInit"));
            int eMesLimit = Integer.parseInt((String) jsonObject.get("eMesLimit"));
            int mMesLimit = Integer.parseInt((String) jsonObject.get("mMesLimit"));
            int dMesLimit = Integer.parseInt((String) jsonObject.get("dMesLimit"));

            if (workTime <= 0
                    || timeForClosing <= 0
                    || eProdLimit <= 0
                    || mProdLimit <= 0
                    || dProdLimit <= 1
                    || waitersLimit < 0
                    || eProdInit < 0
                    || dProdInit < 0
                    || dProdInit < 0
                    || waiterInit <= 0
                    || eMesLimit <= 0
                    || mMesLimit < 0
                    || dMesLimit <= 0) {
                throw new Exception("Hay una o más propiedades en config.json que no pueden ser menores o iguales a 0.");
            }

            if (eProdInit > eProdLimit || mProdInit > mProdInit || dProdInit > dProdLimit || waiterInit > waitersLimit) {
                throw new Exception("Una cantidad inicial no puede ser mayor que una cantidad máxima");
            }
            
            if (workTime <= 5) {
                file = "/Audio/EG_Music.wav";
            }
            
            AudioClip sonido = java.applet.Applet.newAudioClip(getClass().getResource(file));
            sonido.loop();
            
            App app = new App(App_WIDTH, App_HEIGHT, App_PARENT);
            app.start(workTime, timeForClosing, eProdLimit, mProdLimit, dProdLimit, waitersLimit, eProdInit, mProdInit, dProdInit, waiterInit, eMesLimit, mMesLimit, dMesLimit);
            app.setVisible(true);
            app.setEnabled(true);
        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                JOptionPane.showMessageDialog(null, "Archivo config.json no encontrado.");
            } else if (e instanceof NumberFormatException) {
                JOptionPane.showMessageDialog(null, "El config.json tiene una propiedad que no es un int o está vacía.");
            } else {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
}
