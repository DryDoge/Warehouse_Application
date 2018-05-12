package gui.warehouses;

import db.dao.daoSklad;
import db.e.Sklad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UpdateWarehouse extends JFrame {
    private JPanel updatePanel;
    private JPanel optionPanel;

    private JTextField telTextField;
    private JTextField webTextField;
    private JTextField pcTextField;
    private JTextField streetTextField;
    private JTextField cityTextField;

    private JButton saveButton;
    private JButton cancelButton;
    private JLabel webLabel;
    private JLabel postalCodeLabel;
    private JLabel streetLabel;
    private JLabel cityLabel;
    private JLabel infoTelLabel;
    private JLabel infoPCLabel;
    private JLabel telLabel;
    private int id;


    UpdateWarehouse(Sklad w){
        super("Update Warehouse No. "+w.getIdsklad());
        setContentPane(updatePanel);
        int optionButton = getDefaultCloseOperation();
        if(optionButton == WindowConstants.EXIT_ON_CLOSE){
            setVisible(false);
        }
        pack();
        setLocationRelativeTo(null);
        this.id = w.getIdsklad();
        String tel = w.getTel();
        String street = w.getUlica();
        String city = w.getMesto();
        String postalCode = w.getPsc();
        String web = w.getWeb();

        telTextField.setText(tel);
        streetTextField.setText(street);
        cityTextField.setText(city);
        pcTextField.setText(postalCode);
        webTextField.setText(web);


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int optionButton = JOptionPane.showConfirmDialog(null,
                        "Do you really want to update this warehouse ?",
                        "Update confirmation",JOptionPane.YES_NO_OPTION);
                if (optionButton == JOptionPane.YES_OPTION){
                    if(updateData(id)){
                        JOptionPane.showMessageDialog(
                                null,
                                "Succesfully updated warehouse No. "+ id
                        );
                        dispose();
                    }

                }
            }
        });

        webTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    saveButton.doClick();
                }
            }
        });

        telTextField.addKeyListener(new KeyAdapter() {
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

        streetTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    saveButton.doClick();
                }
            }
        });

        pcTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    saveButton.doClick();
                }
            }
        });
    }
    private boolean updateData(int idWarehouse){
        boolean ret = false;
        String c = cityTextField.getText();
        String s = streetTextField.getText();
        String w = webTextField.getText();
        String t = telTextField.getText();
        String p = pcTextField.getText();

        if(!(WarehouseGUI.areValidData(c,s,w,t,p)))
            return ret;

        Sklad storage = new daoSklad().getWarehouseById(idWarehouse);

        storage.setTel(t);
        storage.setUlica(s);
        storage.setMesto(c);
        storage.setPsc(p);
        storage.setWeb(w);

        new daoSklad().updateWarehouse(storage);

        ret = true;

        return ret;
    }


}