package gui.WarehouseGui;

import db.e.*;
import db.dao.*;


import java.awt.event.*;

import java.util.Comparator;
import java.util.List;
import javax.persistence.*;
import javax.swing.*;

public class WarehouseGUI extends JFrame {
    private JPanel whPanel;
    private JComboBox<String> warehousesCB;
    private JLabel telLabel;
    private JLabel streetLabel;
    private JLabel cityLabel;
    private JLabel pscLabel;
    private JLabel webLabel;
    private JLabel imageLabel;
    private JButton newButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JList productsList;
    private JList employersList;
    private JPanel descriptionPanel;
    private JLabel infoNumberLabel;
    private JLabel infoWebLabel;
    private JLabel infoStreetLabel;
    private JLabel infoCityLabel;
    private JLabel infoTelLabel;
    private JLabel infoPCLabel;
    private JPanel actionsPanel;
    private JPanel containsPanel;
    private JPanel employersPanel;
    private JPanel descPanel;

    static private NewWarehouse nw = null;
    static private UpdateWarehouse uw = null;

    public WarehouseGUI() {
        super("Warehouse");
        setContentPane(whPanel);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        pack();
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ImageIcon backgroundIcon = new ImageIcon("icons/warehouse-back.jpg", "Background");

        telLabel.setText("");
        streetLabel.setText("");
        cityLabel.setText("");
        pscLabel.setText("");
        webLabel.setText("");
        refreshButton.doClick();
        imageLabel.setIcon(backgroundIcon);

        warehousesCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int chosenId = Integer.valueOf((String)warehousesCB.getSelectedItem());
                    Sklad s1 = new daoSklad().getWarehouseById(chosenId);
                    telLabel.setText(s1.getTel());
                    streetLabel.setText(s1.getUlica());
                    cityLabel.setText(s1.getMesto());
                    pscLabel.setText(s1.getPsc());
                    webLabel.setText(s1.getWeb());
                } catch (NumberFormatException ex) {
                    telLabel.setText("");
                    streetLabel.setText("");
                    cityLabel.setText("");
                    pscLabel.setText("");
                    webLabel.setText("");
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    int optionButton = JOptionPane.showConfirmDialog(null,
                            "Do you really want to delete this warehouse ?",
                            "Delete confirmation",JOptionPane.YES_NO_OPTION);

                    if (optionButton == JOptionPane.YES_OPTION){
                        try {
                            int chosenId = Integer.valueOf((String)warehousesCB.getSelectedItem());
                            Sklad s1 = new daoSklad().getWarehouseById(chosenId);
                            if(deleteData(s1)){
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Succesfully deleted warehouse No. "+ chosenId
                                );

                            }
                            refreshButton.doClick();
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showConfirmDialog(null,
                                     "Delete is not possible now",
                                    "Warning", JOptionPane.DEFAULT_OPTION);
                        }
                    }
            }
        });
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nw == null) {
                    nw = new NewWarehouse();
                    nw.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Window for adding warehouse is already open");
                }

            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nw = null;
                uw = null;
                setData();
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int chosenId = Integer.valueOf((String)warehousesCB.getSelectedItem());
                    Sklad s1 = new daoSklad().getWarehouseById(chosenId);
                    if(nw == null) {
                        uw = new UpdateWarehouse(s1);
                        uw.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null,"Window for updating warehouse is already open");
                    }

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showConfirmDialog(
                            null, "Update is not possible now",
                            "Warning", JOptionPane.DEFAULT_OPTION);
                }

            }
        });

        addWindowListener(new  java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                if (!(nw == null)) {
                    nw.dispose();
                    nw = null;
                }
                if (!(uw == null)) {
                    uw.dispose();
                    uw = null;
                }
            }
        });
    }

    public void setData(){

        List<Sklad> l = new daoSklad().getAllWarehouses();

        warehousesCB.removeAllItems();
        warehousesCB.addItem("Select Number");
        for (Sklad s :l) {
            String item = String.valueOf(s.getIdsklad());
            warehousesCB.addItem(item);
        }
    }

    private boolean deleteData(Sklad s) {
        new daoSklad().deleteWarehouse(s.getIdsklad());
        return true;
    }
}
