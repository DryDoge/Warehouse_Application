package app;


import gui.main.GuiMain;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class MyThread extends Thread {
    private Logger logr = GuiMain.getLogr();
    private JLabel lab;

    public MyThread(JLabel label){
        this.lab = label;
    }

    /**
     * Set actual time to Jlabel.
     */
    public void run() {
        logr.info("Running thread");
        String time = getActualTime();
        lab.setText(time);
        try {
            while (true){
                time = getActualTime();
                lab.setText(time);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            logr.warning("Interrupted thread");
        }
        logr.info("Stopping thread");
    }

    /**
     * Gets actual time and converts it in string.
     *
     * @return Date in format: yyyy/MM/dd HH:mm:ss
     */
    private String getActualTime(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
