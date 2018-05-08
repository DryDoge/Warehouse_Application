package gui.warehouses;

import db.dao.daoBasics;
import db.e.Sklad;
import db.dao.daoSklad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;



public class NewWarehouse extends JFrame{
    private JPanel newPanel;
    private JTextField telephoneTextfield;
    private JTextField webPageTextField;
    private JTextField streetTextField;
    private JTextField cityTextField;
    private JTextField postalCodeTextField;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel optionPanel;
    private JLabel cityLabel;
    private JLabel telLabel;
    private JLabel webLabel;
    private JLabel postalCodeLabel;
    private JLabel infoTelLabel;
    private JLabel infoStreetLabel;
    private JLabel streetLabel;
    private JLabel infoCityLabel;
    private JLabel infoPCLabel;
    private JLabel infoWebLabel;
    private static int new_id = 1;

    public NewWarehouse(){
        super("New warehouse");
        setContentPane(newPanel);
        pack();
        int optionButton = getDefaultCloseOperation();
        if(optionButton == WindowConstants.EXIT_ON_CLOSE){
            setVisible(false);
        }
        setSize(700,450);
        setLocationRelativeTo(null);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addData()) {
                    JOptionPane.showMessageDialog(null,
                            "Succesfully added warehouse No. "+new_id);
                    dispose();
                }else
                    JOptionPane.showMessageDialog(null,"Error while adding. Try again");


            }
        });

        webPageTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    saveButton.doClick();
                }
            }
        });

        telephoneTextfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    saveButton.doClick();
                }
            }
        });

        streetTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    saveButton.doClick();
                }
            }
        });

        cityTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    saveButton.doClick();
                }
            }
        });

        postalCodeTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    saveButton.doClick();
                }
            }
        });
    }



    private boolean addData(){
        boolean ret = false;
        List<Integer> ids = new ArrayList<>();
        String c = cityTextField.getText();
        String s = streetTextField.getText();
        String w = webPageTextField.getText();
        String t = telephoneTextfield.getText();
        String p = postalCodeTextField.getText();

        if(c.equals("")){
            JOptionPane.showConfirmDialog(
                    null,"City is not filled",
                    "Warning",JOptionPane.DEFAULT_OPTION);
        return ret;
        }

        if(s.equals("")){
            JOptionPane.showConfirmDialog(
                    null,"Street is not filled",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }


        if(w.equals("") || !(w.matches(
                "^(https?://)?(www\\.)?([\\w]+\\.)+[\u200C\u200B\\w]{2,63}/?$"))
                ){
            JOptionPane.showConfirmDialog(
                    null,"Web page is not filled or wrong",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }

        if((t.length()!= 9) || !(t.matches("[0-9]+"))){
            JOptionPane.showConfirmDialog(
                    null,"Telephone number is not filled or wrong",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }

        if((p.length()!= 5) || !(p.matches("[0-9]+"))){
            JOptionPane.showConfirmDialog(
                    null,"Postal code is not filled or wrong",
                    "Warning",JOptionPane.DEFAULT_OPTION);
            return ret;
        }


        List<Sklad> l = new daoSklad().getAllWarehouses();

        for (Sklad  st: l) {
            ids.add(st.getIdsklad());
        }

        while (ids.contains(new_id)){
            new_id++;
        }

        Sklad storage = new Sklad();
        storage.setIdsklad(new_id);
        storage.setTel(t);
        storage.setMesto(c);
        storage.setUlica(s);
        storage.setPsc(p);
        storage.setWeb(w);
        new daoBasics().addAnything(storage);
        ret = true;

        return ret;


    }
}
